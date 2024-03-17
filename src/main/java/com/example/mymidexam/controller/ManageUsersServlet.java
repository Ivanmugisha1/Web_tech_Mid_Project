package com.example.mymidexam.controller;

import com.example.mymidexam.dao.UserDao;
import com.example.mymidexam.modal.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/MyMidExam_war/manage-users")
public class ManageUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch list of users from the database
        List<User> userList = userDao.getAllUsers();

        // Set content type to HTML
        response.setContentType("text/html");

        // Create a PrintWriter to write HTML response
        PrintWriter out = response.getWriter();

        // Generate HTML markup for the user list
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Manage Users</title>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container mt-4\">");
        out.println("<h2>Manage Users</h2>");
        out.println("<table class=\"table\">");
        out.println("<thead><tr><th>ID</th><th>Email</th><th>Password</th><th>Role</th></tr></thead>");
        out.println("<tbody>");

        // Populate table rows with user data
        for (User user : userList) {
            out.println("<tr>");
            out.println("<td>" + user.getId() + "</td>");
            out.println("<td>" + user.getEmail() + "</td>");
            out.println("<td>" + user.getPassword() + "</td>");
            out.println("<td>" + user.getRole() + "</td>");
            out.println("<td><a href=/manage-users/edit-user?id=" + user.getId() + "'>Edit</a></td>");
            out.println("<td><a href='/MyMidExam_war/manage-users/delete-user?id=" + user.getId() + "'>Delete</a></td>");
            out.println("</tr>");
        }

        out.println("</tbody></table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        // Close the PrintWriter
        out.close();
        response.sendRedirect("/MyMidExam_war/manage-users");
    }
}
