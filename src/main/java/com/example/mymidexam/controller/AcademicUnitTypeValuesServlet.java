package com.example.mymidexam.controller;

import com.example.mymidexam.enume.AcademicUnitType;
import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/AcademicUnitTypeValuesServlet")
public class AcademicUnitTypeValuesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the values of AcademicUnitType enum
        List<String> academicUnitTypes = Arrays.stream(AcademicUnitType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        // Convert the list to JSON
        String json = new Gson().toJson(academicUnitTypes);

        // Set content type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write JSON data to response
        response.getWriter().write(json);
    }
}
