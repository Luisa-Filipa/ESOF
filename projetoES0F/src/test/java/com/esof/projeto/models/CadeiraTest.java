package com.esof.projeto.models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CadeiraTest {

    @Test
    void removeExplicador() {

        Cadeira cadeira= new Cadeira();
        Explicador explicador1 = new Explicador();
        Explicador explicador2 = new Explicador();

        Set<Explicador> explicadors= new HashSet<>();
        explicadors.add(explicador1);
        explicadors.add(explicador2);

        cadeira.setExplicadors(explicadors);

        cadeira.removeExplicador(explicador1);

        if(!cadeira.getExplicadors().contains(explicador1)){
            System.out.println("Teste bem sucedido");
        }
    }
}