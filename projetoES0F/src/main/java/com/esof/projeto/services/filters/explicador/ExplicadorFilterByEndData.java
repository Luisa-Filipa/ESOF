package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Disponibilidade;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.services.filters.FilterI;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterByEndData  implements FilterI<Explicador> {

    private LocalTime endDate;

    public ExplicadorFilterByEndData(LocalTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> entities) {
        if (this.endDate == null) {
            return entities;
        }
        Set<Explicador> explicadores = new HashSet<>();
        for (Explicador explicador : entities) {
            for (Disponibilidade oi : explicador.getDisponibilidades()) {
                if (oi != null && oi.getFim() != null && oi.getInicio().isBefore(this.endDate) && (oi.getFim().isAfter(this.endDate) || oi.getFim().equals(this.endDate))) {
                    explicadores.add(explicador);
                    System.out.println(explicador);
                    System.out.println(endDate);
                }
            }
        }
        System.out.println(explicadores);
        return explicadores;
    }
}
