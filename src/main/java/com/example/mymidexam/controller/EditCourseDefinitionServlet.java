package com.example.mymidexam.controller;

import com.example.mymidexam.dao.AcademicUnitDAO;
import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.CourseDefinitionDAO;
import com.example.mymidexam.dao.TeacherDAO;
import com.example.mymidexam.enume.AcademicUnitType;
import com.example.mymidexam.enume.EQualification;
import com.example.mymidexam.modal.AcademicUnit;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.CourseDefinition;
import com.example.mymidexam.modal.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-courseDefinition")
public class EditCourseDefinitionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CourseDefinitionDAO courseDefinitionDAO = new CourseDefinitionDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This servlet only handles POST requests, so redirect GET requests to the main page
        request.getRequestDispatcher("/editCourseDefinition.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String courseId = request.getParameter("courseId");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String description = request.getParameter("description");


        // Retrieve the teacher from the database
        int id = Integer.parseInt(courseId);
        CourseDefinition courseDefinition = courseDefinitionDAO.getCourseDefinitionById(id);

        if (courseDefinition != null) {
            // Update the retrieved teacher with the new information
            courseDefinition.setCourseDefinitionName(name);
            courseDefinition.setCourseDefinitionDescription(description);



            // Update the teacher in the database
            courseDefinitionDAO.updateCourseDefinition(courseDefinition);
            System.out.println("Course Definition updated successfully");
        } else {
            System.out.println("No Course Definition found");
        }

//        // Handle delete request
//        String deleteTeacherId = request.getParameter("deleteTeacherId");
//        if (deleteTeacherId != null) {
//            int teacherIdToDelete = Integer.parseInt(deleteTeacherId);
//            teacherDAO.deleteTeacherById(teacherIdToDelete);
//            System.out.println("Teacher deleted successfully");
//        }
//
//        // Redirect to the teacher list page
        response.sendRedirect("MyMidExam_war/manage-courseDefinition");
    }
}
