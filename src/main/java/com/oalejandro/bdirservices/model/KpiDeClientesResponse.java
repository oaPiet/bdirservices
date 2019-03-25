package com.oalejandro.bdirservices.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class KpiDeClientesResponse implements Serializable {
    @ApiModelProperty(notes = "Promedio de edad de los clientes.")
    private double promedioEdadClientes;
    @ApiModelProperty(notes = "Desviación estandar de los edades de los clientes.")
    private double desviacionEdadClientes;
    @ApiModelProperty(notes = "Mensaje resultado de la operación")
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public KpiDeClientesResponse() {
    }

    public KpiDeClientesResponse(double promedioEdadClientes, double desviacionEdadClientes) {
        this.promedioEdadClientes = promedioEdadClientes;
        this.desviacionEdadClientes = desviacionEdadClientes;
    }

    public double getPromedioEdadClientes() {
        return promedioEdadClientes;
    }

    public void setPromedioEdadClientes(double promedioEdadClientes) {
        this.promedioEdadClientes = promedioEdadClientes;
    }

    public double getDesviacionEdadClientes() {
        return desviacionEdadClientes;
    }

    public void setDesviacionEdadClientes(double desviacionEdadClientes) {
        this.desviacionEdadClientes = desviacionEdadClientes;
    }
}
