package com.esof.projeto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.esof.projeto.models.Aluno;
import com.esof.projeto.services.AlunoService;

import java.util.Optional;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Aluno>> getAllAlunos() {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.alunoService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Aluno> getAlunoById(@PathVariable("id") Long id) throws NoAlunoExcpetion {
        this.logger.info("Received a get request");

        Optional<Aluno> optionalAluno = this.alunoService.findById(id);
        if (optionalAluno.isPresent()) {
            return ResponseEntity.ok(optionalAluno.get());
        }
        throw new NoAlunoExcpetion(id);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such aluno")
    private static class NoAlunoExcpetion extends RuntimeException {

        public NoAlunoExcpetion(Long id) {
            super("No such aluno with id: " + id);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
        Optional<Aluno> alunoOptional = this.alunoService.createdAluno(aluno);
        if (alunoOptional.isPresent()) {
            return ResponseEntity.ok(alunoOptional.get());
        }
        throw new AlunoAlreadyExistsExcpetion(aluno.getName());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Aluno already exists")
    private static class AlunoAlreadyExistsExcpetion extends RuntimeException {

        public AlunoAlreadyExistsExcpetion(String name) {
            super("A aluno with name: " + name + " already exists");
        }
    }
}