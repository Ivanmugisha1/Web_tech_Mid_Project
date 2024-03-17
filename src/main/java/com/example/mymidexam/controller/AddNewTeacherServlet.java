package com.example.mymidexam.controller;

import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.TeacherDAO;
import com.example.mymidexam.enume.EQualification;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/new-teacher")
public class AddNewTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final TeacherDAO teacherDAO = new TeacherDAO();
    private final CourseDAO courseDAO = new CourseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/addNewTeacher.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String qualificationStr = request.getParameter("qualification");
        EQualification qualification = EQualification.valueOf(qualificationStr);

        // Retrieve selected courses
        String[] courseIds = request.getParameterValues("courses");

        // Convert course IDs to a list of Course objects
        List<Course> selectedCourses = new ArrayList<>();
        if (courseIds != null) {
            for (String courseId : courseIds) {
                Course course = courseDAO.getCourseById(Integer.parseInt(courseId));
                if (course != null) {
                    selectedCourses.add(course);
                }
            }
        }

        // Create a new teacher object
        Teacher teacher = new Teacher();
        teacher.setFirstname(firstName);
        teacher.setLastname(lastName);
        teacher.setQualification(qualification);
        teacher.setCourses(selectedCourses);

        // Add the teacher to the database
        teacherDAO.addTeacher(teacher);

        // Redirect to the teacher list page
        response.sendRedirect("MyMidExam_war/manage-teachers");
    }
}
