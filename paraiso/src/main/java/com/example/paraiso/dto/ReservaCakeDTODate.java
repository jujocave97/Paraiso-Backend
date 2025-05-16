package com.example.paraiso.dto;

import java.time.LocalDateTime;

public class ReservaCakeDTODate {
    private String usuario;
    private String cake;
    private int cantidad;
    private LocalDateTime dateTime;
    private String estadoReserva;

    public ReservaCakeDTODate() {
    }

    public ReservaCakeDTODate(String usuario, String cake, int cantidad, LocalDateTime dateTime, String estadoReserva) {
        this.usuario = usuario;
        this.cake = cake;
        this.cantidad = cantidad;
        this.dateTime = dateTime;
        this.estadoReserva = estadoReserva;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
}
