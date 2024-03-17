package com.example.mymidexam.dao;

import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Semester;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class SemesterDAO {
    private final SessionFactory sessionFactory;

    public SemesterDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addSemester(Semester semester) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(semester);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Semester getSemesterById(int semesterId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Semester.class, semesterId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Semester> findSemestersByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Semester> query = session.createQuery("FROM Semester WHERE semesterName LIKE :name", Semester.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateSemester(Semester semester) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(semester);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteSemester(Semester semester) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(semester);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Semester> getAllSemesters() {
        try (Session session = sessionFactory.openSession()) {
            Query<Semester> query = session.createQuery("FROM Semester ", Semester.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Semester getSemesterByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Semester> query = session.createQuery("FROM Semester WHERE semesterName = :name", Semester.class);
            query.setParameter("name", name);
            List<Semester> semesters = query.list();
            return semesters.isEmpty() ? null : semesters.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void deleteSemesterById(int semesterId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Semester semester = session.get(Semester.class, semesterId);
            if (semester != null) {
                session.delete(semester);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

