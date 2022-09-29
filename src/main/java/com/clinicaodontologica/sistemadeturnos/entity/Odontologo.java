package com.clinicaodontologica.sistemadeturnos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false)
    private String matricula;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Turno> turnos;


}
