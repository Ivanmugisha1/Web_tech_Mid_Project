package com.example.mymidexam.controller;

import com.example.mymidexam.dao.CourseDefinitionDAO;
import com.example.mymidexam.dao.SemesterDAO;
import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.modal.CourseDefinition;
import com.example.mymidexam.modal.Semester;
import com.example.mymidexam.modal.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/new-semester") // Correcting servlet mapping
public class AddNewSemesterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final SemesterDAO semesterDAO = new SemesterDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the addNewStudentForm.html page
        request.getRequestDispatcher("/addNewSemester.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve student details from the form
        String semCode = request.getParameter("semCode");
        String semName = request.getParameter("semName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob1 = null;
        Date dob2 = null;

        try {
            dob1 = dateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob2 = dateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Parse the date of birth string to a Date object


        // Create a new student object
        Semester semester = new Semester();
        semester.setSemesterCode(semCode);
        semester.setSemesterName(semName);
        semester.setStartingDate(dob1);
        semester.setEndDate(dob2);


        // Add the student to the database
        semesterDAO.addSemester(semester);

        // Redirect to the student list page
        response.sendRedirect("MyMidExam_war/manage-semesters");
    }
}

