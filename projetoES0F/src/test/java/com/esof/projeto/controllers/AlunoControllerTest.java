package com.esof.projeto.controllers;

import com.esof.projeto.models.Aluno;
import com.esof.projeto.services.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAluno() throws Exception {
        Aluno aluno = new Aluno("aluno1");

        String jsonRequest = this.objectMapper.writeValueAsString(aluno);

        when(alunoService.createdAluno(aluno)).thenReturn(Optional.of(aluno));

        this.mockMvc.perform(
                post("/aluno").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Aluno existingAluno = new Aluno("aluno2");

        when(this.alunoService.createdAluno(existingAluno)).thenReturn(Optional.empty());

        String existingAlunoJson = this.objectMapper.writeValueAsString(existingAluno);

        this.mockMvc.perform(
                post("/aluno").contentType(MediaType.APPLICATION_JSON).content(existingAlunoJson)
        ).andExpect(
                status().isBadRequest()
        );

    }

    @Test
    void getAlunosById() throws Exception {
        Aluno aluno = new Aluno("aluno1");
        aluno.setId(1L);

        when(this.alunoService.findById(1L)).thenReturn(Optional.of(aluno));

        String responseJson = this.mockMvc.perform(
                get("/aluno/1")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        Aluno responseAluno = this.objectMapper.readValue(responseJson, Aluno.class);
        assertEquals(aluno, responseAluno);

        this.mockMvc.perform(
                get("/aluno/2")
        ).andExpect(
                status().isNotFound()
        );
    }
}