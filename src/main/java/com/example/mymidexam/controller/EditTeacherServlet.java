package com.example.mymidexam.controller;

import com.example.mymidexam.dao.TeacherDAO;
import com.example.mymidexam.enume.EQualification;
import com.example.mymidexam.modal.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-teacher")
public class EditTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final TeacherDAO teacherDAO = new TeacherDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This servlet only handles POST requests, so redirect GET requests to the main page
        request.getRequestDispatcher("/editTeacher.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String teacherId = request.getParameter("teacherId");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String qualificationStr = request.getParameter("qualification");
        EQualification qualification = EQualification.valueOf(qualificationStr);

        // Retrieve the teacher from the database
        int id = Integer.parseInt(teacherId);
        Teacher myTeacher = teacherDAO.getTeacherById(id);

        if (myTeacher != null) {
            // Update the retrieved teacher with the new information
            myTeacher.setFirstname(firstName);
            myTeacher.setLastname(lastName);
            myTeacher.setQualification(qualification);

            // Update the teacher in the database
            teacherDAO.updateTeacher(myTeacher);
            System.out.println("Teacher updated successfully");
        } else {
            System.out.println("No teacher found");
        }

        // Handle delete request
        String deleteTeacherId = request.getParameter("deleteTeacherId");
        if (deleteTeacherId != null) {
            int teacherIdToDelete = Integer.parseInt(deleteTeacherId);
            teacherDAO.deleteTeacherById(teacherIdToDelete);
            System.out.println("Teacher deleted successfully");
        }

        // Redirect to the teacher list page
        response.sendRedirect("MyMidExam_war/manage-teachers");
    }
}
