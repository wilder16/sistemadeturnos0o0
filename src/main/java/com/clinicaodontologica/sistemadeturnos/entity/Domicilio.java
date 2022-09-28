package com.clinicaodontologica.sistemadeturnos.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
