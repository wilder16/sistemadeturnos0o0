package com.clinicaodontologica.sistemadeturnos.service;

import com.clinicaodontologica.sistemadeturnos.dto.OdontologoDto;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;


import java.util.Collection;

public interface IOdontologoService {

    OdontologoDto agregarOdontologo(OdontologoDto odontologo) throws DuplicateResourceException;
    OdontologoDto obtenerOdontologoPorId(Long id) throws ResourceNotFoundException;
    Collection<OdontologoDto> listarOdontologo();
    OdontologoDto modificarOdontologo(OdontologoDto odontologo) throws ResourceNotFoundException, JsonMappingException;
    OdontologoDto eliminarOdontologoPorId(Long id) throws ResourceNotFoundException;
}
