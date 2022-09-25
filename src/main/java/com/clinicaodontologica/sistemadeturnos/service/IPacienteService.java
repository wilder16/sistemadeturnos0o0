package com.clinicaodontologica.sistemadeturnos.service;

import com.clinicaodontologica.sistemadeturnos.dto.PacienteDto;

import java.util.Collection;

public interface IPacienteService {


     PacienteDto agregarPaciente(PacienteDto paciente);
     PacienteDto obtenerPacientePorId(Long id);
     Collection<PacienteDto> listarPaciente();
     PacienteDto modificarPaciente(PacienteDto paciente);
     PacienteDto elimiarPacientePorId(Long id);



}
