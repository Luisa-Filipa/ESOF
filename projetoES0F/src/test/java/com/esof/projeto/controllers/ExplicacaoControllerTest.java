package com.esof.projeto.controllers;

import com.esof.projeto.models.Explicacao;
import com.esof.projeto.services.ExplicacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ExplicacaoController.class)
class ExplicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicacaoService explicacaoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createExplicacao() throws Exception{
        Explicacao explicacao = new Explicacao();
        explicacao.setInicio(LocalDateTime.of(2020,1,12,12,30));

        String jsonRequest = this.objectMapper.writeValueAsString(explicacao);

        when(explicacaoService.createdExplicacao(explicacao)).thenReturn(Optional.of(explicacao));

        this.mockMvc.perform(
                post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Explicacao existingExplicacao = new Explicacao();
        existingExplicacao.setInicio(LocalDateTime.of(2020,1,19,12,30));

        when(this.explicacaoService.createdExplicacao(explicacao)).thenReturn(Optional.empty());

        String existingCursoJson = this.objectMapper.writeValueAsString(existingExplicacao);

        this.mockMvc.perform(
                post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(existingCursoJson)
        ).andExpect(
                status().isBadRequest()
        );
    }

}