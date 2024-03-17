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

@WebServlet("/edit-course")
public class EditCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CourseDAO courseDAO = new CourseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This servlet only handles POST requests, so redirect GET requests to the main page
        request.getRequestDispatcher("/editCourse.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String courseId = request.getParameter("courseId");
        String code = request.getParameter("courseCode");
        String name = request.getParameter("courseName");
        String description = request.getParameter("description");


        // Retrieve the teacher from the database
        int id = Integer.parseInt(courseId);
        Course myTeacher = courseDAO.getCourseById(id);

        if (myTeacher != null) {
            // Update the retrieved teacher with the new information
            myTeacher.setCourseName(name);
            myTeacher.setCourseCode(code);

            // Update the teacher in the database
            courseDAO.updateCourse(myTeacher);
            System.out.println("Course updated successfully");
        } else {
            System.out.println("No course found");
        }

//        // Handle delete request
//        String deleteTeacherId = request.getParameter("deleteTeacherId");
//        if (deleteTeacherId != null) {
//            int teacherIdToDelete = Integer.parseInt(deleteTeacherId);
//            teacherDAO.deleteTeacherById(teacherIdToDelete);
//            System.out.println("Teacher deleted successfully");
//        }
//
//        // Redirect to the teacher list page
        response.sendRedirect("MyMidExam_war/manage-courses");
    }
}
