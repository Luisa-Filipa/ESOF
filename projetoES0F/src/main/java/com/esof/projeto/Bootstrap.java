package com.esof.projeto;

import com.esof.projeto.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.esof.projeto.repositories.*;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AlunoRepo alunoRepo;

    @Autowired
    private CadeiraRepo cadeiraRepo;

    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    @Autowired
    private ExplicadorRepo explicadorRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Startup");

        Aluno aluno1 = new Aluno("Rui");
        Aluno aluno2 = new Aluno("Toni");

        Cadeira cadeira1 = new Cadeira("ESOF", 6, 1, 3);
        Cadeira cadeira2 = new Cadeira("MAT II", 7, 2, 1);

        Curso curso = new Curso("EngInformatica", 90, 3);

        Faculdade faculdade = new Faculdade("UFP");

        Disponibilidade disponibilidade = new Disponibilidade(LocalTime.of(16, 0, 0),LocalTime.of(20, 0, 0),DayOfWeek.FRIDAY);


        Idioma idioma = new Idioma("Portugues");

        Explicador explicador = new Explicador(idioma,"Alessandro");

        Explicacao explicacao = new Explicacao(LocalDateTime.of(2020,01,12,12,20),aluno1,explicador,cadeira1);

        curso.addCadeira(cadeira1);
        curso.addCadeira(cadeira2);

        explicador.addCadeira(cadeira1);
        explicador.addDisponibilidade(disponibilidade);
        explicador.addExplicacao(explicacao);
        explicador.addIdioma(idioma);

        this.alunoRepo.save(aluno1);
        this.alunoRepo.save(aluno2);
        this.cadeiraRepo.save(cadeira1);
        this.cadeiraRepo.save(cadeira2);
        this.cursoRepo.save(curso);
        this.faculdadeRepo.save(faculdade);
        this.explicadorRepo.save(explicador);
    }
}
