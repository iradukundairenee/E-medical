package com.example.ehospital.controllers;

import com.example.ehospital.models.User;
import com.example.ehospital.services.PhysicianService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/physicians")
public class physician  extends HttpServlet {

    private PhysicianService physicianService = new PhysicianService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/json");
        PrintWriter printWriter = res.getWriter();
        try {
            List<User> physicians = physicianService.listPhysicians();
            String jsonResponse = new Gson().toJson(physicians);
            printWriter.print(jsonResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}
