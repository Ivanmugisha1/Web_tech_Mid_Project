package com.example.mymidexam.controller;

import com.example.mymidexam.dao.SemesterDAO;
import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.dao.StudentRegistrationDao;
import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Semester;
import com.example.mymidexam.modal.Student;

import com.example.mymidexam.modal.StudentRegistration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/new-studentRegistration") // Correcting servlet mapping
public class AddNewStudentRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentRegistration studentRegistration = new StudentRegistration();
    private final Student student = new Student();
    private final Semester semester = new Semester();
    private final AcademicUnit academicUnit = new AcademicUnit();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the addNewStudentForm.html page
        request.getRequestDispatcher("/addNewStudentRegistration.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve student details from the form
        String stdCode = request.getParameter("studCode");
        String firstName = request.getParameter("firstname");
        String semester = request.getParameter("semcode");
        String registrationdate= request.getParameter("dob");

        // Parse the date of birth string to a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = dateFormat.parse(registrationdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create a new student object
        StudentRegistration studentRegistration1 = new StudentRegistration();
        studentRegistration1.setRegistrationCode(stdCode);
        studentRegistration1.setAcademicUnit(academicUnit);
        studentRegistration1.setRegistrationDate(dob);
        studentRegistration1.setSemesterId(semester.indexOf(academicUnit.getAcademicCode()));


        // Add the student to the database
        StudentRegistrationDao dao = new StudentRegistrationDao();
        dao.addStudentRegistration(studentRegistration1);

        // Redirect to the student list page
        response.sendRedirect("MyMidExam_war/manage-studentRegistration");
    }
}
