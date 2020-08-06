package com.esof.projeto.models;

import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

class ExplicacaoTest {

    @Test
    void sobreposicao() {

        Explicacao explicacao1= new Explicacao();
        explicacao1.setInicio(LocalDateTime.of(2020,1,13,12,0));

        Explicacao explicacao2= new Explicacao();
        explicacao1.setInicio(LocalDateTime.of(2020,1,13,12,30));

        Explicacao explicacao= new Explicacao();
        explicacao.setInicio(explicacao1.getInicio());

        if (explicacao.sobreposicao(explicacao2)){
            System.out.println("Teste bem sucedido");
        }

    }
}