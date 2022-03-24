/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.baches.control;

import com.mycompany.baches.entity.resources.TipoObjeto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

/**
 *
 * @author crisagui
 */
public class tipoObjetoBeanTest {
    
    public tipoObjetoBeanTest() {
    }

    /**
     * Test of crear method, of class tipoObjetoBean.
     */
    @Test
    public void testCrear() throws Exception {
        System.out.println("crear");
        TipoObjeto nuevo = new TipoObjeto();
        tipoObjetoBean cut = new tipoObjetoBean();
        assertThrows(IllegalArgumentException.class, () -> {
            cut.crear(null);
        });
        assertThrows(IllegalStateException.class, () -> {
            cut.crear(nuevo);
        }); 
        EntityManager mocKEN = Mockito.mock(EntityManager.class);
        cut.em=mocKEN;
        cut.crear(nuevo);
        
        tipoObjetoBean espia = Mockito.spy(tipoObjetoBean.class);
        espia.em=mocKEN;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.crear(nuevo);
        } catch (Exception e) {
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
        
    }
    
    /**
     * Test of findById method, of class tipoObjetoBean.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer id=1;
        EntityManager mockEM=Mockito.mock(EntityManager.class);
        
        tipoObjetoBean cut=new tipoObjetoBean();
        TipoObjeto esperado=new TipoObjeto();
        Mockito.when(mockEM.find(TipoObjeto.class, id)).thenReturn(esperado);
        assertThrows(IllegalArgumentException.class, ()->{
           cut.findById(null);
        });
        assertThrows(IllegalStateException.class, ()->{
           cut.findById(id);
        });
        cut.em=mockEM;
        TipoObjeto encontrado=cut.findById(id);
        assertNotNull(encontrado);
        assertEquals(esperado, encontrado);
        
        tipoObjetoBean espia=Mockito.spy(tipoObjetoBean.class);
        espia.em=mockEM;
        
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try{
        espia.findById(id);
        }catch(Exception ex){   
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
    }
    
     /**
     * Test of contar method, of class tipoObjetoBean.
     */
    @org.junit.jupiter.api.Test
    public void testContar() throws Exception {
        System.out.println("contar");
        Long esperado=Long.valueOf(1);
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        CriteriaBuilder mockCB = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery mockCQ = Mockito.mock(CriteriaQuery.class);
        TypedQuery mockTQ = Mockito.mock(TypedQuery.class);
        
        Mockito.when(mockEM.getCriteriaBuilder()).thenReturn(mockCB);
        Mockito.when(mockCB.createQuery(Long.class)).thenReturn(mockCQ);
        Mockito.when(mockEM.createQuery(mockCQ)).thenReturn(mockTQ);
        Mockito.when(mockTQ.getSingleResult()).thenReturn(esperado);
        tipoObjetoBean cut=new tipoObjetoBean();
        
        assertThrows(IllegalStateException.class, () -> {
           cut.contar();
        });
        cut.em=mockEM;
        Long resultado=cut.contar();
        assertNotNull(resultado);
        assertEquals(esperado, resultado);
        try { 
            cut.em=null;
            cut.contar();
            fail("El entityManager es null");
        } catch (Exception e) {
        }
         tipoObjetoBean espia=Mockito.spy(tipoObjetoBean.class);
        espia.em=mockEM;
        
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try{
        espia.contar();
        }catch(Exception ex){   
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
    }
    
    /**
     * Test of eliminar method, of class tipoObjetoBean.
     */
     @Test
    public void testFindRange() throws Exception {
        System.out.println("findRange");
         tipoObjetoBean cut = new tipoObjetoBean();

        assertThrows(IllegalStateException.class, ()->{
            cut.findRange(1, 2);
        });
        
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        CriteriaBuilder mockCB = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery mockCQ = Mockito.mock(CriteriaQuery.class);
        Root mockR = Mockito.mock(Root.class);
        TypedQuery mockTQ = Mockito.mock(TypedQuery.class);
        
        Mockito.when(mockEM.getCriteriaBuilder()).thenReturn(mockCB);
        Mockito.when(mockCB.createQuery(Mockito.any())).thenReturn(mockCQ);
        Mockito.when(mockCQ.from(Object.class)).thenReturn(mockR);
        Mockito.when(mockEM.createQuery(mockCQ)).thenReturn(mockTQ);
        Mockito.when(mockTQ.getResultList()).thenReturn(null);
        
        cut.em = mockEM;
        cut.findRange(1, 2);

        Mockito.when(mockTQ.getResultList()).thenReturn(new ArrayList());
        cut.findRange(1, 2);
        
        tipoObjetoBean espia = Mockito.spy(tipoObjetoBean.class);
        espia.em = mockEM;

        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.findRange(1, 2);
        } catch (Exception e) {
        }
        Mockito.verify(espia, Mockito.times(1)).getEntityManager();
        //fail("The test case is a prototype.");
    }
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
         EntityManager mockEM = Mockito.mock(EntityManager.class);
        CriteriaBuilder mockCB = Mockito.mock(CriteriaBuilder.class);
        CriteriaQuery mockCQ = Mockito.mock(CriteriaQuery.class);
        Root mockR = Mockito.mock(Root.class);
        TypedQuery mockTQ = Mockito.mock(TypedQuery.class);

