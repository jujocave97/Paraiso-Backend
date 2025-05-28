package com.example.paraiso.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservaNoPendienteException.class)
    public ResponseEntity<String> handleReservaNoPendiente(ReservaNoPendienteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Manejo de errores de validación en DTOs con @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    // Para validaciones a nivel de parámetros (por ejemplo @RequestParam o @PathVariable)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> {
            String campo = error.getPropertyPath().toString();
            errores.put(campo, error.getMessage());
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    // Otras excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralErrors(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "⚠️ Error interno: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(CakeNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCakeNoEncontrado(CakeNoEncontradoException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(ReservaNoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleReservaNoEncontrada(ReservaNoEncontradaException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(AccesoNoAutorizadoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleAccesoNoAutorizado(AccesoNoAutorizadoException ex) {
        return Map.of("error", ex.getMessage());
    }


    @ExceptionHandler(UsuarioYaExistenteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleUsuarioYaExiste(UsuarioYaExistenteException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(FormatoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleFormatoInvalido(FormatoInvalidoException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(DatosInvalidosException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleDatosInvalidos(DatosInvalidosException ex) {
        return Map.of("error", ex.getMessage());
    }

}
