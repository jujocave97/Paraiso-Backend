package com.example.paraiso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CakeDTO {
    private String id;
    @NotBlank(message = "El nombre de la tarta es obligatorio")
    @Size(max = 100, message = "El nombre es demasiado largo")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s.,-]+$", message = "Nombre de tarta inválido")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción es demasiado larga")
    @Pattern(regexp = "^[^<>]*$", message = "La descripción contiene caracteres no permitidos")
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
