package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.Idioma;
import com.esof.projeto.services.filters.FilterI;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterByEcts implements FilterI<Explicador> {

    private Integer ects;

    public ExplicadorFilterByEcts(Integer ects) {
        this.ects = ects;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> entities) {
        if(ects==null || ects==0){
            return entities;
        }
        Set<Explicador> explicadores=new HashSet<>();
        for(Explicador explicador:entities){
            for (Cadeira oi : explicador.getCadeiras()) {
                if(oi.getEcts() == Integer.parseInt(ects.toString())) {
                    explicadores.add(explicador);
                }
            }
        }
        return explicadores;
    }
}
