package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.services.filters.FilterI;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterByAno  implements FilterI<Explicador> {

    private Integer ano;

    public ExplicadorFilterByAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> entities) {
        if(ano==null || ano==0){
            return entities;
        }
        Set<Explicador> explicadores=new HashSet<>();
        for(Explicador explicador:entities){
            for (Cadeira oi : explicador.getCadeiras()) {
                if(oi.getAno() == Integer.parseInt(ano.toString())) {
                    explicadores.add(explicador);
                }
            }
        }
        return explicadores;
    }
}
