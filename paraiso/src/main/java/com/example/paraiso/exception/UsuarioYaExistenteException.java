package com.example.paraiso.exception;

public class UsuarioYaExistenteException extends RuntimeException {
    public UsuarioYaExistenteException(String mensaje) {
        super(mensaje);
    }
}