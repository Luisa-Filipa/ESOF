package com.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esof.projeto.models.Faculdade;
import com.esof.projeto.repositories.FaculdadeRepo;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class FaculdadeService {

    private FaculdadeRepo faculdadeRepo;

    @Autowired
    public FaculdadeService(FaculdadeRepo faculdadeRepo) {
        this.faculdadeRepo = faculdadeRepo;
    }

    public Optional<Faculdade> createFaculdade(Faculdade faculdade) {
        Optional<Faculdade> optionalFaculdade = this.faculdadeRepo.findByName(faculdade.getName());
        if (optionalFaculdade.isPresent()) {
            return Optional.empty();
        }
        Faculdade createdFaculdade = this.faculdadeRepo.save(faculdade);
        return Optional.of(createdFaculdade);
    }

    public Set<Faculdade> findAll() {
        Set<Faculdade> faculdades = new HashSet<>();
        for (Faculdade faculdade : this.faculdadeRepo.findAll()) {
            faculdades.add(faculdade);
        }
        return faculdades;
    }

    public Optional<Faculdade> findById(Long id) {
        return this.faculdadeRepo.findById(id);
    }

    public Optional<Faculdade> findByName(String name) {
        return this.faculdadeRepo.findByName(name);
    }

}
