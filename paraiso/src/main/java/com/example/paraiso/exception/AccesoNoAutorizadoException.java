package com.example.paraiso.exception;

public class AccesoNoAutorizadoException extends RuntimeException {
    public AccesoNoAutorizadoException(String mensaje) {
        super(mensaje);
    }
}