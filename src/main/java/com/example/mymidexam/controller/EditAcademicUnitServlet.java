package com.example.mymidexam.controller;

import com.example.mymidexam.dao.AcademicUnitDAO;
import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.TeacherDAO;
import com.example.mymidexam.enume.AcademicUnitType;
import com.example.mymidexam.enume.EQualification;
import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-academicUnit")
public class EditAcademicUnitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This servlet only handles POST requests, so redirect GET requests to the main page
        request.getRequestDispatcher("/editAcademicUnit.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String academicId = request.getParameter("academicId");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String type = request.getParameter("qualification");


        // Retrieve the teacher from the database
        int id = Integer.parseInt(academicId);
        AcademicUnit academicUnit = academicUnitDAO.getAcademicUnitById(id);

        if (academicUnit != null) {
            // Update the retrieved teacher with the new information
            academicUnit.setAcademicCode(code);
            academicUnit.setAcademicName(name);


            // Update the teacher in the database
            academicUnitDAO.updateAcademicUnit(academicUnit);
            System.out.println("Academic updated successfully");
        } else {
            System.out.println("No Academic found");
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
        response.sendRedirect("MyMidExam_war/manage-academicUnits");
    }
}
