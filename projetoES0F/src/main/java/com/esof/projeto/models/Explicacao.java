package com.esof.projeto.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Explicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime inicio;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Aluno aluno;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Explicador explicador;

    @OneToOne(cascade = CascadeType.PERSIST)//unidirecional
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Cadeira cadeira;

    public Explicacao(LocalDateTime inicio, Aluno aluno, Explicador explicador, Cadeira cadeira) {
        this.inicio = inicio;
        this.aluno = aluno;
        this.explicador = explicador;
        this.cadeira = cadeira;
    }

    /**
     * verifica sobreposicao
     *
     * @param explicacao explicacao
     * @return boolean
     */
    public boolean sobreposicao(Explicacao explicacao) {
        return this.isBetween(explicacao) || explicacao.isBetween(this) ||
                (this.inicio.equals(explicacao.inicio) && this.inicio.plusHours(1).equals(explicacao.inicio.plusHours(1)));
    }

    /**
     * "entre"
     *
     * @param explicacao explicacao
     * @return boolean
     */
    private boolean isBetween(Explicacao explicacao) {
        LocalDateTime appointmentStartTime = explicacao.getInicio();
        LocalDateTime appointmentEndTime = explicacao.getInicio().plusHours(1);
        return this.isBetween(appointmentStartTime) || this.isBetween(appointmentEndTime);
    }

    /**
     * verificar hora
     *
     * @param timeToCheck tempo a verificar
     * @return boolean
     */
    private boolean isBetween(LocalDateTime timeToCheck) {
        return this.inicio.isBefore(timeToCheck) && this.inicio.plusHours(1).isAfter(timeToCheck);
    }
}

