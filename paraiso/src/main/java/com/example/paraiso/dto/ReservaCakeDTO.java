package com.example.paraiso.dto;

import java.time.LocalDateTime;

public class ReservaCakeDTO {
    private String id;
    private String usuario;
    private String cake;
    private int cantidad;
    private String estado;
    private LocalDateTime fechaReserva;

    public ReservaCakeDTO() {

    }

    public ReservaCakeDTO(String cake, int cantidad) {
        this.cake = cake;
        this.cantidad = cantidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCake() {
        return cake;
    }

    public void setCake(String cake) {
        this.cake = cake;
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
}
