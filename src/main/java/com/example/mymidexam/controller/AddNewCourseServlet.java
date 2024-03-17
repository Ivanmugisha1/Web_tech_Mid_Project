package com.example.mymidexam.controller;

import com.example.mymidexam.dao.*;
import com.example.mymidexam.modal.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/new-course") // Correcting servlet mapping
public class AddNewCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CourseDAO courseDAO = new CourseDAO();
    private final Semester semester = new Semester();
    private final Teacher teacher = new Teacher();
    CourseDefinition courseDefinition = new CourseDefinition();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SemesterDAO semesterDAO = new SemesterDAO();
        TeacherDAO teacherDAO = new TeacherDAO();

        List<Semester> savedSemesters = semesterDAO.getAllSemesters();
        List<Teacher> savedTeachers = teacherDAO.getAllTeachers();

        // Set content type
        response.setContentType("text/html");

        // Generate the HTML dynamically
        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<!DOCTYPE html>");
        htmlResponse.append("<html lang=\"en\">");
        htmlResponse.append("<head>");
        htmlResponse.append("<meta charset=\"UTF-8\">");
        htmlResponse.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        htmlResponse.append("<title>Add New Course</title>");
        htmlResponse.append("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
        htmlResponse.append("</head>");
        htmlResponse.append("<body>");
        htmlResponse.append("<div class=\"container mt-5\">");
        htmlResponse.append("<h1>Add New Course</h1>");
        htmlResponse.append("<form id=\"addCourseForm\" action=\"new-course\" method=\"post\">");
        htmlResponse.append("<div class=\"form-group\">");
        htmlResponse.append("<label for=\"courseCode\">Course Code:</label>");
        htmlResponse.append("<input type=\"text\" class=\"form-control\" id=\"courseCode\" name=\"courseCode\" required>");
        htmlResponse.append("</div>");
        htmlResponse.append("<div class=\"form-group\">");
        htmlResponse.append("<label for=\"courseName\">Course Name:</label>");
        htmlResponse.append("<input type=\"text\" class=\"form-control\" id=\"courseName\" name=\"courseName\" required>");
        htmlResponse.append("</div>");
        htmlResponse.append("<div class=\"form-group\">");
        htmlResponse.append("<label for=\"semester\">Semester:</label>");
        htmlResponse.append("<select class=\"form-control\" id=\"semester\" name=\"semester\">");
        htmlResponse.append("<option value=\"\" disabled selected>Select Semester</option>");

        // Populate semester options
        for (Semester semester : savedSemesters) {
            htmlResponse.append("<option value=\"").append(semester.getSemesterId()).append("\">").append(semester.getSemesterName()).append("</option>");
        }

        htmlResponse.append("</select>");
        htmlResponse.append("</div>");
        htmlResponse.append("<div class=\"form-group\">");
        htmlResponse.append("<label for=\"teacher\">Teacher:</label>");
        htmlResponse.append("<select class=\"form-control\" id=\"teacher\" name=\"teacher\">");
        htmlResponse.append("<option value=\"\" disabled selected>Select Teacher</option>");

        // Populate teacher options
        for (Teacher teacher : savedTeachers) {
            htmlResponse.append("<option value=\"").append(teacher.getTeacherId()).append("\">").append(teacher.getFirstname()).append(" ").append(teacher.getLastname()).append("</option>");
        }

        htmlResponse.append("</select>");
        htmlResponse.append("</div>");
        htmlResponse.append("<button type=\"submit\" class=\"btn btn-primary\">Add Course</button>");
        htmlResponse.append("</form>");
        htmlResponse.append("</div>");
        htmlResponse.append("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
        htmlResponse.append("<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>");
        htmlResponse.append("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
        htmlResponse.append("</body>");
        htmlResponse.append("</html>");

        // Write the HTML response
        response.getWriter().write(htmlResponse.toString());
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseCode = request.getParameter("courseCode");
        String courseName = request.getParameter("courseName");
        int semesterId = Integer.parseInt(request.getParameter("semester")); // Retrieve semester ID from the form
        int teacherId = Integer.parseInt(request.getParameter("teacher")); // Retrieve teacher ID from the form

        System.out.println("Received courseCode: " + courseCode);
        System.out.println("Received courseName: " + courseName);
        System.out.println("Received semesterId: " + semesterId);
        System.out.println("Received teacherId: " + teacherId);

        // Retrieve the Semester object corresponding to the selected ID
        SemesterDAO semesterDAO = new SemesterDAO();
        Semester selectedSemester = semesterDAO.getSemesterById(semesterId);
        System.out.println("Retrieved Semester: " + selectedSemester);

        // Retrieve the Teacher object corresponding to the selected ID
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher selectedTeacher = teacherDAO.getTeacherById(teacherId);
        System.out.println("Retrieved Teacher: " + selectedTeacher);

        // Create a new course object
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseName(courseName);
        course.setSemester(selectedSemester);
        course.setTeacher(selectedTeacher);

        // Add the course to the database
        courseDAO.addCourse(course);

        // Redirect to the course list page
        response.sendRedirect("MyMidExam_war/manage-courses");
    }


}

