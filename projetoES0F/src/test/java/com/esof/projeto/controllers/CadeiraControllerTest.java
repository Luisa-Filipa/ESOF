package com.esof.projeto.controllers;

import com.esof.projeto.models.Aluno;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.services.CadeiraService;
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

@WebMvcTest(controllers = CadeiraController.class)
class CadeiraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CadeiraService cadeiraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createdCadeira() throws Exception {
        Cadeira cadeira = new Cadeira();
        cadeira.setName("cadeira");

        String jsonRequest = this.objectMapper.writeValueAsString(cadeira);

        when(cadeiraService.createdCadeira(cadeira)).thenReturn(Optional.of(cadeira));

        this.mockMvc.perform(
                post("/cadeira").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Cadeira existingCadeira = new Cadeira();
        existingCadeira.setName("cadeira2");

        when(this.cadeiraService.createdCadeira(cadeira)).thenReturn(Optional.empty());

        String existingCadeiraJson = this.objectMapper.writeValueAsString(existingCadeira);

        this.mockMvc.perform(
                post("/cadeira").contentType(MediaType.APPLICATION_JSON).content(existingCadeiraJson)
        ).andExpect(
                status().isBadRequest()
        );

    }

}