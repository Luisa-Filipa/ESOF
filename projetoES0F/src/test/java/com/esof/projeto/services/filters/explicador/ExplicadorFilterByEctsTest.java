package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Explicador;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterByEctsTest {

    @Test
    void filter() {

        Set<Explicador> explicadors=new HashSet<>();

        Cadeira cadeira= new Cadeira();
        cadeira.setName("cadeira");
        cadeira.setEcts(7);

        Cadeira cadeira1= new Cadeira();
        cadeira1.setName("cadeira1");
        cadeira1.setEcts(6);

        Cadeira cadeira2= new Cadeira();
        cadeira2.setName("cadeira2");
        cadeira2.setEcts(2);

        Set<Cadeira> cadeirase1=new HashSet<>();
        cadeirase1.add(cadeira);

        Set<Cadeira> cadeirase2 =new HashSet<>();
        cadeirase2.add(cadeira1);


        Explicador explicador1 =new Explicador();
        explicador1.setName("explicador1");

        Explicador explicador2 =new Explicador();
        explicador2.setName("explicador2");


        explicador1.setCadeiras(cadeirase1);
        explicador2.setCadeiras(cadeirase2);

        explicadors.add(explicador1);
        explicadors.add(explicador2);


        ExplicadorFilterByEcts explicadorFilterByEcts= new ExplicadorFilterByEcts(cadeira.getEcts());
        assertEquals(1,explicadorFilterByEcts.filter(explicadors).size());

        explicadorFilterByEcts= new ExplicadorFilterByEcts(null);
        assertEquals(2,explicadorFilterByEcts.filter(explicadors).size());
    }
}