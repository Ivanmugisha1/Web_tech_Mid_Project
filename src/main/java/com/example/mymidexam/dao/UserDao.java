package com.example.mymidexam.dao;

import com.example.mymidexam.modal.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao extends HibernateUtil {

    // Create
    public void addUser(User user) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read - findById
    public User getUserById(int id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Read - findByEmail
    public User getUserByEmail(String email) {
        try (Session session = getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update
    public void updateUser(User user) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteUser(User user) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        try (Session session = getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
