package com.clinicaodontologica.sistemadeturnos.exception;

public class DuplicateResourceException extends Exception{

    public DuplicateResourceException(String mensaje) {
        super(mensaje);
    }
}
