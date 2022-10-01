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
import java.util.*;

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
                throw new PastDateException("No se puede asignar un turno en una fecha en el pasado");
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
        List<Turno> turnosEntity  = turnoRepository.findAll();
        Set<TurnoDto> turnosDto = new HashSet<>();
        for (Turno turno: turnosEntity) {
            turnosDto.add(mapper.convertValue(turno, TurnoDto.class));
        }
        LOGGER.info("Listando todos los trunos");
        return turnosDto;
    }

    @Override
    public TurnoDto modificarTurno(TurnoDto turnoDto, Long id) throws ResourceNotFoundException, PastDateException {
        Optional<Turno> turnoModificar = turnoRepository.findById(id);
        if (turnoModificar.isPresent()) {
            Turno turnoEntity = mapper.convertValue(turnoDto, Turno.class);
            Optional<Paciente> pacienteEntity = pacienteRepository.findById(turnoEntity.getPaciente().getId());
            Optional<Odontologo> odontologoEntity = odontologoRepository.findById(turnoEntity.getOdontologo().getId());
            if(pacienteEntity.isPresent()  && odontologoEntity.isPresent()){
                if(!turnoEntity.getFechaDeTurno().isBefore(LocalDate.now())){
                    turnoModificar.map( turno -> {
                        turno.setOdontologo(turnoEntity.getOdontologo());
                        turno.setPaciente(turnoEntity.getPaciente());
                        turno.setFechaDeTurno(turnoEntity.getFechaDeTurno());
                        return turnoRepository.save(turno);
                    });
                    LOGGER.info("Modificando el turno con el id " + id);
                    return mapper.convertValue(turnoEntity, TurnoDto.class);

                }else{
                    throw new PastDateException("No se puede modificar un turno en una fecha en el pasado");
                }
            }else {
                throw new ResourceNotFoundException("No existe un paciente o un odontologo con ese id ingresado");
            }
        }else{
            throw new ResourceNotFoundException("No existe un turno con id " + id);
        }
    }

    @Override
    public TurnoDto elimiarTurnoPorId(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoEntity = turnoRepository.findById(id);
        if(turnoEntity.isPresent()){
            LOGGER.info("Eliminando el turno con el id " + id);
            turnoRepository.deleteById(id);
            return mapper.convertValue(turnoEntity, TurnoDto.class);
        }else{
            throw new ResourceNotFoundException("No existe un turno con el id: " + id);
        }

    }
}
