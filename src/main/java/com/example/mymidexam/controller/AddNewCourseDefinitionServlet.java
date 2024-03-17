package com.example.mymidexam.controller;

import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.CourseDefinitionDAO;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.CourseDefinition;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/new-courseDefinition")
public class AddNewCourseDefinitionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CourseDAO courseDAO = new CourseDAO();
    private final CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch all saved courses
        List<Course> courses = courseDAO.getAllCourses();

        // Set the courses as an attribute in the request
        request.setAttribute("courses", courses);

        // Forward the request to the addNewCourseDefinition.html page
        request.getRequestDispatcher("/addNewCourseDefinition.html").forward(request, response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve course definition details from the form
        String courseDefinitionName = request.getParameter("courseDefinitionName");
        String courseDefinitionDescription = request.getParameter("courseDefinitionDescription");

        // Create a new course definition object
        CourseDefinition courseDefinition = new CourseDefinition();
        courseDefinition.setCourseDefinitionName(courseDefinitionName);
        courseDefinition.setCourseDefinitionDescription(courseDefinitionDescription);

        // Add the course definition to the database
        courseDefinitionDAO.addCourseDefinition(courseDefinition);

        // Redirect to the course definition management page
//        response.sendRedirect(request.getContextPath() + "MyMidExam_war/manage-courseDefinition");
        response.sendRedirect("MyMidExam_war/manage-courseDefinition");
    }
}
