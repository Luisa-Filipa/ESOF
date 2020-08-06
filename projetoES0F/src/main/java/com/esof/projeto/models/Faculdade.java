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
public class Faculdade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;

    public Faculdade(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "faculdade", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Set<Curso> cursos = new HashSet<>();

    public void addCurso(Curso curso) {
        curso.setFaculdade(this);
        this.cursos.add(curso);
    }

    public void removeCurso(Curso curso) {
        curso.setFaculdade(null);
        this.cursos.remove(curso);
    }

}
