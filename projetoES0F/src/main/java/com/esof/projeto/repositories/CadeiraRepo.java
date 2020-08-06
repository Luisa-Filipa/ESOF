package com.esof.projeto.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esof.projeto.models.Cadeira;

import java.util.Optional;

@Repository
public interface CadeiraRepo extends CrudRepository<Cadeira, Long> {

    Optional<Cadeira> findByName(String name);

    Optional<Cadeira> findById(Long id);
}
