package com.example.paraiso.exception;

public class ReservaNoEncontradaException extends RuntimeException {
    public ReservaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}