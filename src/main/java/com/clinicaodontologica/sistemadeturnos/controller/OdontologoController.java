package com.clinicaodontologica.sistemadeturnos.controller;

import com.clinicaodontologica.sistemadeturnos.dto.OdontologoDto;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;
import com.clinicaodontologica.sistemadeturnos.service.impl.OdontologoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoServiceImpl odontologoService;

    @Autowired
    public OdontologoController(OdontologoServiceImpl odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<OdontologoDto> guargarOdontologo(@RequestBody OdontologoDto odontologo) throws DuplicateResourceException
    {
        return new ResponseEntity(odontologoService.agregarOdontologo(odontologo), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity(odontologoService.obtenerOdontologoPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public Collection<OdontologoDto> listarOdontologo(){
        return odontologoService.listarOdontologo();
    }

    @PatchMapping
    public OdontologoDto modificarOdontologo(@RequestBody OdontologoDto odontologo) throws ResourceNotFoundException {
        return odontologoService.modificarOdontologo(odontologo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<OdontologoDto> elimianarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity(odontologoService.eliminarOdontologoPorId(id), HttpStatus.OK);
    }
}
