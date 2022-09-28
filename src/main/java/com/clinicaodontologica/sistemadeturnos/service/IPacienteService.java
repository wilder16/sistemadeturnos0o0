package com.clinicaodontologica.sistemadeturnos.service;

import com.clinicaodontologica.sistemadeturnos.dto.PacienteDto;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;

import java.util.Collection;


public interface IPacienteService {


     PacienteDto agregarPaciente(PacienteDto paciente) throws DuplicateResourceException;
     PacienteDto obtenerPacientePorId(Long id) throws ResourceNotFoundException;
     Collection<PacienteDto> listarPaciente();
     PacienteDto modificarPaciente(PacienteDto paciente) throws ResourceNotFoundException;
     PacienteDto elimiarPacientePorId(Long id) throws ResourceNotFoundException;



}
