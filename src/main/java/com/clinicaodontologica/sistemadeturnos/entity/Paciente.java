package com.clinicaodontologica.sistemadeturnos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Column(length = 15, unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String email;

    @Column(name = "fecha_de_ingreos", nullable = false)
    private LocalDate fechaDeIngreso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id",nullable = false)
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos;


}
