package com.example.mymidexam.controller;

import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.StudentCourseDAO;
import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Student;

import com.example.mymidexam.modal.StudentCourse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/new-studentCourse") // Correcting servlet mapping
public class AddNewStudentCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentCourseDAO studentCourseDAO = new StudentCourseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the addNewStudentForm.html page
        request.getRequestDispatcher("/addNewStudentForm.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Course course = new Course();
        Student student = new Student();

        StudentCourseDAO dao1 = new StudentCourseDAO();
        CourseDAO dao2 = new CourseDAO();

        StudentDao dao3 = new StudentDao();

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourse(studentCourse.getCourse());



        // Add the student to the database
        studentCourseDAO.addStudentCourse(studentCourse);

        // Redirect to the student list page
        response.sendRedirect("MyMidExam_war/manage-studentCourse");
    }
}
