package com.esof.projeto.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

class DisponibilidadeTest {

    @Test
    void contains() {
        Disponibilidade disponibilidade= new Disponibilidade();
        disponibilidade.setInicio(LocalTime.of(12,0));
        disponibilidade.setFim(disponibilidade.getInicio().plusHours(1));
        disponibilidade.setDiaDaSemana(DayOfWeek.FRIDAY);

        Explicacao explicacao= new Explicacao();
        explicacao.setInicio(LocalDateTime.of(2020,1,13,disponibilidade.getInicio().getHour(),disponibilidade.getInicio().getMinute()));

        if (!disponibilidade.contains(explicacao)){
            System.out.println("Teste bem sucedido");
        }
    }
}