package com.example.paraiso.dto;

public class SignUpDTO {
    private String nombre;
    private String apellidos;
    private String password;
    private String telefono;
    private String email;
    private String rol;

    public SignUpDTO(String nombre, String apellidos,String password, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.telefono = telefono;
        this.email = email;
    }

    public SignUpDTO(String nombre, String apellidos,String password, String telefono, String email, String rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password=password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
