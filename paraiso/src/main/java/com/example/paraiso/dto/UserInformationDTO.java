package com.example.paraiso.dto;

public class UserInformationDTO {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String rol;

    public UserInformationDTO(String email, String telefono, String apellidos, String nombre) {
        this.email = email;
        this.telefono = telefono;
        this.apellidos = apellidos;
        this.nombre = nombre;
    }

    public UserInformationDTO(String nombre, String apellidos, String telefono, String email, String rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
