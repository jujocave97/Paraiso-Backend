package com.example.paraiso.dto;

import java.time.LocalDateTime;

public class ReservaCakeDTO {
    private String id;
    private String usuario;
    private String cakeId;
    private int cantidad;
    private String estado;
    private LocalDateTime fechaReserva;
    private String comentario;
    public ReservaCakeDTO() {

    }

    public ReservaCakeDTO(String cakeId, int cantidad) {
        this.cakeId = cakeId;
        this.cantidad = cantidad;
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
