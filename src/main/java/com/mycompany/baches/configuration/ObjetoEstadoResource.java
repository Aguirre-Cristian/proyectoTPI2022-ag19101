/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baches.configuration;

import com.mycompany.baches.control.ObjetoEstadoBean;
import com.mycompany.baches.entity.resources.ObjetoEstado;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author crisagui
 */
@Path("ObjetoEstado")
@RequestScoped
public class ObjetoEstadoResource {
    @Inject
    ObjetoEstadoBean toBean;
    
    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Response findAll() {
        List<ObjetoEstado> registros = toBean.findAll();
        Long total = toBean.contar();
        return Response.ok(registros)
                .header("Total-Registros", total)
                .build();
    }

    public Response findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "pagesize")
            @DefaultValue(value = "50") int pageSize) {
        List<ObjetoEstado> registros = toBean.findRange(first, pageSize);
        Long total = toBean.contar();
        return Response.ok(registros)
                .header("Total-Registros", total)
                .build();
    }

    @GET
    @Path("contar")
    public CompletableFuture<Long> contar() {
        return CompletableFuture.supplyAsync(toBean::contar);
    }

    @POST
    public Response Crear(ObjetoEstado nuevo) {
        toBean.crear(nuevo);
        return Response.ok(nuevo)
                .header("Registro-Creado", nuevo)
                .build();
    }

    @PUT
    public Response Modificar(ObjetoEstado modificar) {
        toBean.actualizar(modificar);
        return Response.ok(modificar)
                .header("Modificado", modificar)
                .build();
    }

    @DELETE
    @Path("{idObjetoEstado}")
    public Response Eliminar(@PathParam("idObjetoEstado") long id) {
        ObjetoEstado registro = toBean.findById(id);
        toBean.eliminar(registro);
        return Response.ok(registro)
                .header("ID-eliminado", id)
                .build();
    }
}