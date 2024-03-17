package com.example.mymidexam.controller;

import com.example.mymidexam.dao.UserDao;
import com.example.mymidexam.modal.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/manage-users/edit-user?id")
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdParam = request.getParameter("id");
        // Decode the userIdParam to handle encoded characters
        String userIdDecoded = java.net.URLDecoder.decode(userIdParam, "UTF-8");
        int userId = Integer.parseInt(userIdDecoded);

        User user = userDao.getUserById(userId);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/edit-user.jsp").forward(request, response);
        } else {
            // Handle error: user not found
            response.sendRedirect("/manage-users?error=user_not_found");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = userDao.getUserById(userId);
        if (user != null) {
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);
            userDao.updateUser(user);
            // Redirect back to manage-users page with success message
            response.sendRedirect("/MyMidExam_war/manage-users?success=update_success");
        } else {
            // Handle error: user not found
            response.sendRedirect("/manage-users?error=user_not_found");
        }
    }
}
