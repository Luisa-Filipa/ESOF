package com.esof.projeto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.esof.projeto.models.Curso;
import com.esof.projeto.services.CursoService;

import java.util.Optional;

@Controller
@RequestMapping("/curso")
public class CursoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> createdCurso(@RequestBody Curso curso) {
        Optional<Curso> cursoOptional = this.cursoService.createdCurso(curso);
        if (cursoOptional.isPresent()) {
            return ResponseEntity.ok(cursoOptional.get());
        }
        throw new CursoController.CursoAlreadyExistsExcpetion(curso.getName());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Curso already exists")
    private static class CursoAlreadyExistsExcpetion extends RuntimeException {

        public CursoAlreadyExistsExcpetion(String name) {
            super("A curso with name: " + name + " already exists");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Curso>> getAllCursos() {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.cursoService.findAll());
    }

    @PostMapping(value = "/{faculdade}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> createCurso(@PathVariable("faculdade") String faculdade, @RequestBody Curso curso) {
        Optional<Curso> cursoOptional = this.cursoService.createCurso(faculdade, curso);
        if (cursoOptional.isPresent()) {
            return ResponseEntity.ok(cursoOptional.get());
        }
        throw new CursoController.CursoAlreadyExistsException(curso.getName());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Curso already exists or Faculdade does not exist")
    private class CursoAlreadyExistsException extends RuntimeException {
        public CursoAlreadyExistsException(String name) {
            super("A curso with name: " + name + " already exists or faculdade does not exist");
        }
    }
}
