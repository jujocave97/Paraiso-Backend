package com.example.paraiso.dto;

public class ReservaCakeDTO {
    private String usuario;
    private String cake;
    private int cantidad;

    public ReservaCakeDTO(String usuario, String cake, int cantidad) {
        this.usuario = usuario;
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
}
