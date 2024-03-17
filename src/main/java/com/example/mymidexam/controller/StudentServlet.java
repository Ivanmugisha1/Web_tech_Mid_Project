package com.example.mymidexam.controller;


import com.example.mymidexam.dao.StudentDao;
import com.example.mymidexam.modal.Student;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentDao studentDao;

    @Override
    public void init() throws ServletException {
        super.init();
        studentDao = new StudentDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDao.getAllStudents();

        // Convert students to JSON
        Gson gson = new Gson();
        String jsonStudents = gson.toJson(students);

        // Set response content type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write JSON data to response
        response.getWriter().write(jsonStudents);
    }


    private void getAllStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDao.getAllStudents();

        // Convert students to JSON
        Gson gson = new Gson();
        String jsonStudents = gson.toJson(students);

        // Set response content type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write JSON data to response
        response.getWriter().write(jsonStudents);
    }

    private void getStudentById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        Student student = studentDao.getStudentById(studentId);
        response.getWriter().println(student != null ? student.toString() : "Student not found!");
    }

    private void findStudentsByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        List<Student> students = studentDao.findStudentsByName(name);
        if (students != null && !students.isEmpty()) {
            for (Student student : students) {
                response.getWriter().println(student.toString());
            }
        } else {
            response.getWriter().println("No students found with the provided name!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        Date dob = new Date(); // Sample - you need to parse the actual date from request

        // Create a new student object
        Student student = new Student();
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setDob(dob);

        // Add the student
        studentDao.addStudent(student);

        response.getWriter().println("Student added successfully!");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        Date dob = new Date(); // Sample - you need to parse the actual date from request

        // Create a new student object
        Student student = new Student();
        student.setStudentId(studentId);
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setDob(dob);

        // Update the student
        studentDao.updateStudent(student);

        response.getWriter().println("Student updated successfully!");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        // Get the student by ID
        Student student = studentDao.getStudentById(studentId);
//        if (student != null) {
//            // Delete the student
//            studentDao.deleteStudent(student);
//            response.getWriter().println("Student deleted successfully!");
//        } else {
//            response.getWriter().println("Student not found!");
//        }
    }
}

