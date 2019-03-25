package com.oalejandro.bdirservices.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ClientesResponse implements Serializable {
    @ApiModelProperty(notes = "Cliente.")
    private Cliente cliente;
    @ApiModelProperty(notes = "Fecha probable de muerte del cliente.", dataType = "date", example = "YYYY-MM-DD")
    private String fechaProbableMuerte;

    public ClientesResponse() {
    }

    public ClientesResponse(Cliente cliente, String fechaProbableMuerte) {
        this.cliente = cliente;
        this.fechaProbableMuerte = fechaProbableMuerte;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaProbableMuerte() {
        return fechaProbableMuerte;
    }

    public void setFechaProbableMuerte(String fechaProbableMuerte) {
        this.fechaProbableMuerte = fechaProbableMuerte;
    }
}
