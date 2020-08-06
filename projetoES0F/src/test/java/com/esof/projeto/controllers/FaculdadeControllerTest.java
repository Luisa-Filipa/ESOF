package com.esof.projeto.controllers;

import com.esof.projeto.models.Faculdade;
import com.esof.projeto.services.FaculdadeService;
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


@WebMvcTest(controllers = FaculdadeController.class)
class FaculdadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaculdadeService faculdadeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFaculdade() throws Exception {
        Faculdade faculdade = new Faculdade("faculdade1");

        String jsonRequest = this.objectMapper.writeValueAsString(faculdade);

        when(faculdadeService.createFaculdade(faculdade)).thenReturn(Optional.of(faculdade));

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Faculdade existingFaculdade = new Faculdade("faculdade2");

        when(this.faculdadeService.createFaculdade(faculdade)).thenReturn(Optional.empty());

        String existingCursoJson = this.objectMapper.writeValueAsString(existingFaculdade);

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON).content(existingCursoJson)
        ).andExpect(
                status().isBadRequest()
        );
    }

}