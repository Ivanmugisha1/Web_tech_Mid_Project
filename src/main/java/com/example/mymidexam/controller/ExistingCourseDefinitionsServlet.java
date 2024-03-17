package com.example.mymidexam.controller;

import com.example.mymidexam.dao.CourseDefinitionDAO;
import com.example.mymidexam.modal.CourseDefinition;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ExistingCourseDefinitionsServlet")
public class ExistingCourseDefinitionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Fetch existing course definitions from the database
        List<CourseDefinition> courseDefinitions = courseDefinitionDAO.getAllCourseDefinitions();

        // Generate HTML options for course definitions
        StringBuilder options = new StringBuilder();
        for (CourseDefinition courseDefinition : courseDefinitions) {
            options.append("<option value=\"").append(courseDefinition.getCourseDefinitionId()).append("\">")
                    .append(courseDefinition.getCourseDefinitionName()).append("</option>");
        }

        // Set content type to HTML
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Write HTML options to response
        response.getWriter().write(options.toString());
    }
}
