package com.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esof.projeto.models.Explicador;

import java.util.Optional;

@Repository
public interface ExplicadorRepo extends CrudRepository<Explicador, Long> {
    Optional<Explicador> findByName(String name);

    Optional<Explicador> findById(Long id);
}
