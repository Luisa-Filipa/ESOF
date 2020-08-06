package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Disponibilidade;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.services.filters.FilterI;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterByDay  implements FilterI<Explicador> {

    private DayOfWeek day;

    public ExplicadorFilterByDay(DayOfWeek day) {
        this.day = day;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> entities) {
        if(day==null){
            return entities;
        }
        Set<Explicador> explicadores=new HashSet<>();
        for (Explicador explicador : entities) {
            for (Disponibilidade oi : explicador.getDisponibilidades()) {
                if (oi != null && oi.getDiaDaSemana().equals(day)) {
                    explicadores.add(explicador);
                }
            }
        }
        return explicadores;
    }
}
