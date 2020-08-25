/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Student;
import java.io.File;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import resource.JPAResource;

/**
 *
 * @author Thao
 */
public class StudentDAO extends JPAResource {

    //list all students
    public List<Student> findAllStudents() {
        EntityManager em = this.getEMF().createEntityManager();
        List<Student> students = null;
        try {
            String sql = "select m from Student m";
            Query query = em.createQuery(sql);
            students = query.getResultList();
            return students;
        } finally {
            em.close();
        }
    }

    //list all students by first name and then last name
    public List<Student> listStudentsByName() {
        EntityManager em = this.getEMF().createEntityManager();
        List<Student> students = null;
        try {
            String sql = "select m from Student m order by m.firstName, m.lastName";
            Query query = em.createQuery(sql);
            students = query.getResultList();
            return students;
        } finally {
            em.close();
        }
    }

    //find student by id  

    public Student findStudentByID(int id) {
        Student student;
        EntityManager em = this.getEMF().createEntityManager();
        student = em.find(Student.class, id);
        return student;
    }

    //find student by code
    public Student findStudentByCode(String code) {
        EntityManager em = this.getEMF().createEntityManager();
        Student student;
        try {
            String sql = "select m from Student m where m.code = ?1";
            Query query = em.createQuery(sql);
            query.setParameter(1, code);
            student = (Student) query.getSingleResult();
            return student;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    //find student by PID
    public Student findStudentByPID(String pid) {
        EntityManager em = this.getEMF().createEntityManager();
        Student student;
        try {
            String sql = "select m from Student m where m.pid = ?1";
            Query query = em.createQuery(sql);
            query.setParameter(1, pid);
            student = (Student) query.getSingleResult();
            return student;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // search functions
    public List<Student> search(String keyword) {
        EntityManager em = this.getEMF().createEntityManager();
        List<Student> students;
        try {
            String sql = "select m from Student m where m.lastName like ?1 or "
                    + "m.firstName like ?2 or m.code like ?3 or m.email like ?4";
            Query query = em.createQuery(sql);
            query.setParameter(1, "%" + keyword + "%");
            query.setParameter(2, "%" + keyword + "%");
            query.setParameter(3, "%" + keyword + "%");
            query.setParameter(4, "%" + keyword + "%");
            students = query.getResultList();
            return students;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List getStudents(int max, int index) {
        EntityManager em = this.getEMF().createEntityManager();
        Query query = em.createQuery("SELECT c FROM Student c order by c.firstName, c.lastName");
        return query.setMaxResults(max).
                setFirstResult(index).
                getResultList( );
    }
    
    //add new student
    public void addStudent(Student student) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //update a student
    public void updateStudent(Student student) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //delete a student
    public void deleteStudent(Student student) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            student = em.merge(student);
            em.remove(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

	//create a bar code
	public String createBarCode(String studentCode, String path) throws BarcodeException {
		String filename = studentCode + "-Code" + ".jpg";
		Barcode barCode = BarcodeFactory.createCode128(studentCode);
		barCode.setBarWidth(1);
		barCode.setBarHeight(50);
		barCode.setDrawingText(false);
		
		File file = new File(path + File.separator + filename);
		try {
			BarcodeImageHandler.saveJPEG(barCode, file);
			System.out.print(file.getName());
			return file.getName();
		} catch (Exception e) {
			System.out.print(e);
			return null;
		}	
	}
	
    //constructor
    public StudentDAO() {
    }
}
