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

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final StudentDao teacherDAO = new StudentDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherIdParam = request.getParameter("studentId"); try {
            int studentId = Integer.parseInt(teacherIdParam);
            StudentDao studentDAO = new StudentDao();
            teacherDAO.deleteStudent(studentId);
            response.sendRedirect("MyMidExam_war/manage-students");
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

        request.getRequestDispatcher("/deleteStudent.html").forward(request, response);

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve teacher details from the form
        String studentId = request.getParameter("studentId");
//        String firstName = request.getParameter("fname");
//        String lastName = request.getParameter("lname");
//        String qualificationStr = request.getParameter("qualification");
//        EQualification qualification = EQualification.valueOf(qualificationStr);

        // Retrieve the teacher from the database
        int id = Integer.parseInt(studentId);
        Student myStudent = teacherDAO.getStudentById(id);

        if (myStudent != null) {
            // Update the retrieved teacher with the new information
//            myTeacher.setFirstname(firstName);
//            myTeacher.setLastname(lastName);


            // Update the teacher in the database
            teacherDAO.updateStudent(myStudent);
            System.out.println("st deleted successfully");
        } else {
            System.out.println("No st found");
        }

        // Handle delete request
        String studentId2 = request.getParameter("studentId");
        if (studentId2 != null) {
            int teacherIdToDelete = Integer.parseInt(studentId2);
            teacherDAO.deleteTeacherById(teacherIdToDelete);
            System.out.println("st deleted successfully");
        }else{
            System.out.println("not work");
        }

        // Redirect to the teacher list page
        response.sendRedirect("MyMidExam_war/manage-students");
    }

}
