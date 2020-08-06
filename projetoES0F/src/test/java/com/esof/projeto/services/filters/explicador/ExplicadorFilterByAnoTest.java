package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Explicador;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterByAnoTest {

    @Test
    void filter() {

        Set<Explicador> explicadors=new HashSet<>();

        Cadeira cadeira= new Cadeira();
        cadeira.setName("cadeira");
        cadeira.setAno(3);

        Cadeira cadeira1= new Cadeira();
        cadeira1.setName("cadeira1");
        cadeira.setAno(1);

        Cadeira cadeira2= new Cadeira();
        cadeira2.setName("cadeira2");
        cadeira.setAno(2);
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


        ExplicadorFilterByAno explicadorFilterByAno= new ExplicadorFilterByAno(cadeira.getAno());
        assertEquals(1,explicadorFilterByAno.filter(explicadors).size());

        explicadorFilterByAno= new ExplicadorFilterByAno(null);
        assertEquals(2,explicadorFilterByAno.filter(explicadors).size());
    }
}