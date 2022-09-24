package com.clinicaodontologica.sistemadeturnos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, nullable = false)
    private String calle;

    @Column(length = 50, nullable = false)
    private String numero;

    @Column(length = 50, nullable = false)
    private String localidad;

    @Column(length = 50, nullable = false)
    private String provincia;

}
