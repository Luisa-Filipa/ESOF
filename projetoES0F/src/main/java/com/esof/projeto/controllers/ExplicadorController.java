package com.esof.projeto.controllers;

import com.esof.projeto.services.ExplicadorServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.esof.projeto.models.*;


import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExplicadorServices explicadorService;

    @Autowired
    public ExplicadorController(ExplicadorServices explicadorService) {
        this.explicadorService = explicadorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Explicador>> getAllExplicadores() {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.explicadorService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Explicador> getExplicadorById(@PathVariable("id") Long id) throws NoExplicadorExcpetion {
        this.logger.info("Received a post request");

        Optional<Explicador> optionalExplicador = this.explicadorService.findById(id);
        if (optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadorExcpetion(id);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Explicador>> searchFor(@RequestParam Map<String, String> query) {
        Set<Explicador> optionalExplicador= this.explicadorService.filterExplicador(query);
        if (!optionalExplicador.isEmpty()) {
            return ResponseEntity.ok(optionalExplicador);
        }
        throw new NoExplicadorExceptionWithQuery20pf(query);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> createExplicador(@RequestBody Explicador explicador) {

        Optional<Explicador> explicadorOptional = this.explicadorService.createExplicador(explicador);
        if (explicadorOptional.isPresent()) {
            return ResponseEntity.ok(explicadorOptional.get());
        }
        throw new ExplicadorController.ExplicadorAlreadyExistsExcpetion(explicador.getName());
    }

    @RequestMapping(value = "/{nome_explicador}", method = RequestMethod.GET)
    public ResponseEntity<Explicador> getExplicadorByNome(@PathVariable("nome") String nome) throws NoExplicadorsExcpetion {
        this.logger.info("Received a get request");

        Optional<Explicador> optionalExplicador = this.explicadorService.findByName(nome);
        if (optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadorsExcpetion(nome);
    }

    @PutMapping(value = "/{curso}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> putExplicaCurso(@PathVariable("curso") Long id, @RequestBody Explicador explicador) throws NoExplicadorssExcpetion {
        this.logger.info("Received a put request");
        Optional<Explicador> optionalExplicador = this.explicadorService.updateCurso(id, explicador);
        if (optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadorssExcpetion(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> putExplicadorDisponibilidade(@RequestBody Explicador explicador) throws NoExplicadorssExcpetion{
        this.logger.info("Received a put request");
        Optional<Explicador> optionalExplicador = this.explicadorService.updateDisciplina(explicador);
        if (optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadorssExcpetion(explicador.getId());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such explicador")
    private static class NoExplicadorssExcpetion extends RuntimeException {
        public NoExplicadorssExcpetion(Long id) {
            super("No such explicador with id: " + id);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such explicador")
    private class NoExplicadorExcpetion extends RuntimeException {

        public NoExplicadorExcpetion(Long id) {
            super("No such Explicador with id: " + id);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such client")
    private static class NoExplicadorsExcpetion extends RuntimeException {

        public NoExplicadorsExcpetion(String nome) {
            super("No such client with nome: " + nome);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Explicador already exists")
    private static class ExplicadorAlreadyExistsExcpetion extends RuntimeException {
        public ExplicadorAlreadyExistsExcpetion(String name) {
            super("A explicador with name: " + name + " already exists");
        }
    }


    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such explicador")
    private class NoExplicadorExceptionWithQuery20pf extends RuntimeException {

        public NoExplicadorExceptionWithQuery20pf(Map<String, String> query) {
            super("No such explicador with query: "+query);
        }
    }
}