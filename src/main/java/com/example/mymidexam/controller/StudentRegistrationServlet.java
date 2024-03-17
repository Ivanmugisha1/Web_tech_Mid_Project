package com.example.mymidexam.controller;

import com.example.mymidexam.dao.AcademicUnitDAO;
import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.dao.StudentRegistrationDao;
import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Student;

import com.example.mymidexam.modal.StudentRegistration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MyMidExam_war/manage-studentRegistration")
public class StudentRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentRegistrationDao studentRegistrationDao = new StudentRegistrationDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch list of students from the database
        List<StudentRegistration> studentRegistrations = studentRegistrationDao.getAllStudentRegistration();

        // Set content type to HTML
        response.setContentType("text/html");

        // Create a PrintWriter to write HTML response
        PrintWriter out = response.getWriter();

        // Generate HTML markup for the student list
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Manage Student Registration</title>");

        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container mt-4\">");
        out.println("<h2>Manage Student Registrations</h2>");

        out.println("<a href='/MyMidExam_war/new-studentRegistration' class='btn btn-primary mb-3'>Create New</a>\n");

        out.println("<table class=\"table\">");
        out.println("<thead><tr><th>ID</th><th>Registration Code</th><th>Student</th><th>Semester</th><th>Academic</th><th>Registration Date</th><th>Action</th></tr></thead>");
        out.println("<tbody>");

        // Populate table rows with student data
        for (StudentRegistration studentRegistration : studentRegistrations) {
            out.println("<tr>");
            out.println("<td>" + studentRegistration.getRegistrationId() + "</td>");
            out.println("<td>" + studentRegistration.getRegistrationCode() + "</td>");
            out.println("<td>" + studentRegistration.getStudent().getStudentId() + "</td>");
            out.println("<td>" + studentRegistration.getSemester().getSemesterId() + "</td>");
            out.println("<td>" + studentRegistration.getRegistrationDate().toString() + "</td>");
            out.println("<td>" + studentRegistration.getAcademicUnit().getAcademicName() + "</td>");
            // Add edit and delete links as required
            out.println("<td><a href='/MyMidExam_war/manage-studentRegistration/edit-studentRegistration?id=" + studentRegistration.getRegistrationId() + "'>Edit</a></td>");
            out.println("<td><a href='/MyMidExam_war/manage-studentRegistration/delete-studentRegistration?id=" + studentRegistration.getRegistrationId() + "'>Delete</a></td>");
            out.println("</tr>");
        }

        out.println("</tbody></table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        // Close the PrintWriter
        out.close();
    }
}
