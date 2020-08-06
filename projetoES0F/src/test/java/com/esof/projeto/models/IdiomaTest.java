package com.esof.projeto.models;

import org.junit.jupiter.api.Test;

class IdiomaTest {

    @Test
    void addExplicador() {

       Idioma idioma= new Idioma();

       Explicador explicador= new Explicador();

        idioma.addExplicador(explicador);

        if(idioma.getExplicadores().contains(explicador)){
            System.out.println("Teste bem sucedido");
        }
    }
}