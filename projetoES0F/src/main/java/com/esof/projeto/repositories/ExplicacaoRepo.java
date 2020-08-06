package com.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esof.projeto.models.Explicacao;

import java.util.Optional;

@Repository
public interface ExplicacaoRepo extends CrudRepository<Explicacao, Long> {

    Optional<Explicacao> findById(Long id);
}
