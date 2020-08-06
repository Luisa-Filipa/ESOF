package com.esof.projeto.controllers;

import com.esof.projeto.models.Curso;
import com.esof.projeto.models.Faculdade;
import com.esof.projeto.services.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCurso() throws Exception{
        Faculdade faculdade1= new Faculdade("faculdade1");
        Faculdade faculdade2= new Faculdade("faculdade2");
        Curso curso = new Curso();
        curso.setName("curso");

        String jsonRequest = this.objectMapper.writeValueAsString(curso);

        when(cursoService.createCurso(faculdade1.getName(),curso)).thenReturn(Optional.of(curso));

        this.mockMvc.perform(
                post("/curso/faculdade1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Curso existingCurso = new Curso();
        existingCurso.setName("curso");

        when(this.cursoService.createCurso(faculdade2.getName(),curso)).thenReturn(Optional.empty());

        String existingCursoJson = this.objectMapper.writeValueAsString(existingCurso);

        this.mockMvc.perform(
                post("/curso/faculdade2").contentType(MediaType.APPLICATION_JSON).content(existingCursoJson)
        ).andExpect(
                status().isBadRequest()
        );
    }


    @Test
    void createdCurso() throws Exception {
        Curso curso = new Curso();
        curso.setName("curso");

        String jsonRequest = this.objectMapper.writeValueAsString(curso);

        when(cursoService.createdCurso(curso)).thenReturn(Optional.of(curso));

        this.mockMvc.perform(
                post("/curso").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Curso existingCurso = new Curso();
        existingCurso.setName("curso");

        when(this.cursoService.createdCurso(curso)).thenReturn(Optional.empty());

        String existingCursoJson = this.objectMapper.writeValueAsString(existingCurso);

        this.mockMvc.perform(
                post("/curso").contentType(MediaType.APPLICATION_JSON).content(existingCursoJson)
        ).andExpect(
                status().isBadRequest()
        );
    }
}