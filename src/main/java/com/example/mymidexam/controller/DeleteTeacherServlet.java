package com.example.mymidexam.controller;

import com.example.mymidexam.dao.SemesterDAO;
import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.dao.TeacherDAO;

import com.example.mymidexam.enume.EQualification;
import com.example.mymidexam.modal.Course;
import com.example.mymidexam.modal.Semester;
import com.example.mymidexam.modal.Student;
import com.example.mymidexam.modal.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-teacher")
public class DeleteTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final TeacherDAO teacherDAO = new TeacherDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherIdParam = request.getParameter("teacherId"); try {
            int studentId = Integer.parseInt(teacherIdParam);
            TeacherDAO teacherDAO1 = new TeacherDAO();
            teacherDAO1.deleteTeacherById(studentId);
            response.sendRedirect("MyMidExam_war/manage-teachers");
        } catch (NumberFormatException e) {
            // Handle invalid teacher ID
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid teacher ID");
        }


//        if (teacherIdParam != null && !teacherIdParam.isEmpty()) {
//            try {
//                int teacherId = Integer.parseInt(teacherIdParam);
//                TeacherDAO teacherDAO = new TeacherDAO();
//                teacherDAO.deleteTeacherById(teacherId);
//                response.sendRedirect("MyMidExam_war/manage-teachers");
//            } catch (NumberFormatException e) {
//                // Handle invalid teacher ID
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                response.getWriter().println("Invalid teacher ID");
//            }
//        } else {
//            // No teacher ID provided
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().println("Teacher ID not provided");
//        }

        request.getRequestDispatcher("/deleteTeacher.html").forward(request, response);

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String studentId = request.getParameter("teacherId");
//        String firstName = request.getParameter("fname");
//        String lastName = request.getParameter("lname");
//        String qualificationStr = request.getParameter("qualification");
//        EQualification qualification = EQualification.valueOf(qualificationStr);

        // Retrieve the teacher from the database
        int id = Integer.parseInt(studentId);
        Teacher myStudent = teacherDAO.getTeacherById(id);

        if (myStudent != null) {
            // Update the retrieved teacher with the new information
//            myTeacher.setFirstname(firstName);
//            myTeacher.setLastname(lastName);


            // Update the teacher in the database
            teacherDAO.updateTeacher(myStudent);
            System.out.println("st deleted successfully");
        } else {
            System.out.println("No st found");
        }

        // Handle delete request
        String studentId2 = request.getParameter("teacherId");
        if (studentId2 != null) {
            int teacherIdToDelete = Integer.parseInt(studentId2);
            teacherDAO.deleteTeacherById(teacherIdToDelete);
            System.out.println("st deleted successfully");
        }else{
            System.out.println("not work");
        }

        // Redirect to the teacher list page
        response.sendRedirect("MyMidExam_war/manage-teachers");
    }

}
