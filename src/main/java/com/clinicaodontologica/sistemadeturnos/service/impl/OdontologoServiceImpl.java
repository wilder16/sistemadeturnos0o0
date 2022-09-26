package com.clinicaodontologica.sistemadeturnos.service.impl;

import com.clinicaodontologica.sistemadeturnos.dto.OdontologoDto;
import com.clinicaodontologica.sistemadeturnos.entity.Odontologo;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;
import com.clinicaodontologica.sistemadeturnos.repository.IOdontologoRepository;
import com.clinicaodontologica.sistemadeturnos.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoServiceImpl implements IOdontologoService {

    private final IOdontologoRepository odontologoRepository;
    private final ObjectMapper mapper;
    private final static Logger LOGGER = Logger.getLogger(OdontologoServiceImpl.class);


    public OdontologoServiceImpl(IOdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }


    @Override
    public OdontologoDto agregarOdontologo(OdontologoDto odontologo) throws DuplicateResourceException {
        try {
            odontologoRepository.save( mapper.convertValue(odontologo, Odontologo.class));
            LOGGER.info("Se guardo correctamente el odontologo con matricula " + odontologo.getMatricula());
            return odontologo;
        }catch (Exception e){
            throw new DuplicateResourceException("Ya existe un odontologo con la matricula " + odontologo.getMatricula());
        }
    }

    @Override
    public OdontologoDto obtenerOdontologoPorId(Long id) throws ResourceNotFoundException {
        Optional<Odontologo>  odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            LOGGER.info("Consultando el odontologo con el id " + id);
            return mapper.convertValue(odontologo, OdontologoDto.class);
        }
        else{
            throw new ResourceNotFoundException("No existe un odontologo con el id: " + id);
        }
    }

    @Override
    public Collection<OdontologoDto> listarOdontologo() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDto> odontologosDto = new HashSet<>();
        for(Odontologo odontologo: odontologos)
            odontologosDto.add(mapper.convertValue(odontologo, OdontologoDto.class));
        return odontologosDto;
    }

    @Override
    public OdontologoDto modificarOdontologo(OdontologoDto odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoModificar = odontologoRepository.findByMatricula(odontologo.getMatricula()).map(odontologo1 ->{
            odontologo1.setNombre(odontologo.getNombre());
            odontologo1.setNombre(odontologo.getApellido());
            return odontologoRepository.save(odontologo1);
        });
        if(odontologoModificar.isPresent()){
            return odontologo;
        }
        else{
            throw new ResourceNotFoundException("No existe un odontologo con la matricula: " + odontologo.getMatricula());
        }
    }

    @Override
    public OdontologoDto eliminarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Optional<Odontologo>  odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            LOGGER.info("Eliminando el odontologo con el id " + id);
            odontologoRepository.deleteById(id);
            return mapper.convertValue(odontologo, OdontologoDto.class);
        }
        else{
            throw new ResourceNotFoundException("No existe un odontologo con el id: " + id);
        }
    }
}
