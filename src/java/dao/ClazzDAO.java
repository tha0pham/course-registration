/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Year;
import entity.Clazz;
import entity.Course;
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
public class ClazzDAO extends JPAResource {

    //list all clazzes
    public List<Clazz> findAllClazzes() {
        EntityManager em = this.getEMF().createEntityManager();
        List<Clazz> clazzes = null;
        try {
            String sql = "select m from Clazz m";
            Query query = em.createQuery(sql);
            clazzes = query.getResultList();
            return clazzes;
        } finally {
            em.close();
        }
    }

    //find clazz by id  
    public Clazz findClazzByID(int id) {
        Clazz clazz;
        EntityManager em = this.getEMF().createEntityManager();
        clazz = em.find(Clazz.class, id);
        return clazz;
    }

    //find clazz by name
    public Clazz findClazzByName(String name) {
        EntityManager em = this.getEMF().createEntityManager();
        Clazz clazz;
        try {
            String sql = "select m from Clazz m where m.name=?1 order by m.name";
            Query query = em.createQuery(sql);
            query.setParameter(1, name);
            clazz = (Clazz) query.getSingleResult();
            return clazz;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    //list clazzes by semester
    public List<Clazz> findClazzBySemester(int semester, int year) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Clazz> clazzes = null;
        try {
            String sql = "select c from Clazz c where c.semester.id = ?1 and c.year.id = ?2";
            Query query = em.createQuery(sql);
            query.setParameter(1, semester);
            query.setParameter(2, year);
            clazzes = query.getResultList();
            return clazzes;
        } finally {
            em.close();
        }
    }
    
    public List<Clazz> findClazzBySemester(Semester semester, Year year) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Clazz> clazzes = null;
        try {
            String sql = "select c from Clazz c where c.semester = ?1 and c.year = ?2";
            Query query = em.createQuery(sql);
            query.setParameter(1, semester);
            query.setParameter(2, year);
            clazzes = query.getResultList();
            return clazzes;
        } finally {
            em.close();
        }
    }

    public List<Clazz> findClazzBySemester(Semester semester, Year year, int max, int index) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            String sql = "select c from Clazz c where c.semester = ?1 and c.year = ?2 order by c.name";
            Query query = em.createQuery(sql);
            query.setParameter(1, semester);
            query.setParameter(2, year);
            return query.setMaxResults(max).
                setFirstResult(index).
                getResultList( );
        } finally {
            em.close();
        }
    }
    
    // search functions
    public List<Clazz> search(String keyword, Semester semester, Year year) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Clazz> clazzes;
        try {
            String sql = "select m from Clazz m where m.name like ?1 "
                    + "and m.semester = ?2 and m.year = ?3";
            Query query = em.createQuery(sql);
            query.setParameter(1, "%" + keyword + "%");
            query.setParameter(2, semester);
            query.setParameter(3, year);
            clazzes = query.getResultList();
            return clazzes;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List getCourses(int max, int index) {
        EntityManager em = this.getEMF().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Clazz c");
        return query.setMaxResults(max).
                setFirstResult(index).
                getResultList( );
    }
    
    //add new clazz
    public void addClazz(Clazz clazz) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(clazz);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //update a clazz
    public void updateClazz(Clazz clazz) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(clazz);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //delete a clazz
    public void deleteClazz(Clazz clazz) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            clazz = em.merge(clazz);
            em.remove(clazz);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //constructor
    public ClazzDAO() {
    }
}
