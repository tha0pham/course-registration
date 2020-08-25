/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Clazz;
import entity.Major;
import entity.Registration;
import entity.Semester;
import entity.Student;
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
public class RegistrationDAO extends JPAResource {

    //register
    public void register(int studentId, int clazzId) {
        EntityManager em = this.getEMF().createEntityManager();
        Registration newRegistration = new Registration(studentId, clazzId);
        try {
            em.getTransaction().begin();
            em.persist(newRegistration);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //unregister
    public void unregister(int studentId, int clazzId) {
        EntityManager em = this.getEMF().createEntityManager();
        Registration registration = this.findRegistration(studentId, clazzId);
        try {
            em.getTransaction().begin();
            registration = em.merge(registration);
            em.remove(registration);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //find registration
    public Registration findRegistration(int studentId, int clazzId) {
        EntityManager em = this.getEMF().createEntityManager();
        Registration registration = null;
        try {
            String sql = "SELECT r FROM Registration r WHERE r.registrationPK.stuId = ?1 "
                    + "and r.registrationPK.claId = ?2";
            Query query = em.createQuery(sql);
            query.setParameter(1, studentId);
            query.setParameter(2, clazzId);
            registration = (Registration) query.getSingleResult();
        } catch (NoResultException e) {
        } finally {
            em.close();
        }
        return registration;
    }

    //classes offered
    public List<Clazz> classesOffered(Student student, Semester semester, Year year) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Clazz> clazzes = null;
        try {
            String sql = "select c from Clazz c where c.semester = ?1 and c.year = ?2 "
                    + "and c not in (select r.clazz from Registration r where r.student = ?3)";
            Query query = em.createQuery(sql);
            query.setParameter(1, semester);
            query.setParameter(2, year);
            query.setParameter(3, student);
            clazzes = query.getResultList();
        } finally {
            em.close();
        }
        return clazzes;
    }

    //classes registered
    public List<Clazz> classesRegistered(Student student, Semester semester, Year year) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Clazz> clazzes = null;
        try {
            String sql = "select c from Clazz c, Registration r where  c.semester = ?1 and c.year = ?2 and c = r.clazz and r.student = ?3";
            Query query = em.createQuery(sql);
            query.setParameter(1, semester);
            query.setParameter(2, year);
            query.setParameter(3, student);
            clazzes = query.getResultList();
        } finally {
            em.close();
        }
        return clazzes;
    }

    // get student list in class
    public List<Student> getStudentList(int clazzId) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Student> students = null;
        try {
            String sql = "select c from Student c, Registration r where r.registrationPK.claId = ?1"
                    + " and c.id = r.registrationPK.stuId";
            Query query = em.createQuery(sql);
            query.setParameter(1, clazzId);
            students = query.getResultList();
        } finally {
            em.close();
        }
        return students;
    }
    
    public List<Student> getStudentList(Clazz clazz) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Student> students = null;
        try {
            String sql = "select c from Student c, Registration r where r.registrationPK.claId = ?1"
                    + " and c.id = r.registrationPK.stuId";
            Query query = em.createQuery(sql);
            query.setParameter(1, clazz.getId());
            students = query.getResultList();
        } finally {
            em.close();
        }
        return students;
    }

    //update scores
    public void updateScore(Registration reg) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(reg);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //calculate average grade
    public double calcAverage(Registration reg) {
        double average = (reg.getMidterm() * reg.getClazz().getMidterm() / 100
                + reg.getFinalExam() * reg.getClazz().getFinalExam() / 100
                + reg.getAssignment() * reg.getClazz().getAssignment() / 100);
        reg.setAverage(average);
        return average;
    }

    //determine grade
    public char determineGrade(Registration reg) {
        double average = calcAverage(reg);
        if (average < 60) {
            return 'F';
        } else if (average < 70) {
            return 'D';
        } else if (average < 80) {
            return 'C';
        } else if (average < 90) {
            return 'B';
        } else {
            return 'A';
        }
    }

    public boolean isPassed(Registration reg) {
        return calcAverage(reg) >= reg.getClazz().getCourse().getPassingScore();
    }
}
