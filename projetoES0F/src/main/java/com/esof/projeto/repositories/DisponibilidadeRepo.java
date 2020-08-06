package com.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esof.projeto.models.Disponibilidade;

import java.util.Optional;


@Repository
public interface DisponibilidadeRepo extends CrudRepository<Disponibilidade, Long> {
    Optional<Disponibilidade> findById(Long aLong);
}
