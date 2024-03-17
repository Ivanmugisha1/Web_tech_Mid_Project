package com.example.mymidexam.controller;

import com.example.mymidexam.dao.TeacherDAO;
import com.example.mymidexam.modal.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MyMidExam_war/manage-teachers")
public class TeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final TeacherDAO teacherDAO = new TeacherDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch list of teachers from the database
        List<Teacher> teachers = teacherDAO.getAllTeachers();

        // Set content type to HTML
        response.setContentType("text/html");

        // Create a PrintWriter to write HTML response
        PrintWriter out = response.getWriter();

        // Generate HTML markup for the teacher list
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Manage Teachers</title>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container mt-4\">");
        out.println("<h2>Manage Teachers</h2>");

        // Add a link to create a new teacher
        out.println("<a href='/MyMidExam_war/new-teacher' class='btn btn-primary mb-3'>Create New</a>\n");

        // Generate the teacher table
        out.println("<table class=\"table\">");
        out.println("<thead><tr><th>ID</th><th>Firstname</th><th>Lastname</th><th>Qualification</th><th>Action</th></tr></thead>");
        out.println("<tbody>");

        // Populate table rows with teacher data
        for (Teacher teacher : teachers) {
            out.println("<tr>");
            out.println("<td>" + teacher.getTeacherId() + "</td>");
            out.println("<td>" + teacher.getFirstname() + "</td>");
            out.println("<td>" + teacher.getLastname() + "</td>");
            out.println("<td>" + teacher.getQualification()+ "</td>");

            // Add edit and delete links
            out.println("<td><a href='/MyMidExam_war/edit-teacher?teacherId=" + teacher.getTeacherId() + "'>Edit</a></td>");
            out.println("<td><a href='/MyMidExam_war/delete-teacher  '>Delete</a></td>");
            out.println("</tr>");
        }

        out.println("</tbody></table>");
        out.println("</div>");
        out.println("</body>");
        out.println("<script>");
        out.println("function deleteTeacher(teacherId) {");
        out.println("if (confirm('Are you sure you want to delete this teacher?')) {");
        out.println("$.ajax({");
        out.println("url: '/MyMidExam_war/edit-teacher',");
        out.println("type: 'POST',");
        out.println("data: { deleteTeacherId: teacherId },");
        out.println("success: function(data) {");
        out.println("location.reload(); // Reload the page after deletion");
        out.println("},");
        out.println("error: function(xhr, status, error) {");
        out.println("console.error('Error deleting teacher:', error);");
        out.println("}");
        out.println("});");
        out.println("}");
        out.println("}");
        out.println("</script>");
        out.println("</html>");

        // Close the PrintWriter
        out.close();
    }
}
