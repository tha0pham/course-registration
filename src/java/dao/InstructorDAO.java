/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Instructor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import resource.JPAResource;

/**
 *
 * @author Thao
 */
public class InstructorDAO extends JPAResource {

    //list all intructors
    public List<Instructor> findAllInstructors() {
        EntityManager em = this.getEMF().createEntityManager();
        List<Instructor> intructors = null;
        try {
            String sql = "select m from Instructor m";
            Query query = em.createQuery(sql);
            intructors = query.getResultList();
            return intructors;
        } finally {
            em.close();
        }
    }

    //list all intructors by first name and then last name
    public List<Instructor> listInstructorsByName() {
        EntityManager em = this.getEMF().createEntityManager();
        List<Instructor> intructors = null;
        try {
            String sql = "select m from Instructor m order by m.firstName, m.lastName";
            Query query = em.createQuery(sql);
            intructors = query.getResultList();
            return intructors;
        } finally {
            em.close();
        }
    }

    //find intructor by id  

    public Instructor findInstructorByID(int id) {
        Instructor intructor;
        EntityManager em = this.getEMF().createEntityManager();
        intructor = em.find(Instructor.class, id);
        return intructor;
    }

    //find intructor by code
    public Instructor findInstructorByCode(String code) {
        EntityManager em = this.getEMF().createEntityManager();
        Instructor intructor;
        try {
            String sql = "select m from Instructor m where m.code = ?1";
            Query query = em.createQuery(sql);
            query.setParameter(1, code);
            intructor = (Instructor) query.getSingleResult();
            return intructor;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // search functions
    public List<Instructor> search(String keyword) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Instructor> instructors;
        try {
            String sql = "select m from Instructor m where m.lastName like ?1 or "
                    + "m.firstName like ?2 or m.code like ?3";
            Query query = em.createQuery(sql);
            query.setParameter(1, "%" + keyword + "%");
            query.setParameter(2, "%" + keyword + "%");
            query.setParameter(3, "%" + keyword + "%");
            instructors = query.getResultList();
            return instructors;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

     public List getInstructors(int max, int index) {
        EntityManager em = this.getEMF().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Instructor c order by c.firstName, c.lastName");
        return query.setMaxResults(max).
                setFirstResult(index).
                getResultList( );
    }
    
    //add new intructor
    public void addInstructor(Instructor intructor) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(intructor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //update a intructor
    public void updateInstructor(Instructor intructor) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(intructor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //delete a intructor
    public void deleteInstructor(Instructor intructor) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            intructor = em.merge(intructor);
            em.remove(intructor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //constructor
    public InstructorDAO() {
    }
}
