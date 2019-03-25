package com.oalejandro.bdirservices.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "Id del cliente generado de la base de datos.", hidden = true)
    private Long id;
    @ApiModelProperty(notes = "Nombre del cliente.", required = true)
    private String nombre;
    @ApiModelProperty(notes = "Apellido del cliente.", required = true)
    private String apellido;
    @ApiModelProperty(notes = "Edad del cliente.", required = true)
    private int edad;
    @ApiModelProperty(notes = "Fecha de nacimiento del cliente.", required = true, dataType = "date", example = "YYYY-MM-DD")
    private String fechaNacimiento;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, int edad, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
