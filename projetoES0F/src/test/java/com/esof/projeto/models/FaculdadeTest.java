package com.esof.projeto.models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class FaculdadeTest {

    @Test
    void addCurso() {

        Faculdade faculdade= new Faculdade();

        Curso curso1= new Curso();

        faculdade.addCurso(curso1);

        if(faculdade.getCursos().contains(curso1)){
            System.out.println("Teste bem sucedido");
        }
    }

    @Test
    void removeCurso() {

        Faculdade faculdade= new Faculdade();

        Curso curso1= new Curso();
        Curso curso2= new Curso();


        Set<Curso> cursos= new HashSet<>();
        cursos.add(curso1);
        cursos.add(curso2);

        faculdade.setCursos(cursos);

        faculdade.removeCurso(curso1);

        if(!faculdade.getCursos().contains(curso1)){
            System.out.println("Teste bem sucedido");
        }
    }
}