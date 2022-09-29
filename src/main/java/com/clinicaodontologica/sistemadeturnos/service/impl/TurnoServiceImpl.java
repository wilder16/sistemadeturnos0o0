package com.clinicaodontologica.sistemadeturnos.service.impl;

import com.clinicaodontologica.sistemadeturnos.dto.TurnoDto;
import com.clinicaodontologica.sistemadeturnos.entity.Odontologo;
import com.clinicaodontologica.sistemadeturnos.entity.Paciente;
import com.clinicaodontologica.sistemadeturnos.entity.Turno;
import com.clinicaodontologica.sistemadeturnos.exception.GlobalExceptionHandler;
import com.clinicaodontologica.sistemadeturnos.exception.PastDateException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;
import com.clinicaodontologica.sistemadeturnos.repository.IOdontologoRepository;
import com.clinicaodontologica.sistemadeturnos.repository.IPacienteRepository;
import com.clinicaodontologica.sistemadeturnos.repository.ITurnoRepository;
import com.clinicaodontologica.sistemadeturnos.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements ITurnoService {

    private final ITurnoRepository turnoRepository;
    private final IPacienteRepository pacienteRepository;
    private final IOdontologoRepository odontologoRepository;
    private final ObjectMapper mapper;

    public static final Logger LOGGER = Logger.getLogger(TurnoServiceImpl.class);
    @Autowired
    public TurnoServiceImpl(ITurnoRepository turnoRepository, IPacienteRepository pacienteRepository, IOdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }

    @Override
    public TurnoDto agregarTurno(TurnoDto turno) throws ResourceNotFoundException, PastDateException {
        Turno turnoEntity = mapper.convertValue(turno, Turno.class);
        Optional<Paciente> pacienteEntity = pacienteRepository.findById(turnoEntity.getPaciente().getId());
        Optional<Odontologo> odontologoEntity = odontologoRepository.findById(turnoEntity.getOdontologo().getId());
        if(pacienteEntity.isPresent()  && odontologoEntity.isPresent()){
            if(!turnoEntity.getFechaDeTurno().isBefore(LocalDate.now())){
                turnoRepository.save(turnoEntity);
                return mapper.convertValue(turnoEntity, TurnoDto.class);

            }else{
                throw new PastDateException("No se puede asignar un turno en una fecha en el pasodo");
            }
        }else {
            throw new ResourceNotFoundException("No existe un paciente o un odontologo con ese id ingresado");
        }
    }

    @Override
    public TurnoDto obtenerTurnoPorId(Long id) throws ResourceNotFoundException {
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isPresent()){
            LOGGER.info("Consultando el turno con el id " + id);
            return mapper.convertValue(turno, TurnoDto.class);
        }else {
            throw new ResourceNotFoundException("No existe un turno con el id: " + id);
        }
    }

    @Override
    public Collection<TurnoDto> listarTurno() {
        return null;
    }

    @Override
    public TurnoDto modificarTurno(TurnoDto turno) {
        return null;
    }

    @Override
    public TurnoDto elimiarTurnoPorId(Long id) {
        return null;
    }
}
