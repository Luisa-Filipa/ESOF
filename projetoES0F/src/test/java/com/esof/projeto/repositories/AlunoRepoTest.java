package com.esof.projeto.repositories;

import com.esof.projeto.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AlunoRepoTest {

    @Autowired
    private AlunoRepo alunoRepo;

    @Autowired
    private ExplicacaoRepo explicacaoRepo;

    @Autowired
    private ExplicadorRepo explicadorRepo;

    @Autowired
    private CadeiraRepo cadeiraRepo;

    @Autowired
    private IdiomaRepo idiomaRepo;

    @Autowired
    private DisponibilidadeRepo disponibilidadeRepo;

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    @Test
    void teste() {

        Aluno aluno1=new Aluno("aluno1");

        Curso curso1=new Curso();
        curso1.setName("curso1");

        Explicador explicador1=new Explicador();
        explicador1.setName("explicador1");

        Cadeira cadeira1=new Cadeira();
        cadeira1.setName("cadeira1");

        Idioma idioma1=new Idioma("idioma1");

        Disponibilidade disponibilidade1=new Disponibilidade();
        disponibilidade1.setInicio(LocalTime.of(12,30));

        Faculdade faculdade1= new Faculdade();
        faculdade1.setName("faculdade1");

        Explicacao explicacao1=new Explicacao();
        explicacao1.setAluno(aluno1);
        explicacao1.setCadeira(cadeira1);
        explicacao1.setExplicador(explicador1);
        explicacao1.setInicio(LocalDateTime.of(2020,1,12,12,30));

        explicador1.addDisponibilidade(disponibilidade1);
        explicador1.addCadeira(cadeira1);
        explicador1.addExplicacao(explicacao1);
        explicador1.addIdioma(idioma1);
        cadeira1.setCurso(curso1);

        aluno1.addExplicacao(explicacao1);

        curso1.addCadeira(cadeira1);

        faculdade1.addCurso(curso1);

        idioma1.addExplicador(explicador1);

        this.alunoRepo.save(aluno1);
        this.cadeiraRepo.save(cadeira1);
        this.disponibilidadeRepo.save(disponibilidade1);
        this.explicadorRepo.save(explicador1);
        this.explicacaoRepo.save(explicacao1);
        this.idiomaRepo.save(idioma1);
        this.faculdadeRepo.save(faculdade1);

        assertEquals(1,this.alunoRepo.count());
        assertEquals(1,this.cadeiraRepo.count());
        assertEquals(1,this.disponibilidadeRepo.count());
        assertEquals(1,this.explicacaoRepo.count());
        assertEquals(1,this.explicadorRepo.count());
        assertEquals(1,this.idiomaRepo.count());
        assertEquals(1,this.faculdadeRepo.count());
    }
}