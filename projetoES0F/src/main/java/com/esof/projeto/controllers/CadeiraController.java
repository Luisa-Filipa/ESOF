package com.esof.projeto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.esof.projeto.models.Cadeira;
import com.esof.projeto.services.CadeiraService;

import java.util.Optional;

@Controller
@RequestMapping("/cadeira")
public class CadeiraController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private CadeiraService cadeiraService;

    @Autowired
    public CadeiraController(CadeiraService cadeiraService) {
        this.cadeiraService = cadeiraService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Cadeira>> getAllCadeira() {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.cadeiraService.findAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cadeira> createdCadeira(@RequestBody Cadeira cadeira) {
        Optional<Cadeira> optionalCadeira = this.cadeiraService.createdCadeira(cadeira);
        if (optionalCadeira.isPresent()) {
            return ResponseEntity.ok(optionalCadeira.get());
        }
        throw new CadeiraController.CadeiraAlreadyExistsExcpetion(cadeira.getName());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cadeira already exists")
    private static class CadeiraAlreadyExistsExcpetion extends RuntimeException {

        public CadeiraAlreadyExistsExcpetion(String name) {
            super("A cadeira with name: " + name + " already exists");
        }
    }

    @PostMapping(value = "/{curso}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cadeira> createCadeira(@PathVariable("curso") String curso, @RequestBody Cadeira cadeira) {
        Optional<Cadeira> cadeiraOptional = this.cadeiraService.createCadeira(cadeira, curso);
        if (cadeiraOptional.isPresent()) {
            return ResponseEntity.ok(cadeiraOptional.get());
        }
        throw new CadeiraAlreadyExistsOrCursoNotExistException(cadeira.getName());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cadeira already exists or Curso does not exist")
    private class CadeiraAlreadyExistsOrCursoNotExistException extends RuntimeException {

        public CadeiraAlreadyExistsOrCursoNotExistException(String name) {
            super("A cadeira with name: " + name + " already exists or curso does not exist");
        }
    }
}