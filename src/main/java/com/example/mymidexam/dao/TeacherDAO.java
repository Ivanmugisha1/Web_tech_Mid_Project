package com.example.mymidexam.dao;

import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Semester;
import com.example.mymidexam.modal.Teacher;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class TeacherDAO {
    private final SessionFactory sessionFactory;

    public TeacherDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    // Existing methods omitted for brevity

    public void updateTeacher(Teacher teacher) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(teacher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTeacherById(int teacherId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, teacherId);
            if (teacher != null) {
                session.delete(teacher);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public Teacher getTeacherById(int teachrId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Teacher.class, teachrId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addTeacher(Teacher teacher) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(teacher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Teacher> getAllTeachers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Teacher> query = session.createQuery("FROM Teacher ", Teacher.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
