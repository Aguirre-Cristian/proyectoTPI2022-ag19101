/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baches.configuration;

import com.mycompany.baches.control.tipoObjetoBean;
import com.mycompany.baches.entity.resources.TipoObjeto;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author crisagui
 */

@Path("tipoobjeto")
@RequestScoped

public class tipoObjetoResource {
    @Inject
    tipoObjetoBean toBean;

    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Response findAll() {
        List<TipoObjeto> registros = toBean.findAll();
        Long total = toBean.contar();
        return Response.ok(registros)
                .header("Total-Registros", total)
                .build();
    }
    @GET
    @Path("contar")
    public CompletableFuture<Long> contar(){
        return CompletableFuture.supplyAsync(toBean::contar); 
    } 
}
