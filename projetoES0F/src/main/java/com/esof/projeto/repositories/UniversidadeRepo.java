package com.esof.projeto.repositories;

import com.esof.projeto.models.Universidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversidadeRepo extends CrudRepository<Universidade, Long> {
    Optional<Universidade> findByName(String name);
    Optional<Universidade> findById(Long id);
}
