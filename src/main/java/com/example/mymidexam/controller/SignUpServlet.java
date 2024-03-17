package com.example.mymidexam.controller;

import com.example.mymidexam.dao.UserDao;
import com.example.mymidexam.modal.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display signup form
        request.getRequestDispatcher("signup.html").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle user signup
        String email = request.getParameter("email");
        String password1 = request.getParameter("password");
        String password2 = request.getParameter("conf-password");
        String role = request.getParameter("role");

        // Check if any field is empty
        if (email.isEmpty() || password1.isEmpty() || password2.isEmpty() || role.isEmpty()) {
            // Redirect to signup page with error message
            response.sendRedirect("signup.html?error=All fields are required.");
            return;
        }

        // Check if passwords match
        if (!password1.equals(password2)) {
            // Redirect to signup page with error message
            response.sendRedirect("signup.html?error=Passwords do not match.");
            return;
        }

        // Check if email already exists
        User existingUser = userDao.getUserByEmail(email);
        if (existingUser != null) {
            // Redirect to signup page with error message
            response.sendRedirect("signup.html?error=Email address already exists.");
            return;
        }

        // Create new user
        User user = new User(email, password1, role);
        userDao.addUser(user);

        // Redirect to login page with success message
        response.sendRedirect("login.html?success=Account created successfully. Please login.");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handling PUT requests is not implemented for this servlet
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "PUT method is not supported for this resource.");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Deleting a user is not applicable for signup
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "DELETE method is not supported for this resource.");
    }
}
