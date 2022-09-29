package com.clinicaodontologica.sistemadeturnos.dto;

import com.clinicaodontologica.sistemadeturnos.entity.Odontologo;
import com.clinicaodontologica.sistemadeturnos.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private Odontologo odontologo;
    private Paciente paciente;
    private LocalDate fechaDeTurno;
}
