package com.example.mymidexam.controller;

import com.example.mymidexam.dao.UserDao;
import com.example.mymidexam.modal.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle user login
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate user credentials
        User user = userDao.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Authentication successful, create session
            HttpSession session = request.getSession(true);
            if (session.isNew()) {
                // If it's the first login, redirect to index.html
                response.sendRedirect("index.html");
            } else {
                session.setAttribute("user", user);
                // Set session timeout to 1 minute (60 seconds)
                session.setMaxInactiveInterval(60);

                // Redirect based on user role
                String role = user.getRole();
                switch (role) {
                    case "student":
                        response.sendRedirect("studentDashboard.html");
                        break;
                    case "teacher":
                        response.sendRedirect("teacherDashboard.html");
                        break;
                    case "admin":
                        response.sendRedirect("adminDashboard.html");
                        break;
                    default:
                        // Invalid role, display error message
                        response.sendRedirect("login.html?error=Role not found.");
                        break;
                }
            }
        } else {
            // Authentication failed, display error message
            response.sendRedirect("login.html?error=Invalid Email or Password.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is already logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // User is logged in, redirect to respective dashboard
            User user = (User) session.getAttribute("user");
            String role = user.getRole();
            switch (role) {
                case "student":
                    response.sendRedirect("studentDashboard.html");
                    break;
                case "teacher":
                    response.sendRedirect("teacherDashboard.html");
                    break;
                case "admin":
                    response.sendRedirect("adminDashboard.html");
                    break;
                default:
                    // Invalid role, redirect to login page
                    response.sendRedirect("login.html");
                    break;
            }
        } else {
            // User is not logged in, redirect to login page
            response.sendRedirect("login.html");
        }
    }
}
