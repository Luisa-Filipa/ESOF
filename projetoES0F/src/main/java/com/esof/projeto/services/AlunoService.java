package com.esof.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esof.projeto.models.Aluno;
import com.esof.projeto.repositories.AlunoRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AlunoService {

    private AlunoRepo alunoRepo;

    @Autowired
    public AlunoService(AlunoRepo alunoRepo1) {
        this.alunoRepo = alunoRepo1;
    }

    public Optional<Aluno> createdAluno(Aluno aluno) {
        Optional<Aluno> optionalAluno = this.alunoRepo.findByName(aluno.getName());
        if (optionalAluno.isPresent()) {
            return Optional.empty();
        }
        Aluno createdAluno = this.alunoRepo.save(aluno);
        return Optional.of(createdAluno);
    }

    public Set<Aluno> findAll() {
        Set<Aluno> alunos = new HashSet<>();
        for (Aluno aluno : this.alunoRepo.findAll()) {
            alunos.add(aluno);
        }
        return alunos;
    }

    public Optional<Aluno> findById(Long id) {
        return this.alunoRepo.findById(id);
    }

    public Optional<Aluno> findByName(String name) {
        return this.alunoRepo.findByName(name);
    }
}