        Mockito.when(mockEM.getCriteriaBuilder()).thenReturn(mockCB);
        Mockito.when(mockCB.createQuery(Mockito.any())).thenReturn(mockCQ);
        Mockito.when(mockCQ.from(Object.class)).thenReturn(mockR);
        Mockito.when(mockEM.createQuery(mockCQ)).thenReturn(mockTQ);
        Mockito.when(mockTQ.getResultList()).thenReturn(null);

        tipoObjetoBean cut = new tipoObjetoBean();

        assertThrows(IllegalStateException.class, () -> {
            cut.findAll();
        });

        EstadoBean espia = Mockito.spy(EstadoBean.class);
        espia.em = mockEM;

        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.findAll();
        } catch (Exception e) {
        }

        Mockito.verify(espia, Mockito.times(1)).getEntityManager();

        cut.em = mockEM;
        cut.findAll();

        Mockito.when(mockTQ.getResultList()).thenReturn(new ArrayList());
        cut.findAll();
       
        //fail("Esta prueba fallara");
        
    }
    
    @Test
    public void testEliminar() throws Exception {
        System.out.println("eliminar");
        EntityManager mockEM=Mockito.mock(EntityManager.class);
        tipoObjetoBean cut = new tipoObjetoBean();
        cut.em=mockEM;
        TipoObjeto eliminado=new TipoObjeto();
        cut.eliminar(eliminado);
        Mockito.verify(mockEM,Mockito.times(1)).remove(ArgumentMatchers.any());
        try {
            cut.eliminar(null);
            fail("el parametrpo era nulo");
        } catch (IllegalArgumentException ex) {
        }
        
        try {
            cut.em=null;
            cut.eliminar(eliminado);
            fail("el Entitymanager era nulo");
        } catch (IllegalStateException ex) {
        }
        
        tipoObjetoBean espia = Mockito.spy(tipoObjetoBean.class);
        espia.em=mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.eliminar(eliminado);
        } catch (Exception e) {
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
    }
    
    @Test
    public void testActualizar() throws Exception {
        System.out.println("actualizar");
        EntityManager mockEM = Mockito.mock(EntityManager.class);
        
        TipoObjeto nuevo=new TipoObjeto();
        tipoObjetoBean cut=new tipoObjetoBean();
        
        assertThrows(IllegalArgumentException.class, ()->{
            cut.actualizar(null);
        });
        assertThrows(IllegalStateException.class, ()->{
            cut.actualizar(nuevo);
        });

        cut.em=mockEM;
        cut.actualizar(nuevo);

        tipoObjetoBean espia = Mockito.spy(tipoObjetoBean.class);
        espia.em=mockEM;
        Mockito.when(espia.getEntityManager()).thenThrow(NullPointerException.class);
        try {
            espia.actualizar(nuevo);
        } catch (Exception e) {
        }
        Mockito.verify(espia,Mockito.times(1)).getEntityManager();
    }
    
} 
