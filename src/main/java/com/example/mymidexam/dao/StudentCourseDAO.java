package com.example.mymidexam.dao;

import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.StudentCourse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class StudentCourseDAO {
    private final SessionFactory sessionFactory;

    public StudentCourseDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addStudentCourse(StudentCourse studentCourse) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(studentCourse);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public StudentCourse getStudentCourseById(int studentCourseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(StudentCourse.class, studentCourseId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Additional CRUD methods as needed

    public List<StudentCourse> getStudentCoursesByStudentId(int studentId) {
        try (Session session = sessionFactory.openSession()) {
            Query<StudentCourse> query = session.createQuery("FROM StudentCourse WHERE studentId = :studentId", StudentCourse.class);
            query.setParameter("studentId", studentId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<StudentCourse> getStudentCoursesByCourseId(int courseId) {
        try (Session session = sessionFactory.openSession()) {
            Query<StudentCourse> query = session.createQuery("FROM StudentCourse WHERE courseId = :courseId", StudentCourse.class);
            query.setParameter("courseId", courseId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<StudentCourse> getStudentCoursesByStudentName(String studentName) {
        try (Session session = sessionFactory.openSession()) {
            Query<StudentCourse> query = session.createQuery(
                    "SELECT sc FROM StudentCourse sc JOIN sc.student s WHERE s.firstname LIKE :name OR s.lastname LIKE :name",
                    StudentCourse.class
            );
            query.setParameter("name", "%" + studentName + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<StudentCourse> getStudentCoursesByCourseName(String courseName) {
        try (Session session = sessionFactory.openSession()) {
            Query<StudentCourse> query = session.createQuery(
                    "SELECT sc FROM StudentCourse sc JOIN sc.course c WHERE c.courseName LIKE :name",
                    StudentCourse.class
            );
            query.setParameter("name", "%" + courseName + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateStudentCourse(StudentCourse studentCourse) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(studentCourse);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStudentCourse(StudentCourse studentCourse) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(studentCourse);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List<StudentCourse> getAllStudentCourse() {
        try (Session session = sessionFactory.openSession()) {
            Query<StudentCourse> query = session.createQuery("FROM StudentCourse ", StudentCourse.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

