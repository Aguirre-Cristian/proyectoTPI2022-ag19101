/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baches.control;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
/**
 *
 * @author crisagui
 */
@ExtendWith(ArquillianExtension.class)
public class BeanDePruebaIT {
    
    @Deployment
    public static WebArchive crearDespliegue(){
        WebArchive salida = ShrinkWrap.create(WebArchive.class)
                .addClass(BeanDePrueba.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE,"bean.xml");
        System.out.println(salida.toString(true));
        return salida;
    }
    @Inject
    BeanDePrueba cut;
    @Test
    public void testGetNombreUsuario() throws Exception {
        System.out.println("getNombreUsuario IT");
        assertNotNull(cut);
        String esperado = BeanDePrueba.NOMBRE_USUARIO;
        String resultado = cut.getNombreUsuario(1);
        assertNotNull(resultado);
        assertEquals(esperado, resultado);
    }
}
