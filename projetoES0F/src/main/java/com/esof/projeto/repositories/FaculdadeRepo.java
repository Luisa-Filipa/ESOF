package com.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esof.projeto.models.Faculdade;

import java.util.Optional;

@Repository
public interface FaculdadeRepo extends CrudRepository<Faculdade, Long> {
    Optional<Faculdade> findByName(String name);

    Optional<Faculdade> findById(Long id);
}
