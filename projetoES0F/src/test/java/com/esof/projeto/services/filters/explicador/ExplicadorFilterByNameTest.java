package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Explicador;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterByNameTest {

    @Test
    void filter() {

        Set<Explicador> explicadors=new HashSet<>();

        Explicador explicador1=new Explicador();
        explicador1.setName("explicador1");

        Explicador explicador2=new Explicador();
        explicador2.setName("explicador2");

        Explicador explicador3=new Explicador();
        explicador3.setName("explicador3");

        Explicador explicador4=new Explicador();
        explicador4.setName("explicador4");

        explicadors.add(explicador1);
        explicadors.add(explicador2);
        explicadors.add(explicador3);
        explicadors.add(explicador4);



        ExplicadorFilterByName explicadorFilterByName= new ExplicadorFilterByName("explicador1");
        assertEquals(1,explicadorFilterByName.filter(explicadors).size());

        explicadorFilterByName= new ExplicadorFilterByName("non existing client");
        assertEquals(0,explicadorFilterByName.filter(explicadors).size());

        explicadorFilterByName= new ExplicadorFilterByName(null);
        assertEquals(4,explicadorFilterByName.filter(explicadors).size());

    }
}