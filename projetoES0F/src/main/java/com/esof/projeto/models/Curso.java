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
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int ects;
    private int ano;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Faculdade faculdade;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Set<Cadeira> cadeiras = new HashSet<>();

    public Curso(String nome, int ects, int ano) {
        this.name = nome;
        this.ects = ects;
        this.ano = ano;
    }

    public Curso(String nome, int ects, int ano,Faculdade faculdade) {
        this.name = nome;
        this.ects = ects;
        this.ano = ano;
        this.faculdade = faculdade;
    }

    public void addCadeira(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
        cadeira.setCurso(this);
    }

    public void removeCadeira(Cadeira cadeira) {
        this.cadeiras.remove(cadeira);
    }

}
