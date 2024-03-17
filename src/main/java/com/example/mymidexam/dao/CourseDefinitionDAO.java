package com.example.mymidexam.dao;

import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.CourseDefinition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class CourseDefinitionDAO {
    private final SessionFactory sessionFactory;

    public CourseDefinitionDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<CourseDefinition> getAllCourseDefinitions() {
        try (Session session = sessionFactory.openSession()) {
            Query<CourseDefinition> query = session.createQuery("FROM CourseDefinition ", CourseDefinition.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addCourseDefinition(CourseDefinition courseDefinition) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(courseDefinition);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public CourseDefinition getCourseDefinitionById(int courseDefinitionId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CourseDefinition.class, courseDefinitionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CourseDefinition> findCourseDefinitionsByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<CourseDefinition> query = session.createQuery("FROM CourseDefinition WHERE courseDefinitionName LIKE :name", CourseDefinition.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateCourseDefinition(CourseDefinition courseDefinition) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(courseDefinition);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCourseDefinition(CourseDefinition courseDefinition) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(courseDefinition);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteCourseDefinitionById(int academicUnit) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(academicUnit);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

