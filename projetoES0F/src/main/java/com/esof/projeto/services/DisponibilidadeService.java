package com.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esof.projeto.models.Disponibilidade;
import com.esof.projeto.repositories.DisponibilidadeRepo;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DisponibilidadeService {

    private DisponibilidadeRepo disponibilidadeRepo;

    @Autowired
    public DisponibilidadeService(DisponibilidadeRepo disponibilidadeRepo) {
        this.disponibilidadeRepo = disponibilidadeRepo;
    }

    public Optional<Disponibilidade> createdDisponibilidade(Disponibilidade disponibilidade) {
        Optional<Disponibilidade> optionalDisponibilidade = this.disponibilidadeRepo.findById(disponibilidade.getId());
        if (optionalDisponibilidade.isPresent()) {
            return Optional.empty();
        }
        Disponibilidade createdDisponibilidade = this.disponibilidadeRepo.save(disponibilidade);
        return Optional.of(createdDisponibilidade);
    }

    public Set<Disponibilidade> findAll() {
        Set<Disponibilidade> disponibilidades = new HashSet<>();
        for (Disponibilidade disponibilidade : this.disponibilidadeRepo.findAll()) {
            disponibilidades.add(disponibilidade);
        }
        return disponibilidades;
    }

    public Optional<Disponibilidade> findById(Long id) {
        return this.disponibilidadeRepo.findById(id);
    }

/*    public Optional<Disponibilidade> findByName(String name) {
        return this.disponibilidadeRepo.findByName(name);
    }*/
}
