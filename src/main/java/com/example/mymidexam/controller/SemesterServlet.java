package com.example.mymidexam.controller;

import com.example.mymidexam.dao.AcademicUnitDAO;
import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.SemesterDAO;
import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Semester;
import com.example.mymidexam.modal.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MyMidExam_war/manage-semesters")
public class SemesterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final SemesterDAO semesterDAO = new SemesterDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch list of students from the database
        List<Semester> semesters = semesterDAO.getAllSemesters();

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
        out.println("<title>Manage Academic Units</title>");

        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container mt-4\">");
        out.println("<h2>Manage Semesters</h2>");

        out.println("<a href='/MyMidExam_war/new-semester' class='btn btn-primary mb-3'>Create New</a>\n");

        out.println("<table class=\"table\">");
        out.println("<thead><tr><th>ID</th><th>Semester code</th><th>Semester Name</th><th>Start Date</th><th>End Date</th><th>Action</th></tr></thead>");
        out.println("<tbody>");

        // Populate table rows with student data
        for (Semester semester : semesters) {
            out.println("<tr>");
            out.println("<td>" + semester.getSemesterId() + "</td>");
            out.println("<td>" + semester.getSemesterCode() + "</td>");
            out.println("<td>" + semester.getSemesterName() + "</td>");
            out.println("<td>" + semester.getStartingDate() + "</td>");
            out.println("<td>" + semester.getEndDate() + "</td>");

            // Add edit and delete links as required
            out.println("<td><a href='/MyMidExam_war/edit-semester  '>Edit</a></td>");
            out.println("<td><a href='/MyMidExam_war/delete-semester  '>Delete</a></td>");
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
