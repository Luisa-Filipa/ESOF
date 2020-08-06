package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.Idioma;
import org.junit.jupiter.api.Test;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterByIdiomaTest {

    @Test
    void filter() {

        Set<Explicador> explicadors=new HashSet<>();

        Idioma idioma= new Idioma();
        idioma.setIdioma("idioma");

        Idioma idioma1= new Idioma();
        idioma1.setIdioma("idioma1");

        Idioma idioma2= new Idioma();
        idioma2.setIdioma("idioma2");

        Set<Idioma> idiomasepl1=new HashSet<>();
        idiomasepl1.add(idioma);

        Set<Idioma> idiomasepl2=new HashSet<>();
        idiomasepl1.add(idioma1);


        Explicador explicador1 =new Explicador();
        explicador1.setName("explicador1");

        Explicador explicador2 =new Explicador();
        explicador2.setName("explicador2");


        explicador1.setIdiomas(idiomasepl1);

         explicadors.add(explicador1);
         explicadors.add(explicador2);


        ExplicadorFilterByIdioma explicadorFilterByIdioma= new ExplicadorFilterByIdioma(idioma.getIdioma());
        assertEquals(1,explicadorFilterByIdioma.filter(explicadors).size());

        explicadorFilterByIdioma= new ExplicadorFilterByIdioma("non existing idiomas");
        assertEquals(0,explicadorFilterByIdioma.filter(explicadors).size());

        explicadorFilterByIdioma= new ExplicadorFilterByIdioma(null);
        assertEquals(2,explicadorFilterByIdioma.filter(explicadors).size());

    }
}