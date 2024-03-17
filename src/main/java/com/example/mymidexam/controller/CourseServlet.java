package com.example.mymidexam.controller;

import com.example.mymidexam.dao.AcademicUnitDAO;
import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MyMidExam_war/manage-courses")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CourseDAO courseDAO = new CourseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch list of students from the database
        List<Course> courses = courseDAO.getAllCourses();

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
        out.println("<h2>Manage Courses</h2>");

        out.println("<a href='/MyMidExam_war/new-course' class='btn btn-primary mb-3'>Create New</a>\n");

        out.println("<table class=\"table\">");
        out.println("<thead><tr><th>ID</th><th>Course code</th><th>Course Name</th><th>Semester ID</th><th>Teacher ID</th><th>Action</th></tr></thead>");
        out.println("<tbody>");

        // Populate table rows with course data
        for (Course course : courses) {
            out.println("<tr>");
            out.println("<td>" + course.getCourseId() + "</td>");
            out.println("<td>" + course.getCourseCode() + "</td>");
            out.println("<td>" + course.getCourseName() + "</td>");
            out.println("<td>" + course.getSemester().getSemesterName() + "</td>");
            out.println("<td>" + course.getTeacher().getLastname() + "</td>");
            // Logging semester name and teacher ID for debugging
            System.out.println("Semester Name: " + course.getSemester().getSemesterName());
            System.out.println("Teacher ID: " + course.getTeacher().getTeacherId());
            // Add edit and delete links as required
            out.println("<td><a href='/MyMidExam_war/edit-course'>Edit</a></td>");
            out.println("<td><a href='/MyMidExam_war/delete-course'>Delete</a></td>");
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
