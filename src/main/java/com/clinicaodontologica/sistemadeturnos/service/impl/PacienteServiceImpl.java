package com.clinicaodontologica.sistemadeturnos.service.impl;

import com.clinicaodontologica.sistemadeturnos.dto.PacienteDto;
import com.clinicaodontologica.sistemadeturnos.entity.Paciente;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.repository.IPacienteRepository;
import com.clinicaodontologica.sistemadeturnos.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService {

    private final IPacienteRepository pacienteRepository;
    private final ObjectMapper mapper;
    private final static Logger LOGGER = Logger.getLogger(PacienteServiceImpl.class);

    public PacienteServiceImpl(IPacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }


    @Override
    public PacienteDto agregarPaciente(PacienteDto paciente) throws DuplicateResourceException {
        Optional<Paciente> pacienteConsultado = pacienteRepository.findByDni(paciente.getDni());
        if(!pacienteConsultado.isPresent()){
            pacienteRepository.save(mapper.convertValue(paciente, Paciente.class));
            return paciente;
        }
        else {
            throw new DuplicateResourceException("Ya existe un paciente con el dni " + paciente.getDni());
        }
    }

    @Override
    public PacienteDto obtenerPacientePorId(Long id) {
        return null;
    }

    @Override
    public Collection<PacienteDto> listarPaciente() {
        return null;
    }

    @Override
    public PacienteDto modificarPaciente(PacienteDto paciente) {
        return null;
    }

    @Override
    public PacienteDto elimiarPacientePorId(Long id) {
        return null;
    }
}
