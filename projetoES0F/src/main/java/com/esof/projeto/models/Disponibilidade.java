package com.esof.projeto.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
public class Disponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime inicio;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime fim;

    private DayOfWeek diaDaSemana;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Explicador explicador;

    public Disponibilidade(LocalTime inicio, LocalTime fim ,DayOfWeek diaDaSemana) {
        this.inicio=inicio;
        this.fim=fim;
        this.diaDaSemana=diaDaSemana;
    }

    public boolean contains(Explicacao explicacao) {
        DayOfWeek dayOfWeek = explicacao.getInicio().getDayOfWeek();
        if (dayOfWeek.equals(this.diaDaSemana)) {
            LocalTime appointmentStart = explicacao.getInicio().toLocalTime();
            LocalTime appointmentEnd = explicacao.getInicio().plusHours(1).toLocalTime();
            return this.contains(appointmentStart, appointmentEnd);
        }
        return false;
    }

    private boolean contains(LocalTime start, LocalTime end) {
        return (this.inicio.isBefore(start) || this.inicio.equals(start))
                &&
                (this.fim.isAfter(end) || this.fim.equals(end));
    }
}
