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
public class Explicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Set<Idioma> idiomas = new HashSet<>();

    @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Set<Disponibilidade> disponibilidades = new HashSet<>();

    @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Set<Explicacao> explicacoes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Set<Cadeira> cadeiras = new HashSet<>();

    public Explicador(Idioma idioma, String name) {
        this.name = name;
        idiomas.add(idioma);
    }

    public Explicador(Set<Idioma> idiomas, Set<Disponibilidade> disponibilidades, Set<Explicacao> explicacoes, Set<Cadeira> cadeiras, String name) {
        this.idiomas = idiomas;
        this.disponibilidades = disponibilidades;
        this.explicacoes = explicacoes;
        this.cadeiras = cadeiras;
        this.name = name;
    }

    public void addCadeira(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
        cadeira.getExplicadors().add(this);
    }

    public void addIdioma(Idioma idioma) {
        this.idiomas.add(idioma);
        idioma.addExplicador(this);
    }

    public void addDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidades.add(disponibilidade);
        disponibilidade.setExplicador(this);
    }

    public void addExplicacao(Explicacao explicacao) {
        this.explicacoes.add(explicacao);
        explicacao.setExplicador(this);
        Aluno aluno = explicacao.getAluno();
        if (aluno != null && !aluno.getExplicacacoes().contains(explicacao)) {
            aluno.addExplicacao(explicacao);
        }
    }


    public void desmarcarExplicacao(Explicacao explicacao) {

        explicacao.getAluno().desmarcarExplicacao(explicacao);
        this.explicacoes.remove(explicacao);
    }

    /**
     * verificar se pode agendar
     *
     * @param explicacao explicacap
     * @return boolean
     */
    public boolean verfiAgend(Explicacao explicacao) {
        for (Disponibilidade agend : this.disponibilidades) {
            if (agend.contains(explicacao)) {
                return true;
            }
        }
        return false;
    }

    public Set<Disponibilidade> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidade(Set<Disponibilidade> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }
}
