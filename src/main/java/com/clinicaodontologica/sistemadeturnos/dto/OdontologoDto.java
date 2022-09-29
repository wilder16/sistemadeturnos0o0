package com.clinicaodontologica.sistemadeturnos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoDto {

    private String nombre;
    private String apellido;
    private String matricula;
}
