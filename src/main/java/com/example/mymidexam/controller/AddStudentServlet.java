package com.example.mymidexam.controller;

import com.example.mymidexam.dao.StudentDao;
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

@WebServlet("/new-student") // Correcting servlet mapping
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentDao studentDao = new StudentDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the addNewStudentForm.html page
        request.getRequestDispatcher("/addNewStudentForm.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve student details from the form
        String stdId = request.getParameter("stdId");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String dobString = request.getParameter("dob");

        // Parse the date of birth string to a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = dateFormat.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create a new student object
        Student student = new Student();
        student.setFirstname(firstName);
        student.setLastname(lastName);
        student.setDob(dob);
        student.setStudentId(Integer.parseInt(stdId));

        // Add the student to the database
        studentDao.addStudent(student);

        // Redirect to the student list page
        response.sendRedirect("MyMidExam_war/manage-students");
    }
}
