package com.clinicaodontologica.sistemadeturnos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id",nullable = false)
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Turno> turnos;


}
