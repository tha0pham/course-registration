/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Semester;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import resource.JPAResource;

/**
 *
 * @author Thao
 */
public class SemesterDAO extends JPAResource {

    //list all semesters
    public List<Semester> findAllSemesters() {
        EntityManager em = this.getEMF().createEntityManager();
        List<Semester> semesters = null;
        try {
            String sql = "select m from Semester m";
            Query query = em.createQuery(sql);
            semesters = query.getResultList();
            return semesters;
        } finally {
            em.close();
        }
    }
    
    //find semester by id  
    public Semester findSemesterByID(int id) {
        Semester semester;
        EntityManager em = this.getEMF().createEntityManager();
        semester = em.find(Semester.class, id);
        return semester;
    }

    //find semester by name
    public Semester findSemesterByName(String name) {
        EntityManager em = this.getEMF().createEntityManager();
        Semester semester;
        try {
            String sql = "select m from Semester m where m.name=?1";
            Query query = em.createQuery(sql);
            query.setParameter(1, name);
            semester = (Semester) query.getSingleResult();
            return semester;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    //add new semester
    public void addSemester(Semester semester) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(semester);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //update a semester
    public void updateSemester(Semester semester) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(semester);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //delete a semester
    public void deleteSemester(Semester semester) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            semester = em.merge(semester);
            em.remove(semester);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //constructor
    public SemesterDAO() {
    }
}
