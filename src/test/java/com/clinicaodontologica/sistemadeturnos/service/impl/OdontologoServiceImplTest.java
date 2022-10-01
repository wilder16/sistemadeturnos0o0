package com.clinicaodontologica.sistemadeturnos.service.impl;

import com.clinicaodontologica.sistemadeturnos.dto.OdontologoDto;
import com.clinicaodontologica.sistemadeturnos.entity.Odontologo;
import com.clinicaodontologica.sistemadeturnos.exception.DuplicateResourceException;
import com.clinicaodontologica.sistemadeturnos.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Optional;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceImplTest {

    @Autowired
    OdontologoServiceImpl odontologoService;

    @Autowired
    ObjectMapper mapper;


    @Test
    @Order(1)
    public void crearOdontologo() throws DuplicateResourceException {
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNombre("Diana");
        odontologoDto.setApellido("Fernandez");
        odontologoDto.setMatricula("159753A");
        Odontologo odontologoGuardado = mapper.convertValue(odontologoService.agregarOdontologo(odontologoDto), Odontologo.class);

        Assertions.assertEquals(odontologoGuardado.getMatricula(), "159753A");

    }

    @Test
    @Order(2)
    public void consultarOdologo() throws ResourceNotFoundException {
        OdontologoDto odontologoDto = odontologoService.obtenerOdontologoPorId(1L);
        Assertions.assertNotNull(odontologoDto);
    }

    @Test
    @Order(3)
    public void listarOdontologo(){
        Collection<OdontologoDto> odontologos = odontologoService.listarOdontologo();
        Assertions.assertNotNull(odontologos);
    }

    @Test
    @Order(4)
    public void modificarOdontolgo() throws ResourceNotFoundException {
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNombre("Diana W");
        odontologoDto.setApellido("Fernandez Agilar");
        odontologoDto.setMatricula("159753A");
        Odontologo odontologoModificado = mapper.convertValue(odontologoService.modificarOdontologo(odontologoDto), Odontologo.class);
        Assertions.assertEquals(odontologoModificado.getNombre(), "Diana W");
    }

    @Test
    @Order(5)
    public void eliminarOdontologo() throws ResourceNotFoundException {
        Optional<OdontologoDto> odontologoDto = Optional.ofNullable(odontologoService.eliminarOdontologoPorId(1L));

       try {
           odontologoDto = Optional.ofNullable(odontologoService.obtenerOdontologoPorId(1l));
       }catch (ResourceNotFoundException e ){
           String mensaje = "No existe un odontologo con el id: 1";
           Assertions.assertEquals(mensaje, "No existe un odontologo con el id: 1");
       }

    }




}
