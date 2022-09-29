package com.clinicaodontologica.sistemadeturnos.service;


import com.clinicaodontologica.sistemadeturnos.dto.TurnoDto;
import com.clinicaodontologica.sistemadeturnos.exception.PastDateException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;

import java.util.Collection;

public interface ITurnoService {

    TurnoDto agregarTurno(TurnoDto turno) throws ResourceNotFoundException, PastDateException;
    TurnoDto obtenerTurnoPorId(Long id) throws ResourceNotFoundException;
    Collection<TurnoDto> listarTurno();
    TurnoDto modificarTurno(TurnoDto turno);
    TurnoDto elimiarTurnoPorId(Long id);

}
