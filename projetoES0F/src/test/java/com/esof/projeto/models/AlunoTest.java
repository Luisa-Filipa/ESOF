package com.esof.projeto.models;

import org.junit.jupiter.api.Test;


class AlunoTest {

    @Test
    void addExplicacao() {
        Explicacao explicacao = new Explicacao();

        Aluno aluno = new Aluno();
        aluno.setName("aluno");

        aluno.addExplicacao(explicacao);

        if(aluno.getExplicacacoes().contains(explicacao)){
            System.out.println("Teste bem sucedido");
        }
    }

    @Test
    void desmarcarExplicacao() {

        Explicacao explicacao = new Explicacao();

        Aluno aluno = new Aluno();
        aluno.setName("aluno");

        aluno.addExplicacao(explicacao);

        aluno.desmarcarExplicacao(explicacao);

        if(!aluno.getExplicacacoes().contains(explicacao)){
            System.out.println("Teste bem sucedido");
        }
    }
}