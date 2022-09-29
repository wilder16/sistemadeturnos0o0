package com.clinicaodontologica.sistemadeturnos.controller;

import com.clinicaodontologica.sistemadeturnos.dto.TurnoDto;
import com.clinicaodontologica.sistemadeturnos.exception.PastDateException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;
import com.clinicaodontologica.sistemadeturnos.service.impl.TurnoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoServiceImpl turnoService;

    @Autowired
    public TurnoController(TurnoServiceImpl turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDto> guardarTurno(@RequestBody TurnoDto turno) throws ResourceNotFoundException, PastDateException {
        return new ResponseEntity(turnoService.agregarTurno(turno), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TurnoDto> bucarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity(turnoService.obtenerTurnoPorId(id), HttpStatus.OK);
    }

}
