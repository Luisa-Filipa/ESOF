package com.esof.projeto.models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CursoTest {

    @Test
    void addCadeira() {

        Curso curso= new Curso();

        Cadeira cadeira1= new Cadeira();

        curso.addCadeira(cadeira1);

        if(curso.getCadeiras().contains(cadeira1)){
            System.out.println("Teste bem sucedido");
        }
    }

    @Test
    void removeCadeira() {

        Curso curso= new Curso();

        Cadeira cadeira1= new Cadeira();
        Cadeira cadeira2= new Cadeira();

        Set<Cadeira> cadeiras= new HashSet<>();
        cadeiras.add(cadeira1);
        cadeiras.add(cadeira2);

        curso.setCadeiras(cadeiras);

        curso.removeCadeira(cadeira1);

        if(!curso.getCadeiras().contains(cadeira1)){
            System.out.println("Teste bem sucedido");
        }
    }
}