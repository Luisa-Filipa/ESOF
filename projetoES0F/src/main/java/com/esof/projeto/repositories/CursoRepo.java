package com.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esof.projeto.models.Curso;

import java.util.Optional;

@Repository
public interface CursoRepo extends CrudRepository<Curso, Long> {
    Optional<Curso> findById(Long aLong);

    Optional<Curso> findByName(String name);
}
