package com.clinicaodontologica.sistemadeturnos.service;

import com.clinicaodontologica.sistemadeturnos.dto.OdontologoDto;


import java.util.Collection;

public interface OdontologoService {

    OdontologoDto agregarOdontologo(OdontologoDto odontologo);
    OdontologoDto obtenerOdontologoPorId(Long id);
    Collection<OdontologoDto> listarOdontologo();
    OdontologoDto modificarOdontologo(OdontologoDto odontologo);
    OdontologoDto eliminarOdontologoPorId(Long id);
}
