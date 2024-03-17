package com.example.mymidexam.dao;

import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.StudentRegistration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRegistrationDao {
    private SessionFactory sessionFactory;

    public void StudentRegistrationDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addStudentRegistration(StudentRegistration studentRegistration) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(studentRegistration);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public StudentRegistration getStudentRegistrationById(int registrationId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(StudentRegistration.class, registrationId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<StudentRegistration> findStudentRegistrationsByStudentName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<StudentRegistration> query = session.createQuery("SELECT sr FROM StudentRegistration sr JOIN sr.student s WHERE s.firstname LIKE :name OR s.lastname LIKE :name", StudentRegistration.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateStudentRegistration(StudentRegistration studentRegistration) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(studentRegistration);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStudentRegistration(StudentRegistration studentRegistration) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(studentRegistration);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<StudentRegistration> getAllStudentRegistration() {
        try (Session session = sessionFactory.openSession()) {
            Query<StudentRegistration> query = session.createQuery("FROM StudentRegistration ", StudentRegistration.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

