package com.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esof.projeto.models.Idioma;

import java.util.Optional;

@Repository
public interface IdiomaRepo extends CrudRepository<Idioma, Long> {
    Optional<Idioma> findById(Long id);
}
