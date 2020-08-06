package com.esof.projeto.models;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

class ExplicadorTest {

    @Test
    void addCadeira() {
        Explicador explicador1 = new Explicador();

        Cadeira cadeira= new Cadeira();

        explicador1.addCadeira(cadeira);

        if(explicador1.getCadeiras().contains(cadeira)){
            System.out.println("Teste bem sucedido");
        }
    }


    @Test
    void addIdioma() {

        Explicador explicador1 = new Explicador();

        Idioma idioma= new Idioma();

        explicador1.addIdioma(idioma);

        if(explicador1.getIdiomas().contains(idioma)){
            System.out.println("Teste bem sucedido");
        }
    }

    @Test
    void addDisponibilidade() {

        Explicador explicador1 = new Explicador();

        Disponibilidade disponibilidade= new Disponibilidade();

        explicador1.addDisponibilidade(disponibilidade);

        if(explicador1.getDisponibilidades().contains(disponibilidade)){
            System.out.println("Teste bem sucedido");
        }
    }

    @Test
    void addExplicacao() {

        Explicador explicador1 = new Explicador();

        Explicacao explicacao= new Explicacao();

        explicador1.addExplicacao(explicacao);

        if(explicador1.getCadeiras().contains(explicacao)){
            System.out.println("Teste bem sucedido");
        }
    }

    @Test
    void desmarcarExplicacao() {

        Explicador explicador = new Explicador();

        Aluno aluno=new Aluno();

        Explicacao explicacao1= new Explicacao();
        Explicacao explicacao2= new Explicacao();


        Set<Explicacao> explicacoes= new HashSet<>();
        explicacoes.add(explicacao1);
        explicacoes.add(explicacao2);

        explicador.setExplicacoes(explicacoes);
        explicacao1.setAluno(aluno);
        explicacao2.setAluno(aluno);

        explicador.desmarcarExplicacao(explicacao1);

        if(!explicador.getExplicacoes().contains(explicacao1)){
            System.out.println("Teste bem sucedido");
        }
    }


    @Test
    void verfiAgend() {

        Explicador explicador= new Explicador();
        Explicacao explicacao= new Explicacao();

        LocalDateTime dl1= LocalDateTime.of(2020,1,13,12,0);

        Disponibilidade d1= new Disponibilidade();
        d1.setInicio(LocalTime.of(12,0));

        explicacao.setInicio(dl1);

        if (!explicador.verfiAgend(explicacao)){
            System.out.println("Teste bem sucedido");
        }
    }
}