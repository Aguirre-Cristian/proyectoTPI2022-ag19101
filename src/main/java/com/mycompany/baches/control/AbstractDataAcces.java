/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baches.control;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author crisagui
 */
public abstract class AbstractDataAcces<T> implements Serializable {

    protected final Class clase;

    public AbstractDataAcces(Class clase) {
        this.clase = clase;
    }

    abstract EntityManager getEntityManager();

    /**
     *
     * @param nuevo
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    public void crear(T nuevo) throws IllegalArgumentException, IllegalStateException {
        if (nuevo != null) {
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception ex) {
                throw new IllegalStateException("No se puede obtener un ambito de persistencia");
            }
            if (em != null) {
                em.persist(nuevo);
                return;
            } else {
                throw new IllegalStateException("No se puede almacenar el registro");
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Buscar por id
     *
     * @param id
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    public T findById(Object id) throws IllegalArgumentException, IllegalStateException {
        if (id != null) {
            EntityManager em = null;
            try {
                em = this.getEntityManager();
            } catch (Exception ex) {
            }
            if (em != null) {
                return (T) em.find(clase, id);
            }
            throw new IllegalStateException("No se puede obtener un ambito de persistencia");
        }
        throw new IllegalArgumentException();
    }

    /**
     *
     * @return
     */
    public List<T> findAll() {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception ex) {
        }
        if (em != null) {
            TypedQuery q = this.generarConsultaBase(em);
            List salida = q.getResultList();
            if(salida!=null){
                return salida;
            }
            return Collections.EMPTY_LIST;
        }
        throw new IllegalStateException("No se puede obtener un ambito de persistencia");
    }

    /**
     *
     * @param first
     * @param pageSize
     * @return
     */
    public List<T> findRange(int first, int pageSize) {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception ex) {

        }
        if (em != null) {
            TypedQuery tq = this.generarConsultaBase(em);
            tq.setFirstResult(first);
            tq.setMaxResults(pageSize);
            List salida = tq.getResultList();
            if (salida != null) {
                return salida;
            }
            return Collections.EMPTY_LIST;
        }
        throw new IllegalStateException("No se puede obtener un ambito de persistencia");
    }

    protected TypedQuery generarConsultaBase(EntityManager em) {
        if (em != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(clase);
            Root<T> raiz = cq.from(clase);
            cq.select(raiz);
            return em.createQuery(cq);
        }
        throw new IllegalArgumentException();
    }

    /**
     *
     * @return @throws IllegalStateException
     */
    public Long contar() throws IllegalStateException {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception ex) {
        }

        if (em != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            cq.select(cb.count(cq.from(clase)));
            return em.createQuery(cq).getSingleResult();
        }
        throw new IllegalStateException();

    }

    /**
     * Eliminar por id
     *
     * @param id
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     */
    public void eliminar(T id) throws IllegalStateException, IllegalArgumentException {
        if (id != null) {
            EntityManager em = null;
            try {
                em = this.getEntityManager();
            } catch (Exception ex) {
            }
            if (em != null) {

                if (!em.contains(id)) {
                    id = em.merge(id);
                }
                em.remove(id);
                return;
            }

            throw new IllegalStateException("El entity manager era nulo");

        }
        throw new IllegalArgumentException("El objeto a eliminar es nulo");

    }

    /**
     *
     * @param nuevo
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    public void actualizar(T nuevo) throws IllegalArgumentException, IllegalStateException {
        if (nuevo != null) {
            EntityManager em = null;
            try {
                em = this.getEntityManager();
            } catch (Exception e) {
                throw new IllegalStateException("No se puede obtener un ambito de persistencia");
            }
            if (em != null) {
                em.merge(nuevo);
                return;
            } else {
                throw new IllegalStateException("No se pudo modificar el registro");
            }
        }
        throw new IllegalArgumentException();

    }
}
