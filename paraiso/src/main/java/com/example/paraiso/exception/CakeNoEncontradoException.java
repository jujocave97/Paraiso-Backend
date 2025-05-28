package com.example.paraiso.exception;

public class CakeNoEncontradoException extends RuntimeException {
    public CakeNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}