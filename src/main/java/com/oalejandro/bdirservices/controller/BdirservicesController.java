package com.oalejandro.bdirservices.controller;

import com.oalejandro.bdirservices.dao.ClienteRepository;
import com.oalejandro.bdirservices.dao.BdirUtils;
import com.oalejandro.bdirservices.model.Cliente;
import com.oalejandro.bdirservices.model.CrearClienteResponse;
import com.oalejandro.bdirservices.model.KpiDeClientesResponse;
import com.oalejandro.bdirservices.model.ListarClientesResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
@Api(value="clientesIndicadores", description="Indicadores de clientes ")
public class BdirservicesController {
    @Autowired
    private ClienteRepository repository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/creacliente", method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ApiOperation(value = "Crear cliente", response = Object.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Usuario Creado.", response = CrearClienteResponse.class)})
    private CrearClienteResponse nuevoCliente(@RequestBody Cliente nuevoCliente) {
        CrearClienteResponse crearClienteResponse = new CrearClienteResponse();
        crearClienteResponse = BdirUtils.registrarClientes(nuevoCliente);
        if(crearClienteResponse.getMensaje().equals("Usuario creado.")) {repository.save(nuevoCliente);}
        return crearClienteResponse;
    }

    @RequestMapping(value = "/kpideclientes", method = RequestMethod.GET, produces = "application/json;charset=UTF-8" )
    @ApiOperation(value = "KPI de clientes", response = Object.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Cálculo de KPI's de clientes correcto", response = KpiDeClientesResponse.class)})
    private KpiDeClientesResponse kpiDeClientes() {
        return BdirUtils.calcularKpiDeClientes((List<Cliente>) repository.findAll());
    }

    @RequestMapping(value = "/listclientes", method = RequestMethod.GET, produces = "application/json;charset=UTF-8" )
    @ApiOperation(value = "Lista de cliente", response = Object.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Recuperación de clientes exitosa", response = ListarClientesResponse.class)})
    private ListarClientesResponse listClientes() {
        return BdirUtils.listarInformacionClientes((List<Cliente>) repository.findAll());
    }
}
