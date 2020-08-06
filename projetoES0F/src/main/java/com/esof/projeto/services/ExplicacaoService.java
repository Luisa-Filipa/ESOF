package com.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esof.projeto.models.Explicacao;
import com.esof.projeto.repositories.ExplicacaoRepo;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ExplicacaoService {

    private ExplicacaoRepo explicacaoRepo;

    @Autowired
    public ExplicacaoService(ExplicacaoRepo explicacaoRepo) {
        this.explicacaoRepo = explicacaoRepo;
    }

    public Optional<Explicacao> createdExplicacao(Explicacao explicacao) {
        Optional<Explicacao> optionalExplicacao = this.explicacaoRepo.findById(explicacao.getId());
        if (optionalExplicacao.isPresent()) {
            return Optional.empty();
        }
        Explicacao createdExplicacao = this.explicacaoRepo.save(explicacao);
        return Optional.of(createdExplicacao);
    }

    public Set<Explicacao> findAll() {
        Set<Explicacao> explicacaos = new HashSet<>();
        for (Explicacao explicacao : this.explicacaoRepo.findAll()) {
            explicacaos.add(explicacao);
        }
        return explicacaos;
    }

    public Optional<Explicacao> findById(Long id) {
        return this.explicacaoRepo.findById(id);
    }


}
