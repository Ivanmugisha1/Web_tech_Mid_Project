package com.example.mymidexam.controller;

import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.SemesterDAO;
import com.example.mymidexam.dao.TeacherDAO;

import com.example.mymidexam.enume.EQualification;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Semester;
import com.example.mymidexam.modal.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-course")
public class DeleteCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CourseDAO courseDAO = new CourseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherIdParam = request.getParameter("courseId"); try {
            int teacherId = Integer.parseInt(teacherIdParam);
            CourseDAO courseDAO1 = new CourseDAO();
            courseDAO1.deleteCourseById(teacherId);
            response.sendRedirect("MyMidExam_war/manage-courses");
        } catch (NumberFormatException e) {
            // Handle invalid teacher ID
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid course ID");
        }


//        if (teacherIdParam != null && !teacherIdParam.isEmpty()) {
//            try {
//                int teacherId = Integer.parseInt(teacherIdParam);
//                TeacherDAO teacherDAO = new TeacherDAO();
//                teacherDAO.deleteTeacherById(teacherId);
//                response.sendRedirect("MyMidExam_war/manage-teachers");
//            } catch (NumberFormatException e) {
//                // Handle invalid teacher ID
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                response.getWriter().println("Invalid teacher ID");
//            }
//        } else {
//            // No teacher ID provided
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().println("Teacher ID not provided");
//        }

        request.getRequestDispatcher("/deleteCourse.html").forward(request, response);

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String courseId = request.getParameter("courseId");
//        String firstName = request.getParameter("fname");
//        String lastName = request.getParameter("lname");
//        String qualificationStr = request.getParameter("qualification");
//        EQualification qualification = EQualification.valueOf(qualificationStr);

        // Retrieve the teacher from the database
        int id = Integer.parseInt(courseId);
        Course myTeacher = courseDAO.getCourseById(id);

        if (myTeacher != null) {


            // Update the teacher in the database
            courseDAO.deleteCourse(myTeacher);
            System.out.println("Course deleted successfully");
        } else {
            System.out.println("No course found");
        }

        // Handle delete request
        String deleteTeacherId = request.getParameter("courseId");
        if (deleteTeacherId != null) {
            int teacherIdToDelete = Integer.parseInt(deleteTeacherId);
            courseDAO.deleteCourseById(teacherIdToDelete);
            System.out.println("Course deleted successfully");
        }else{
            System.out.println("not work");
        }

        // Redirect to the teacher list page
        response.sendRedirect("MyMidExam_war/manage-courses");
    }

}

