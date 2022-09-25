package com.clinicaodontologica.sistemadeturnos.service;


import com.clinicaodontologica.sistemadeturnos.dto.TurnoDto;

import java.util.Collection;

public interface ITurnoService {

    TurnoDto agregarTurno(TurnoDto turno);
    TurnoDto obtenerTurnoPorId(Long id);
    Collection<TurnoDto> listarTurno();
    TurnoDto modificarTurno(TurnoDto turno);
    TurnoDto elimiarTurnoPorId(Long id);

}
