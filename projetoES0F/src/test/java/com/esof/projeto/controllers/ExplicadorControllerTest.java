package com.esof.projeto.controllers;

import com.esof.projeto.models.Explicador;
import com.esof.projeto.services.ExplicadorServices;
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

@WebMvcTest(controllers = ExplicadorController.class)
class ExplicadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicadorServices explicadorServices;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createExplicador() throws Exception {
        Explicador explicador = new Explicador();
        explicador.setName("explicador1");

        String jsonRequest = this.objectMapper.writeValueAsString(explicador);

        when(explicadorServices.createExplicador(explicador)).thenReturn(Optional.of(explicador));

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Explicador existingExplicador = new Explicador();
        existingExplicador.setName("explicador2");

        when(this.explicadorServices.createExplicador(existingExplicador)).thenReturn(Optional.empty());

        String existingExplicadorJson = this.objectMapper.writeValueAsString(existingExplicador);

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(existingExplicadorJson)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    void getExplicadorById() throws Exception{
        Explicador explicador = new Explicador();
        explicador.setName("explicador1");
        explicador.setId(1L);

        when(this.explicadorServices.findById(1L)).thenReturn(Optional.of(explicador));

        String responseJson = this.mockMvc.perform(
                get("/explicador/1")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        Explicador responseExplicador = this.objectMapper.readValue(responseJson, Explicador.class);
        assertEquals(explicador, responseExplicador);

        this.mockMvc.perform(
                get("/explicador/2")
        ).andExpect(
                status().isNotFound()
        );
    }

    @Test
    void getExplicadorByNome() {

    }

    @Test
    void getExplicadorByCurso() {

    }

}