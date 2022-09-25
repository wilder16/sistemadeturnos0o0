package com.clinicaodontologica.sistemadeturnos.service;

import com.clinicaodontologica.sistemadeturnos.dto.PacienteDto;

import java.util.Collection;

public interface PacienteService {


     PacienteDto agregarPaciente(PacienteDto paciente);
     PacienteDto obtenerPacientePorId(Long id);
     Collection<PacienteDto> listarPaciente();
     PacienteDto modificarPaciente(PacienteDto paciente);
     PacienteDto elimiarPacientePorId(Long id);



}
