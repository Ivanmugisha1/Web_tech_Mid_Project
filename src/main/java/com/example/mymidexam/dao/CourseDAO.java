package com.example.mymidexam.dao;

import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class CourseDAO {
    private final SessionFactory sessionFactory;

    public CourseDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addCourse(Course course) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Course getCourseById(int courseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> findCoursesByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> query = session.createQuery("FROM Course WHERE courseName LIKE :name", Course.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateCourse(Course course) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCourse(Course course) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> query = session.createQuery("FROM Course ", Course.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void updateCoursesForTeacher(int teacherId, List<Course> courses) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Retrieve the teacher by ID
            Teacher teacher = session.get(Teacher.class, teacherId);

            // Clear existing courses associated with the teacher
            teacher.getCourses().clear();

            // Add selected courses to the teacher
//            for (Course course : courses) {
//                Course existingCourse = session.get(Course.class, course.getCourseId());
//                teacher.addCourse(existingCourse);
//            }

            // Update the teacher entity to reflect changes in courses
            session.update(teacher);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void deleteCourseById(int courseId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            if (course != null) {
                session.delete(course);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}

