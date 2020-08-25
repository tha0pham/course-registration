/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Year;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import resource.JPAResource;

/**
 *
 * @author Thao
 */
public class YearDAO extends JPAResource {

    //list all years
    public List<Year> findAllYears() {
        EntityManager em = this.getEMF().createEntityManager();
        List<Year> years = null;
        try {
            String sql = "select m from Year m";
            Query query = em.createQuery(sql);
            years = query.getResultList();
            return years;
        } finally {
            em.close();
        }
    }
    
    //find year by id  
    public Year findYearByID(int id) {
        Year year;
        EntityManager em = this.getEMF().createEntityManager();
        year = em.find(Year.class, id);
        return year;
    }

    //find year by name
    public Year findYearByValue(String value) {
        EntityManager em = this.getEMF().createEntityManager();
        Year year;
        try {
            String sql = "select m from Year m where m.value=?1";
            Query query = em.createQuery(sql);
            query.setParameter(1, value);
            year = (Year) query.getSingleResult();
            return year;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    //add new year
    public void addYear(Year year) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(year);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //update a year
    public void updateYear(Year year) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(year);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //delete a year
    public void deleteYear(Year year) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            year = em.merge(year);
            em.remove(year);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //constructor
    public YearDAO() {
    }
}
