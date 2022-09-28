package com.clinicaodontologica.sistemadeturnos.controller;

import com.clinicaodontologica.sistemadeturnos.dto.PacienteDto;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;
import com.clinicaodontologica.sistemadeturnos.service.impl.PacienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    @GetMapping("{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.obtenerPacientePorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<PacienteDto>> listarPaciente(){
        return new ResponseEntity<>(pacienteService.listarPaciente(), HttpStatus.OK);
    }
    @PatchMapping
    public ResponseEntity<PacienteDto> modificarPaciente(@RequestBody PacienteDto paciente) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.modificarPaciente(paciente), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<PacienteDto> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.elimiarPacientePorId(id), HttpStatus.OK);
    }
}
