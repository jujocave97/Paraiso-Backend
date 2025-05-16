package com.example.paraiso.dto;

public class CakeDTO {
    private String id;
    private String nombre;
    private String descripcion;

    public CakeDTO(){

    }

    public CakeDTO(String nombre, String descripcion){
        this.nombre=nombre;
        this.descripcion=descripcion;
    }

    public CakeDTO(String id,String nombre, String descripcion){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
