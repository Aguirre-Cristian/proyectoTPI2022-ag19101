/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baches.control;

import com.mycompany.baches.entity.resources.TipoObjeto;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author crisagui
 */
@ExtendWith(ArquillianExtension.class)
public class TipoObjetoIT {

    @Deployment
    public static WebArchive crearDespliegue() {
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.mycompany.baches.entity.resources")
                .addAsResource("persistence-arquillian.xml")
                .addClass(AbstractDataAcces.class)
                .addClass(tipoObjetoBean.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/sql/datos.sql", "META-INF/sql/datos.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(salida.toString(true));
        return salida;
    }

    @Inject
    tipoObjetoBean cut;

    @Test
    public void testContar() throws Exception {
        System.out.println("Contar");
        assertNotNull(cut);
        Long resultado = cut.contar();
        assertNotNull(resultado);
        System.out.println("Se encontraron " + resultado + " registros");
    }

    @Test
    public void testCrear() throws Exception {
        System.out.println("Crear");
        assertNotNull(cut);
        TipoObjeto nuevo = new TipoObjeto();
        nuevo.setActivo(Boolean.TRUE);
        nuevo.setFechaCreacion(new Date());
        cut.crear(nuevo);
        TipoObjeto esperado = cut.findById(4);
        Long contar = cut.contar();
        System.out.println("Total: " + contar);

    }

    @Test
    public void testfindById() {
        System.out.println("findById");
        assertNotNull(cut);
        Integer id = 4;
        TipoObjeto resultado = cut.findById(id);
        System.out.println("Se encontro el resultado " + resultado.getIdTipoObjeto());

    }

    @Test
    public void testActualizar() throws Exception {
        System.out.println("modificar");
        assertNotNull(cut);
        int id = 4;
        TipoObjeto nuevo = new TipoObjeto();
        nuevo.setIdTipoObjeto(id);
        nuevo.setActivo(Boolean.FALSE);
        cut.actualizar(nuevo);
        TipoObjeto modificado = cut.findById(id);
    }
    
    @Test
    public void testFindAll() {
        System.out.println("FindAll");
        Assertions.assertNotNull(cut);
        List<TipoObjeto> resultado = cut.findAll();
        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(!resultado.isEmpty());
        System.out.println("La lista posee "+ resultado.size());

    }

    @Test
    public void testfindRange() {
        System.out.println("findRange");
        assertNotNull(cut);
        int first = 1;
        int pageSize = 3;
        List<TipoObjeto> resultado = cut.findRange(first, pageSize);
        assertNotNull(resultado);
        assertTrue(!resultado.isEmpty());
        System.out.println("Se encontraron " + resultado.size());
    }

    @Test
    public void testEliminar() throws Exception {
        System.out.println("eliminar");
        assertNotNull(cut);
        TipoObjeto eliminado = new TipoObjeto();
        eliminado.setIdTipoObjeto(2);
        cut.eliminar(eliminado);
    }
    
}
