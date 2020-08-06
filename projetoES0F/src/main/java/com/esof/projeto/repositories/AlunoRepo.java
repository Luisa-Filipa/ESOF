package com.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esof.projeto.models.Aluno;

import java.util.Optional;

@Repository
public interface AlunoRepo extends CrudRepository<Aluno, Long> {
    Optional<Aluno> findById(Long aLong);
    Optional<Aluno> findByName(String name);
}
