package com.example.mymidexam.controller;

import com.example.mymidexam.dao.AcademicUnitDAO;
import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.CourseDefinitionDAO;
import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.CourseDefinition;
import com.example.mymidexam.modal.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MyMidExam_war/manage-courseDefinition")
public class CourseDefinitionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch list of students from the database
        List<CourseDefinition> coursesDefinitions = courseDefinitionDAO.getAllCourseDefinitions();

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
        out.println("<title>Manage Course Definitions</title>");

        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container mt-4\">");
        out.println("<h2>Manage Courses</h2>");

        out.println("<a href='/MyMidExam_war/new-courseDefinition' class='btn btn-primary mb-3'>Create New</a>\n");

        out.println("<table class=\"table\">");
        out.println("<thead><tr><th>ID</th><th>Course Definition name</th><th>Course Description</th><th>Action</th></tr></thead>");
        out.println("<tbody>");

        // Populate table rows with student data
        for (CourseDefinition courseDefinition : coursesDefinitions) {
            out.println("<tr>");
            out.println("<td>" + courseDefinition.getCourseDefinitionId() + "</td>");
            out.println("<td>" + courseDefinition.getCourseDefinitionName() + "</td>");
            out.println("<td>" + courseDefinition.getCourseDefinitionDescription() + "</td>");

            // Add edit and delete links as required
            out.println("<td><a href='/MyMidExam_war/edit-courseDefinition'>Edit</a></td>");
            out.println("<td><a href='/MyMidExam_war/delete-courseDefinition'>Delete</a></td>");
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
