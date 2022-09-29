package com.clinicaodontologica.sistemadeturnos.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="odontologo_id", referencedColumnName = "id", nullable = false)
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name ="paciente_id", referencedColumnName = "id", nullable = false)
    private Paciente paciente;

    @Column(name = "fecha_de_turno", nullable = false)
    private LocalDate fechaDeTurno;

}
