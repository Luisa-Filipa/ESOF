package com.esof.projeto.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String numero;

    public Aluno(String name) {
        this.name = name;
    }

    public Aluno(String name, String numero) {
        this.name = name;
        this.numero = numero;
    }

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Set<Explicacao> explicacacoes = new HashSet<>();

    public void addExplicacao(Explicacao explicacao) {
        this.explicacacoes.add(explicacao);
        explicacao.setAluno(this);
    }

    public void desmarcarExplicacao(Explicacao explicacao) {
        this.explicacacoes.remove(explicacao);
        explicacao.setAluno(this);
    }
}
