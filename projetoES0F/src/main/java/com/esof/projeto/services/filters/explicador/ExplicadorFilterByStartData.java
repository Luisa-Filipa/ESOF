package com.esof.projeto.services.filters.explicador;


import com.esof.projeto.models.Disponibilidade;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.services.filters.FilterI;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterByStartData implements FilterI<Explicador> {

    private LocalTime startDate;


    public ExplicadorFilterByStartData(LocalTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> entities) {
        if (this.startDate == null) {
            return entities;
        }
        System.out.println(startDate);
        Set<Explicador> explicadores = new HashSet<>();
        for (Explicador explicador : entities) {
            for (Disponibilidade oi : explicador.getDisponibilidades()) {
                if (oi != null && oi.getInicio() != null && oi.getFim().isAfter(this.startDate) && (oi.getInicio().isBefore(this.startDate) || oi.getInicio().equals(this.startDate))) {
                    System.out.println(explicador);
                    explicadores.add(explicador);
                }
            }
        }
        return explicadores;
    }
}
