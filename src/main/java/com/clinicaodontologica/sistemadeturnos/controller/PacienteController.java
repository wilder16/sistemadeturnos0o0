package com.clinicaodontologica.sistemadeturnos.controller;

import com.clinicaodontologica.sistemadeturnos.dto.PacienteDto;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.service.impl.PacienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteServiceImpl pacienteService;

    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDto> guardarPaciente(@RequestBody PacienteDto paciente) throws DuplicateResourceException {

        return new ResponseEntity(pacienteService.agregarPaciente(paciente), HttpStatus.OK);
    }
}
