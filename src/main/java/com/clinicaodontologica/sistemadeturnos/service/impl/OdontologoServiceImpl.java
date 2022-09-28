package com.clinicaodontologica.sistemadeturnos.service.impl;

import com.clinicaodontologica.sistemadeturnos.dto.OdontologoDto;
import com.clinicaodontologica.sistemadeturnos.entity.Odontologo;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;
import com.clinicaodontologica.sistemadeturnos.repository.IOdontologoRepository;
import com.clinicaodontologica.sistemadeturnos.service.IOdontologoService;
import com.fasterxml.jackson.databind.JsonMappingException;
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
            Optional<Odontologo> odontologoConsultado = odontologoRepository.findByMatricula(odontologo.getMatricula());
            if (!odontologoConsultado.isPresent()){
                odontologoRepository.save( mapper.convertValue(odontologo, Odontologo.class));
                LOGGER.info("Se guardo correctamente el odontologo con matricula " + odontologo.getMatricula());
                return odontologo;
            }else {
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
        LOGGER.info("Listando todos los odontologos");
        return odontologosDto;
    }

    @Override
    public OdontologoDto modificarOdontologo(OdontologoDto odontologoDto) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoModificar = odontologoRepository.findByMatricula(odontologoDto.getMatricula());
        if(odontologoModificar.isPresent()){
            Odontologo odontologoEntity = mapper.convertValue(odontologoDto, Odontologo.class);
            odontologoModificar.map(odontologo -> {
                odontologo.setNombre(odontologoEntity.getNombre());
                odontologo.setApellido(odontologoEntity.getApellido());
                return odontologoRepository.save(odontologo);
            });
            LOGGER.info("Modicando el odontologo con matricula " + odontologoDto.getMatricula());
            return odontologoDto;
        }else{
            throw new ResourceNotFoundException("No existe un odontologo con la matricula: " + odontologoDto.getMatricula());
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
