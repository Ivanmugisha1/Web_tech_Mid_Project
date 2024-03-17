package com.example.mymidexam.controller;

import com.example.mymidexam.dao.AcademicUnitDAO;
import com.example.mymidexam.enume.AcademicUnitType;
import com.example.mymidexam.modal.AcademicUnit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-academic")
public class SaveNewAcademicUnit extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AcademicUnitDAO academicUnitDAO = new AcademicUnitDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the addNewAcademicUnit.html page
        request.getRequestDispatcher("/addNewAcademicUnit.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve academic unit details from the form
        String academicCode = request.getParameter("academicCode");
        String academicName = request.getParameter("academicName");
        String type = request.getParameter("type");

        // Create a new academic unit object
        AcademicUnit academicUnit = new AcademicUnit();
        academicUnit.setAcademicCode(academicCode);
        academicUnit.setAcademicName(academicName);
        academicUnit.setType(AcademicUnitType.valueOf(type));

        // Add the academic unit to the database
        academicUnitDAO.addAcademicUnit(academicUnit);

        // Redirect to the academic units list page
        response.sendRedirect("MyMidExam_war/manage-academicUnits");
    }
}
