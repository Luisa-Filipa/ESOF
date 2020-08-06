package com.esof.projeto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.esof.projeto.models.Explicacao;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.services.ExplicacaoService;

import java.util.Optional;

@Controller
@RequestMapping("/explicacao")
public class ExplicacaoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExplicacaoService explicacaoService;

    @Autowired
    public ExplicacaoController(ExplicacaoService explicacaoService) {
        this.explicacaoService = explicacaoService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> createExplicacao(@RequestBody Explicacao explicacao) {
        Optional<Explicacao> optionalExplicacao = this.explicacaoService.createdExplicacao(explicacao);
        if (optionalExplicacao.isPresent()) {
            return ResponseEntity.ok(optionalExplicacao.get().getExplicador());
        }
        throw new ExplicacaoController.ExplicacaoAlreadyExistsExcpetion(explicacao);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Explicador already exists")
    private static class ExplicacaoAlreadyExistsExcpetion extends RuntimeException {

        public ExplicacaoAlreadyExistsExcpetion(Explicacao explicacao) {
            super("A explicador with name: " + explicacao + " already exists");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Explicacao>> getAllExplicacao() {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.explicacaoService.findAll());
    }
}
