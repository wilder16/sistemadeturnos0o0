package com.clinicaodontologica.sistemadeturnos.service.impl;

import com.clinicaodontologica.sistemadeturnos.dto.PacienteDto;
import com.clinicaodontologica.sistemadeturnos.entity.Paciente;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;
import com.clinicaodontologica.sistemadeturnos.repository.IPacienteRepository;
import com.clinicaodontologica.sistemadeturnos.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteServiceImpl implements IPacienteService {

    private final IPacienteRepository pacienteRepository;
    private final ObjectMapper mapper;
    private final static Logger LOGGER = Logger.getLogger(PacienteServiceImpl.class);

    @Autowired
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
    public PacienteDto obtenerPacientePorId(Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()){
            LOGGER.info("Consultando el paciente con el id " + id);
            return mapper.convertValue(paciente, PacienteDto.class);
        }else {
            throw new ResourceNotFoundException("No existe un paciente con el id: " + id);
        }
    }

    @Override
    public Collection<PacienteDto> listarPaciente() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDto> pacientesDto = new HashSet<>();
        for (Paciente paciente: pacientes) {
            pacientesDto.add(mapper.convertValue(paciente, PacienteDto.class));
        }
        LOGGER.info("Listando todos los paciente");
        return pacientesDto;
    }

    @Override
    public PacienteDto modificarPaciente(PacienteDto pacienteDto) throws ResourceNotFoundException {
        Optional<Paciente> pacienteModificar = pacienteRepository.findByDni(pacienteDto.getDni());
        if(pacienteModificar.isPresent()){
            Paciente pacienteEntity = mapper.convertValue(pacienteDto, Paciente.class);
            pacienteModificar.map(paciente -> {
                paciente.setNombre(pacienteEntity.getNombre());
                paciente.setApellido(pacienteEntity.getApellido());
                paciente.setEmail(pacienteEntity.getEmail());
                paciente.setFechaDeIngreso(pacienteEntity.getFechaDeIngreso());
                paciente.setDomicilio(pacienteEntity.getDomicilio());
                return pacienteRepository.save(paciente);
            });
            LOGGER.info("Modicando el paciente con el dni" + pacienteDto.getDni());
            return pacienteDto;
        }else {
            throw new ResourceNotFoundException("No existe un paciente con dni " + pacienteDto.getDni());
        }
    }

    @Override
    public PacienteDto elimiarPacientePorId(Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()){
            LOGGER.info("Eliminando el paciente con el id " + id);
            pacienteRepository.deleteById(id);
            return mapper.convertValue(paciente, PacienteDto.class);
        }else{
            throw new ResourceNotFoundException("No existe un paciente con el id: " + id);
        }
    }
}
