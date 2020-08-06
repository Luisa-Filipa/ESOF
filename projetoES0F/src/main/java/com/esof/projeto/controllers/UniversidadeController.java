package com.esof.projeto.controllers;

import com.esof.projeto.models.Universidade;
import com.esof.projeto.services.UniversidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/universidade")
public class UniversidadeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UniversidadeService universidadeService;

    @Autowired
    public UniversidadeController(UniversidadeService universidadeService) {
        this.universidadeService = universidadeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Universidade>> getAllUniversidades() {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.universidadeService.findAll());
    }
}
