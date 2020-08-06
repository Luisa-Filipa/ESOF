package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.services.filters.FilterI;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterByCadeira implements FilterI<Explicador> {

    private String name;

    public ExplicadorFilterByCadeira(String name) {
        this.name = name;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> entities) {
        if(name==null || name.isEmpty()){
            return entities;
        }
        Set<Explicador> explicadores=new HashSet<>();
        for (Explicador explicador : entities) {
            for (Cadeira oi : explicador.getCadeiras()) {
                if (oi != null && oi.getName() != null && oi.getName().equalsIgnoreCase(name)) {
                    explicadores.add(explicador);
                }
            }
        }
        return explicadores;
    }
}
