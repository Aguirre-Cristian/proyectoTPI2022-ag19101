/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baches.control;

import com.mycompany.baches.entity.resources.Objeto;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author crisagui
 */
@Stateless
@LocalBean

public class ObjetoBean extends AbstractDataAcces<Objeto> implements Serializable {

    @PersistenceContext(unitName ="Baches_PU")
    EntityManager em;

    @Override
    EntityManager getEntityManager() {
        return em;
    }

    public ObjetoBean() {
        super(Objeto.class);
    }
    //prepared statement, sql injection NamedQuery
    public List<Objeto> findByIdTipoObjeto(final Integer idTipoObjeto, int first, int pageSize){
        if(this.em != null && idTipoObjeto !=null){
            Query q = em.createNamedQuery("Objeto.findByTipoObjeto");
            q.setParameter("idTipoObjeto", idTipoObjeto);
            q.setFirstResult(first);
            q.setMaxResults(pageSize);
            return q.getResultList();
        }
        return Collections.EMPTY_LIST;
    }
    
    public int countByIdTipoObjeto(final Integer idTipoObjeto){
        if(this.em !=null && idTipoObjeto !=null){
            Query q = em.createNamedQuery("Objeto.countByTipoObjeto");
            q.setParameter("idTipoObjeto", idTipoObjeto);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }
    
    public List<Objeto> buscarPorNombre(String nombre){
        Query q = em.createNamedQuery("Objeto.findByNombre");
        q.setParameter("nombre", nombre);
        return q.getResultList();
    }
}
