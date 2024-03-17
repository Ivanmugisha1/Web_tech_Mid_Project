package com.example.mymidexam.controller;

import com.example.mymidexam.dao.CourseDAO;
import com.example.mymidexam.dao.SemesterDAO;
import com.example.mymidexam.dao.TeacherDAO;
import com.example.mymidexam.enume.EQualification;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Semester;
import com.example.mymidexam.modal.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/edit-semester")
public class EditSemesterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final SemesterDAO semesterDAO = new SemesterDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This servlet only handles POST requests, so redirect GET requests to the main page
        request.getRequestDispatcher("/editSemester.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String semesterId = request.getParameter("semesterId");
        String semesterCode = request.getParameter("semesterCode");
        String name = request.getParameter("semesterName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Parse the date of birth string to a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob1 = null;
        Date dob2 = null;
        try {
            dob1 = dateFormat.parse(startDate);
            dob2 = dateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // Retrieve the teacher from the database
        int id = Integer.parseInt(semesterId);
        Semester myTeacher = semesterDAO.getSemesterById(id);

        if (myTeacher != null) {
            // Update the retrieved teacher with the new information
            myTeacher.setSemesterCode(semesterCode);
            myTeacher.setSemesterName(name);
            myTeacher.setStartingDate(dob1);
            myTeacher.setEndDate(dob2);

            // Update the teacher in the database
            semesterDAO.updateSemester(myTeacher);
            System.out.println("Course updated successfully");
        } else {
            System.out.println("No course found");
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
        response.sendRedirect("MyMidExam_war/manage-semesters");
    }
}
