package com.example.mymidexam.controller;

import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.modal.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MyMidExam_war/manage-students")
public class ManageStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentDao studentDao = new StudentDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch list of students from the database
        List<Student> studentList = studentDao.getAllStudents();

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
        out.println("<title>Manage Students</title>");

        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container mt-4\">");
        out.println("<h2>Manage Students</h2>");

        out.println("<a href='/MyMidExam_war/new-student' class='btn btn-primary mb-3'>Create New</a>\n");

        out.println("<table class=\"table\">");
        out.println("<thead><tr><th>ID</th><th>Reg No</th><th>First Name</th><th>Last Name</th><th>Date of Birth</th><th>Action</th></tr></thead>");
        out.println("<tbody>");

        // Populate table rows with student data
        for (Student student : studentList) {
            out.println("<tr>");
            out.println("<td>" + student.getId() + "</td>");
            out.println("<td>" + student.getStudentId() + "</td>");
            out.println("<td>" + student.getFirstname() + "</td>");
            out.println("<td>" + student.getLastname() + "</td>");
            out.println("<td>" + student.getDob() + "</td>");
            // Add edit and delete links as required
            out.println("<td><a href='/MyMidExam_war/edit-student'>Edit</a></td>");
            out.println("<td><a href='/MyMidExam_war/deleteStudent'>Delete</a></td>");
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
