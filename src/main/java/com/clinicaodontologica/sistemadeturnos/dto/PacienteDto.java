package com.clinicaodontologica.sistemadeturnos.dto;

import com.clinicaodontologica.sistemadeturnos.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {

    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDate fechaDeIngreso;
    private DomicilioDto domicilio;
}
