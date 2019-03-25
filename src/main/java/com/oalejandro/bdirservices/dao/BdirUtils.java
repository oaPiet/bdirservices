package com.oalejandro.bdirservices.dao;

import com.oalejandro.bdirservices.model.*;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public final class BdirUtils {

    /**
     * Calcular KPI's a partir de una lista de clientes
     * @param listaClientes lista de clientes en BD
     * @return Información de respuesta
     */
    public static KpiDeClientesResponse calcularKpiDeClientes (@NotNull List<Cliente> listaClientes) {
        KpiDeClientesResponse kpiDeClientesResponse = new KpiDeClientesResponse();
        if(!listaClientes.isEmpty()) calcularKpiValido(listaClientes, kpiDeClientesResponse);
        else kpiDeClientesResponse.setMensaje("No se encontraron clientes.");
        return kpiDeClientesResponse;
    }

    /**
     * Obtener el promedio y desviación estándar de los clientes
     * @param listaClientes lista de clientes en BD
     * @param kpiDeClientesResponse Objeto de respuesta
     * @return Estructura de respuesta al cálculo de promedio y desviación estándar.
     */
    private static KpiDeClientesResponse calcularKpiValido(@NotNull List<Cliente> listaClientes, KpiDeClientesResponse kpiDeClientesResponse) {
        double sumatoriaEdades = 0.0;
        double desviavionEstandar = 0.0;

        for (Cliente cliente : listaClientes) { sumatoriaEdades += cliente.getEdad();}
        for (Cliente cliente : listaClientes) { desviavionEstandar += Math.pow(cliente.getEdad() - sumatoriaEdades/listaClientes.size(), 2);}

        kpiDeClientesResponse.setPromedioEdadClientes(sumatoriaEdades/listaClientes.size());
        kpiDeClientesResponse.setDesviacionEdadClientes(desviavionEstandar);
        kpiDeClientesResponse.setMensaje("Cálculo de KPI's de clientes correcto");

        return kpiDeClientesResponse;
    }

    /**
     * Listar la información de los clientes y su fecha probable de muerte
     * @param listaClientes lista de clientes en BD
     * @return Información de respuesta
     */
    public static ListarClientesResponse listarInformacionClientes(List<Cliente> listaClientes) {
        ListarClientesResponse listarClientesResponse = new ListarClientesResponse();
        if(!listaClientes.isEmpty()) informacionValidaDeClientes(listaClientes, listarClientesResponse);
        else listarClientesResponse.setMensaje("No se encontraron clientes.");

        return listarClientesResponse;
    }


    /**
     * Obtener información de clientes
     * @param listaClientes lista de clientes en BD
     * @param listarClientesResponse Objeto de respuesta
     * @return Estructura de respuesta al cálculo de fecha probable de muerte.
     */
    public static ListarClientesResponse informacionValidaDeClientes(List<Cliente> listaClientes, ListarClientesResponse listarClientesResponse) {
        List<ClientesResponse> clientesResponseList = new ArrayList<>();
        for (Cliente cliente: listaClientes) {
            ClientesResponse clientesResponse = new ClientesResponse();
            clientesResponse.setCliente(cliente);
            //Edad promedio de mortalidad en el Perú -> 74 años
            Calendar fechaNacimiento = new GregorianCalendar(Integer.valueOf(cliente.getFechaNacimiento().split("-")[0]), Integer.valueOf(cliente.getFechaNacimiento().split("-")[1]), Integer.valueOf(cliente.getFechaNacimiento().split("-")[2]));
            fechaNacimiento.add(Calendar.YEAR, 74);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            clientesResponse.setFechaProbableMuerte(sdf.format(fechaNacimiento.getTime()));
            clientesResponseList.add(clientesResponse);
        }

        listarClientesResponse.setClientesResponseList(clientesResponseList);
        listarClientesResponse.setMensaje("Recuperación de clientes exitosa");

        return listarClientesResponse;
    }

    /**
     *
     * @param cliente Información del cliente
     * @return Mensaje de registro de usuario
     */
    public static CrearClienteResponse registrarClientes (@NotNull Cliente cliente) {
        CrearClienteResponse crearClienteResponse = new CrearClienteResponse();
        if(validarDatosUsuario(cliente)) validarFechaEdad(cliente, crearClienteResponse);
        else crearClienteResponse.setMensaje("No se pudo registrar el cliente.");
        return crearClienteResponse;
    }

    /**
     *
     * @param cliente Información del cliente
     * @param crearClienteResponse Objeto de respuesta
     * @return Estructura de respuesta a la validación de edad y fecha
     */
    private static CrearClienteResponse validarFechaEdad(@NotNull Cliente cliente, CrearClienteResponse crearClienteResponse) {
        String[] fraccionFechaNacimiento = cliente.getFechaNacimiento().split("-");
        LocalDate hoy = LocalDate.now();
        LocalDate fechaNacimiento = LocalDate.of(Integer.valueOf(fraccionFechaNacimiento[0]), Integer.valueOf(fraccionFechaNacimiento[1]), Integer.valueOf(fraccionFechaNacimiento[2]));  //Birth date
        Period period = Period.between(fechaNacimiento, hoy);

        if(period.getYears() != cliente.getEdad())  crearClienteResponse.setMensaje("La edad del cliente no concuerda con el cálculo a partir de la fecha de nacimiento brindada.");
        else crearClienteResponse.setMensaje("Usuario creado.");

        return crearClienteResponse;
    }

    /**
     * Validar que se introducieron datos correctos
     * @param cliente Información del cliente
     * @return Validación
     */
    private static boolean validarDatosUsuario(@NotNull Cliente cliente) {
        return !StringUtils.isEmpty(cliente.getNombre()) && !StringUtils.isEmpty(cliente.getApellido()) && cliente.getEdad() > 0 && !StringUtils.isEmpty(cliente.getFechaNacimiento());
    }
}
