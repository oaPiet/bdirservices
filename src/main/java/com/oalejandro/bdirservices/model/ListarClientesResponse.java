package com.oalejandro.bdirservices.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class ListarClientesResponse implements Serializable {
    @ApiModelProperty(notes = "Lista de clientes")
    private List<ClientesResponse> clientesResponseList;
    @ApiModelProperty(notes = "Mensaje resultado de la operación")
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ListarClientesResponse() {
    }

    public ListarClientesResponse(List<ClientesResponse> clientesResponseList, String mensaje) {
        this.clientesResponseList = clientesResponseList;
        this.mensaje = mensaje;
    }

    public List<ClientesResponse> getClientesResponseList() {
        return clientesResponseList;
    }

    public void setClientesResponseList(List<ClientesResponse> clientesResponseList) {
        this.clientesResponseList = clientesResponseList;
    }
}
