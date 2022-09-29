package com.clinicaodontologica.sistemadeturnos.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> dupliidadDeRegistro(DuplicateResourceException ex)
    {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity("ExceptionHandler Error " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> noEncontrado(ResourceNotFoundException ex)
    {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity("ExceptionHandler Error " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PastDateException.class)
    public ResponseEntity<?> fechaPasada(PastDateException ex)
    {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity("ExceptionHandler Error " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
