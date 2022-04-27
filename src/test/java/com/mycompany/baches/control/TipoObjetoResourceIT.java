/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baches.control;

import java.io.StringReader;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.mycompany.baches.configuration.JakartaRestConfiguration;
import com.mycompany.baches.configuration.tipoObjetoResource;
import com.mycompany.baches.entity.resources.TipoObjeto;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author crisagui
 */
@ExtendWith(ArquillianExtension.class)
public class TipoObjetoResourceIT {

    @Deployment
    public static WebArchive crearDespliegue() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.mycompany.baches.entity.resources")
                .addAsResource("persistence-arquillian.xml")
                .addClass(AbstractDataAcces.class)
                .addClass(tipoObjetoBean.class)
                .addClass(JakartaRestConfiguration.class)
                .addClass(tipoObjetoResource.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/sql/datos.sql", "META-INF/sql/datos.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(salida.toString(true));
        return salida;
    }

    @ArquillianResource
    URL url;

    @Test
    @RunAsClient
    public void testFindAll() {
        System.out.println("\n\n");
        System.out.println("findAllTipoObjeto");
        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("tipoobjeto").request("application/json").get();
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String totalTexto = respuesta.getHeaderString("Total-Registros");
        assertNotEquals(Integer.valueOf(0), Integer.valueOf(totalTexto));
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonArray listaJson = lector.readArray();
        int totalRegistros = listaJson.size();
        assertTrue(totalRegistros > 0);
        System.out.println("\n\n");
        for (int i = 0; i < listaJson.size(); i++) {
            JsonObject objeto = listaJson.getJsonObject(i);
            System.out.println("ID: " + objeto.getInt("idTipoObjeto"));
        }
        System.out.println("\n\n");
    }
    
    @Test
    @RunAsClient
    public void testEliminar(){
        System.out.println("\n\n");
        System.out.println("Eliminar TipoObjeto");
        TipoObjeto nuevo = new TipoObjeto();
        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("tipoobjeto/3").request("application/json").delete();
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("ID-eliminado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();
        System.out.println("\n\n");
        System.out.println("ID:" + objeto.getInt("idTipoObjeto")+" eliminado con exito");
        System.out.println("\n\n");
    }
    
    @Test
    @RunAsClient
    public void testCrear() {
        System.out.println("\n\n");
        System.out.println("Crear TipoObjeto");
        TipoObjeto nuevo = new TipoObjeto();
        nuevo.setActivo(Boolean.TRUE);

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("tipoobjeto").request("application/json").post(Entity.entity(nuevo, MediaType.APPLICATION_JSON));
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("Registro-Creado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();
        System.out.println("\n\n");
        System.out.println("Creado " + objeto);
        System.out.println("\n\n");

    }
    
    @Test
    @RunAsClient
    public void testModificar() {
        System.out.println("\n\n");
        System.out.println("Modificar TipoObjeto");
        TipoObjeto nuevo = new TipoObjeto();
        nuevo.setIdTipoObjeto(3);
        nuevo.setActivo(Boolean.FALSE);

        int resultadoEsperado = 200;
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target(url.toString() + "resources/");
        Response respuesta = target.path("tipoobjeto").request("application/json").put(Entity.entity(nuevo, MediaType.APPLICATION_JSON));
        assertEquals(resultadoEsperado, respuesta.getStatus());
        String registro = respuesta.getHeaderString("Modificado");
        assertNotEquals(null, registro);
        String cuerpoString = respuesta.readEntity(String.class);
        JsonReader lector = Json.createReader(new StringReader(cuerpoString));
        JsonObject objeto = lector.readObject();
        System.out.println("\n\n");
        System.out.println("Modificado " + objeto);
        System.out.println("\n\n");

    }
}