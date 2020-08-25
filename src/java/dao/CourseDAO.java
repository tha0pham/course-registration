/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Course;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import resource.JPAResource;

/**
 *
 * @author Thao
 */
public class CourseDAO extends JPAResource {

    //list all courses
    public List<Course> findAllCourses() {
        EntityManager em = this.getEMF().createEntityManager();
        List<Course> courses = null;
        try {
            String sql = "select m from Course m order by m.code";
            Query query = em.createQuery(sql);
            courses = query.getResultList();
            return courses;
        } finally {
            em.close();
        }
    }
    
    //find course by id  
    public Course findCourseByID(int id) {
        Course course;
        EntityManager em = this.getEMF().createEntityManager();
        course = em.find(Course.class, id);
        return course;
    }

    //find course by code
    public Course findCourseByCode(String code) {
        EntityManager em = this.getEMF().createEntityManager();
        Course course;
        try {
            String sql = "select m from Course m where m.code=?1";
            Query query = em.createQuery(sql);
            query.setParameter(1, code);
            course = (Course) query.getSingleResult();
            return course;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    //find course by name
    public Course findCourseByName(String name) {
        EntityManager em = this.getEMF().createEntityManager();
        Course course;
        try {
            String sql = "select m from Course m where m.name=?1";
            Query query = em.createQuery(sql);
            query.setParameter(1, name);
            course = (Course) query.getSingleResult();
            return course;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // search functions
    public List<Course> search(String keyword) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Course> courses;
        try {
            String sql = "select m from Course m where m.name like ?1 or "
                    + "m.code like ?2";
            Query query = em.createQuery(sql);
            query.setParameter(1, "%" + keyword + "%");
            query.setParameter(2, "%" + keyword + "%");
            courses = query.getResultList();
            return courses;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List getCourses(int max, int index) {
        EntityManager em = this.getEMF().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Course c order by c.name");
        return query.setMaxResults(max).
                setFirstResult(index).
                getResultList( );
    }
    
    //add new course
    public void addCourse(Course course) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //update a course
    public void updateCourse(Course course) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(course);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //delete a course
    public void deleteCourse(Course course) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            course = em.merge(course);
            em.remove(course);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //constructor
    public CourseDAO() {
    }
}
