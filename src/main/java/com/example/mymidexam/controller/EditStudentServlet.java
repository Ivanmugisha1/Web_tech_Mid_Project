package com.example.mymidexam.controller;

import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.dao.TeacherDAO;
import com.example.mymidexam.enume.EQualification;
import com.example.mymidexam.modal.Student;
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

@WebServlet("/edit-student")
public class EditStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentDao teacherDAO = new StudentDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This servlet only handles POST requests, so redirect GET requests to the main page
        request.getRequestDispatcher("/editStudent.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String teacherId = request.getParameter("studentId");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String dobString = request.getParameter("dob");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = dateFormat.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Retrieve the teacher from the database
        int id = Integer.parseInt(teacherId);
        Student myTeacher = teacherDAO.getStudentById(id);

        if (myTeacher != null) {
            // Update the retrieved teacher with the new information
            myTeacher.setFirstname(firstName);
            myTeacher.setLastname(lastName);
            myTeacher.setFirstname(firstName);
            myTeacher.setLastname(lastName);
            myTeacher.setDob(dob);

            // Update the teacher in the database
            teacherDAO.updateStudent(myTeacher);
            System.out.println("Student updated successfully");
        } else {
            System.out.println("No teacher found");
        }

//        // Handle delete request
//        String deleteTeacherId = request.getParameter("deleteTeacherId");
//        if (deleteTeacherId != null) {
//            int teacherIdToDelete = Integer.parseInt(deleteTeacherId);
//            teacherDAO.deleteTeacherById(teacherIdToDelete);
//            System.out.println("Teacher deleted successfully");
//        }

        // Redirect to the teacher list page
        response.sendRedirect("MyMidExam_war/manage-students");
    }
}
