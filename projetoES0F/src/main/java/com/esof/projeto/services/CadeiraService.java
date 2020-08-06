package com.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Curso;
import com.esof.projeto.repositories.CadeiraRepo;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CadeiraService {

    private CursoService cursoService;
    private CadeiraRepo cadeiraRepo;

    @Autowired
    public CadeiraService(CursoService cursoService, CadeiraRepo cadeiraRepo) {
        this.cursoService = cursoService;
        this.cadeiraRepo = cadeiraRepo;
    }

    public Optional<Cadeira> createdCadeira(Cadeira cadeira) {
        Optional<Cadeira> optionalCadeira = this.cadeiraRepo.findByName(cadeira.getName());
        if (optionalCadeira.isPresent()) {
            return Optional.empty();
        }
        Cadeira createdCadeira = this.cadeiraRepo.save(cadeira);
        return Optional.of(createdCadeira);
    }

    public Set<Cadeira> findAll() {
        Set<Cadeira> cadeiras = new HashSet<>();
        for (Cadeira cadeira : this.cadeiraRepo.findAll()) {
            cadeiras.add(cadeira);
        }
        return cadeiras;
    }

    public Optional<Cadeira> findById(Long id) {
        return this.cadeiraRepo.findById(id);
    }

    public Optional<Cadeira> findByName(String name) {
        return this.cadeiraRepo.findByName(name);
    }

    public Optional<Cadeira> updateCursoCadeira(String cadeira, Curso curso) {
        Optional<Cadeira> optionalCadeira = this.cadeiraRepo.findByName(cadeira);
        if (optionalCadeira.isPresent()) {
            optionalCadeira.get().setCurso(curso);
            cadeiraRepo.save(optionalCadeira.get());
            return optionalCadeira;
        }
        return Optional.empty();
    }

    public Optional<Cadeira> createCadeira(Cadeira cadeira, String curso) {
        Optional<Curso> oCurso;
        if (curso != null) {
            oCurso = this.cursoService.findByName(curso);
            if (oCurso.isPresent()) {
                Optional<Cadeira> optionalCadeira;
                if (cadeira.getName() != null) {
                    optionalCadeira = this.cadeiraRepo.findByName(cadeira.getName());
                    if (optionalCadeira.isPresent()) {
                        return Optional.empty();
                    }
                    oCurso.get().addCadeira(cadeira);
                    Cadeira createdCadeira = this.cadeiraRepo.save(cadeira);
                    return Optional.of(createdCadeira);
                }
            }
        }
        return Optional.empty();
    }
}
