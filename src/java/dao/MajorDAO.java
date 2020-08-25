/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Course;
import entity.Faculty;
import entity.Major;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import resource.JPAResource;

/**
 *
 * @author Thao
 */
public class MajorDAO extends JPAResource {

    //list all majors
    public List<Major> findAllMajors() {
        EntityManager em = this.getEMF().createEntityManager();
        List<Major> majors = null;
        try {
            String sql = "select m from Major m";
            Query query = em.createQuery(sql);
            majors = query.getResultList();
            return majors;
        } finally {
            em.close();
        }
    }
    
    //find major by id  
    public Major findMajorByID(int id) {
        Major major;
        EntityManager em = this.getEMF().createEntityManager();
        major = em.find(Major.class, id);
        return major;
    }

    //find major by name
    public Major findMajorByName(String name) {
        EntityManager em = this.getEMF().createEntityManager();
        Major major;
        try {
            String sql = "select m from Major m where m.name=?1";
            Query query = em.createQuery(sql);
            query.setParameter(1, name);
            major = (Major) query.getSingleResult();
            return major;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // search functions
    public List<Major> search(String keyword) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Major> majors;
        try {
            String sql = "select m from Major m where m.name like ?1 or m.description like ?2";
            Query query = em.createQuery(sql);
            query.setParameter(1, "%" + keyword + "%");
            query.setParameter(2, "%" + keyword + "%");
            majors = query.getResultList();
            return majors;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    //add new major
    public void addMajor(Major major) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(major);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //update a major
    public void updateMajor(Major major) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(major);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //delete a major
    public void deleteMajor(Major major) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            major = em.merge(major);
            em.remove(major);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //constructor
    public MajorDAO() {
    }
}
