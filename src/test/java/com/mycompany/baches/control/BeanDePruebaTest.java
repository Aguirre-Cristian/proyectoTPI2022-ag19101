/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.baches.control;

import javax.ejb.embeddable.EJBContainer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author crisagui
 */
public class BeanDePruebaTest {
    
    public BeanDePruebaTest() {
    }

    /**
     * Test of getNombreUsuario method, of class BeanDePrueba.
     */
    @Test
    public void testGetNombreUsuario() throws Exception {
        System.out.println("getNombreUsuario");
        Integer idUsuario = 1;
        BeanDePrueba cut = new BeanDePrueba();
        String expResult = BeanDePrueba.NOMBRE_USUARIO;
        String result = cut.getNombreUsuario(idUsuario);
        assertEquals(expResult,result);
    }
    
}
