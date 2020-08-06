package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.services.filters.FilterI;
import com.esof.projeto.models.*;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterByIdioma implements FilterI<Explicador> {

    private String name;

    public ExplicadorFilterByIdioma(String name) {
        this.name = name;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> entities) {
        if(name==null || name.isEmpty()){
            return entities;
        }
        Set<Explicador> explicadores=new HashSet<>();
        for (Explicador explicador : entities) {
            for (Idioma oi : explicador.getIdiomas()) {
                if (oi != null && oi.getIdioma() != null && oi.getIdioma().equalsIgnoreCase(name)) {
                    explicadores.add(explicador);
                }
            }
        }
        return explicadores;
    }
}