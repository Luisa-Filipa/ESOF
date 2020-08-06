package com.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esof.projeto.models.Curso;
import com.esof.projeto.models.Faculdade;
import com.esof.projeto.repositories.CursoRepo;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CursoService {

    private CursoRepo cursoRepo;
    private FaculdadeService faculdadeService;

    @Autowired
    public CursoService(CursoRepo cursoRepo, FaculdadeService faculdadeService) {
        this.cursoRepo = cursoRepo;
        this.faculdadeService = faculdadeService;
    }

    public Optional<Curso> createdCurso(Curso curso) {
        Optional<Curso> optionalCurso = this.cursoRepo.findByName(curso.getName());
        if (optionalCurso.isPresent()) {
            return Optional.empty();
        }
        Curso createdCurso = this.cursoRepo.save(curso);
        return Optional.of(createdCurso);
    }

    public Set<Curso> findAll() {
        Set<Curso> cursos = new HashSet<>();
        for (Curso curso : this.cursoRepo.findAll()) {
            cursos.add(curso);
        }
        return cursos;
    }

    public Optional<Curso> createCurso(String faculdade, Curso curso) {
        Optional<Faculdade> optionalFaculdade;
        if (faculdade != null) {
            optionalFaculdade = this.faculdadeService.findByName(faculdade);
            if (optionalFaculdade.isPresent()) {
                Optional<Curso> optionalCurso;
                if (curso.getName() != null) {
                    optionalCurso = this.cursoRepo.findByName(curso.getName());
                    if (optionalCurso.isPresent()) {
                        return Optional.empty();
                    }
                    optionalFaculdade.get().addCurso(curso);
                    Curso createdCurso = this.cursoRepo.save(curso);
                    return Optional.of(createdCurso);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Curso> findById(Long id) {
        return this.cursoRepo.findById(id);
    }

    public Optional<Curso> findByName(String name) {
        return this.cursoRepo.findByName(name);
    }
}
