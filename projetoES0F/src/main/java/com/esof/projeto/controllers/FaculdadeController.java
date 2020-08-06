package com.esof.projeto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.esof.projeto.models.Faculdade;
import com.esof.projeto.services.FaculdadeService;

import java.util.Optional;

@Controller
@RequestMapping("/faculdade")
public class FaculdadeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaculdadeService faculdadeService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> createFaculdade(@RequestBody Faculdade faculdade) {

        Optional<Faculdade> faculdadeOptional = this.faculdadeService.createFaculdade(faculdade);
        if (faculdadeOptional.isPresent()) {
            return ResponseEntity.ok(faculdadeOptional.get());
        }
        throw new FaculdadeController.FaculdadeAlreadyExistsExcpetion(faculdade.getName());
    }

    /**
     * EXCESSÃO CASO A FACULDADE JÁ EXISTA
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Faculdade ja existe")
    private class FaculdadeAlreadyExistsExcpetion extends RuntimeException {

        public FaculdadeAlreadyExistsExcpetion(String name) {
            super("A faculdade with name: " + name + " already exists");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Faculdade>> getAllFac() {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.faculdadeService.findAll());
    }

}
