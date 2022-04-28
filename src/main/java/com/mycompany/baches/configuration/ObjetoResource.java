/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baches.configuration;

import com.mycompany.baches.control.ObjetoBean;
import com.mycompany.baches.entity.resources.Objeto;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author crisagui
 */
@Path("objeto")
@RequestScoped
public class ObjetoResource  {
    @Inject
    ObjetoBean toBean;
    
    @GET
    @Path("nombre")
    public Response buscarPorNombre(String nombre){
        toBean.buscarPorNombre(nombre);
        List<Objeto> registros = toBean.buscarPorNombre(nombre);
           return Response.ok(nombre)
                .build();
    }
}
