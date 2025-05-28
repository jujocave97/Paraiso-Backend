package com.example.paraiso.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ReservaCakeDTO {
    private String id;

    //@NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_.-]{3,50}$", message = "Usuario inválido")
    private String usuario;

    //@NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "El ID de la tarta es obligatorio")
    private String cakeId;

    @Min(value = 1, message = "Debe reservar al menos una tarta")
    @Max(value = 3, message = "No puedes reservar más de 3 tartas")
    private int cantidad;

    @Pattern(regexp = "^(PENDIENTE|CONFIRMADA|CANCELADA)?$", message = "Estado inválido")
    private String estado;

    private LocalDateTime fechaReserva;

    @Size(max = 255, message = "El comentario no puede superar los 255 caracteres")
    @Pattern(regexp = "^[^<>]*$", message = "El comentario no puede contener caracteres peligrosos como < o >")
    private String comentario;
    public ReservaCakeDTO() {

    }

    public ReservaCakeDTO(String cakeId, int cantidad) {
        this.cakeId = cakeId;
        this.cantidad = cantidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCakeId(String cakeId) {
        this.cakeId = cakeId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCakeId() {
        return cakeId;
    }

    public void setCake(String cakeId) {
        this.cakeId = cakeId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
