package com.oalejandro.bdirservices.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CrearClienteResponse implements Serializable {

    public CrearClienteResponse() {
    }

    @ApiModelProperty(notes = "Mensaje resultado de la operación")
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
