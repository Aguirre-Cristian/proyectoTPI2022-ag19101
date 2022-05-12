/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baches.control;

import com.mycompany.baches.entity.resources.ObjetoEstado;
import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author crisagui
 */
@Stateless
@LocalBean
public class ObjetoEstadoBean extends AbstractDataAcces<ObjetoEstado> implements Serializable{
    @PersistenceContext(unitName = "Baches_PU")
    EntityManager em;

    @Override
    EntityManager getEntityManager() {
        return em;
    }

    public ObjetoEstadoBean() {
        super(ObjetoEstado.class);
    }
}
