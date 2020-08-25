/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import resource.JPAResource;

/**
 *
 * @author Thao
 */
public class UserDAO extends JPAResource {

    //list all users
    public List<User> findAllUsers() {
        EntityManager em = this.getEMF().createEntityManager();
        List<User> users = null;
        try {
            String sql = "select m from User m";
            Query query = em.createQuery(sql);
            users = query.getResultList();
            return users;
        } finally {
            em.close();
        }
    }

    //list all users by first name and then last name
    public List<User> listUsersByName() {
        EntityManager em = this.getEMF().createEntityManager();
        List<User> users = null;
        try {
            String sql = "select m from User m order by m.firstName, m.lastName";
            Query query = em.createQuery(sql);
            users = query.getResultList();
            return users;
        } finally {
            em.close();
        }
    }

    //find user by id  
    public User findUserByID(int id) {
        User user;
        EntityManager em = this.getEMF().createEntityManager();
        user = em.find(User.class, id);
        return user;
    }

    //find user by username
    public User findUserByUsername(String usrname) {
        EntityManager em = this.getEMF().createEntityManager();
        User user;
        try {
            String sql = "select m from User m where m.username = ?1";
            Query query = em.createQuery(sql);
            query.setParameter(1, usrname);
            user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    //reset password
    public boolean resetPassword(User user, String password) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            String sql = "update User m set m.password = ?1 where m.username = ?2";
            Query query = em.createQuery(sql);
            query.setParameter(1, password);
            query.setParameter(2, user.getUsername());

            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }

    // search functions
    public List<User> search(String keyword) {
        EntityManager em = this.getEMF().createEntityManager();
        List<User> users;
        try {
            String sql = "select m from User m where m.lastName like ?1 or "
                    + "m.firstName like ?2 or m.username like ?3";
            Query query = em.createQuery(sql);
            query.setParameter(1, "%" + keyword + "%");
            query.setParameter(2, "%" + keyword + "%");
            query.setParameter(3, "%" + keyword + "%");
            users = query.getResultList();
            return users;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List getUsers(int max, int index) {
        EntityManager em = this.getEMF().createEntityManager();
        Query query = em.createQuery("SELECT c FROM User c order by c.firstName, c.lastName");
        return query.setMaxResults(max).
                setFirstResult(index).
                getResultList( );
    }
    
    //add new user
    public void addUser(User user) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //update a user
    public void updateUser(User user) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //delete a user
    public void deleteUser(User user) {
        EntityManager em = this.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            user = em.merge(user);
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static User login(String username, String password) {
        User user = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMSPU", new java.util.HashMap());
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "select u from User u where u.username = ?1 and u.password= ?2";
            Query query = em.createQuery(sql);
            query.setParameter(1, username);
            query.setParameter(2, password);
            user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    //constructor
    public UserDAO() {
    }
}
