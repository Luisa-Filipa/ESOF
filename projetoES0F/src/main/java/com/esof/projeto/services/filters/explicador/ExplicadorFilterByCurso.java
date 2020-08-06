package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.services.filters.FilterI;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterByCurso implements FilterI<Explicador>{
    private String curso;

    public ExplicadorFilterByCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> entities) {
        if(curso==null || curso.isEmpty()){
            return entities;
        }
        Set<Explicador> explicadores=new HashSet<>();
        for(Explicador explicador:entities){
            for (Cadeira oi : explicador.getCadeiras()) {
                if(oi.getCurso().getName().equalsIgnoreCase(curso)) {
                    explicadores.add(explicador);
                }
            }
        }
        return explicadores;
    }
}
