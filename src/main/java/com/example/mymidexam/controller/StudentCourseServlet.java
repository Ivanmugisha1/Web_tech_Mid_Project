package com.example.mymidexam.controller;

import com.example.mymidexam.dao.AcademicUnitDAO;
import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.StudentCourseDAO;
import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Student;

import com.example.mymidexam.modal.StudentCourse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MyMidExam_war/manage-studentCourse")
public class StudentCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentCourseDAO studentCourseDAO = new StudentCourseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch list of students from the database
        List<StudentCourse> studentCourses = studentCourseDAO.getAllStudentCourse();

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
        out.println("<title>Manage Student Course</title>");

        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container mt-4\">");
        out.println("<h2>Manage Student Course</h2>");

        out.println("<a href='/MyMidExam_war/new-studentCourse' class='btn btn-primary mb-3'>Create New</a>\n");

        out.println("<table class=\"table\">");
        out.println("<thead><tr><th>ID</th><th>Student ID</th><th>Course ID</th><th>Action</th></tr></thead>");
        out.println("<tbody>");

        // Populate table rows with student data
        for (StudentCourse studentCourse : studentCourses) {
            out.println("<tr>");
            out.println("<td>" + studentCourse.getStudentCourseId() + "</td>");
            out.println("<td>" + studentCourse.getStudent().getStudentId() + "</td>");
            out.println("<td>" + studentCourse.getCourse().getCourseCode() + "</td>");
            // Add edit and delete links as required
            out.println("<td><a href='/MyMidExam_war/manage-studentCourse/edit-student?id=" + studentCourse.getStudentCourseId() + "'>Edit</a></td>");
            out.println("<td><a href='/MyMidExam_war/manage-studentCourse/delete-student?id=" + studentCourse.getStudentCourseId() + "'>Delete</a></td>");
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
