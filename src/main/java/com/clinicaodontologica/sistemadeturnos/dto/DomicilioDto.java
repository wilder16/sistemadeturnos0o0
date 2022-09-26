package com.clinicaodontologica.sistemadeturnos.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioDto {
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
}
