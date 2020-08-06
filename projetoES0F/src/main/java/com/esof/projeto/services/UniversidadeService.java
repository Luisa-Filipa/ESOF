package com.esof.projeto.services;

import com.esof.projeto.models.Universidade;
import com.esof.projeto.repositories.UniversidadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UniversidadeService {

    private UniversidadeRepo universidadeRepo;

    @Autowired
    public UniversidadeService(UniversidadeRepo universidadeRepo) {
        this.universidadeRepo = universidadeRepo;
    }

    public Optional<Universidade> createUniversidade(Universidade universidade) {
        Optional<Universidade> optionalUniversidade = this.universidadeRepo.findByName(universidade.getName());
        if (optionalUniversidade.isPresent()) {
            return Optional.empty();
        }
        Universidade createdUniversidade = this.universidadeRepo.save(universidade);
        return Optional.of(createdUniversidade);
    }

    public Set<Universidade> findAll() {
        Set<Universidade> universidades = new HashSet<>();
        for (Universidade universidade : this.universidadeRepo.findAll()) {
            universidades.add(universidade);
        }
        return universidades;
    }

    public Optional<Universidade> findById(Long id) {
        return this.universidadeRepo.findById(id);
    }
    public Optional<Universidade> findByName(String name) {
        return this.universidadeRepo.findByName(name);
    }
}