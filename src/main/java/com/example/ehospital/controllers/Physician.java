package com.example.ehospital.controllers;

import com.example.ehospital.models.User;
import com.example.ehospital.services.PhysicianService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/physicians")
public class Physician extends HttpServlet  {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/json");
        PrintWriter printWriter = res.getWriter();
        try {
            String username = req.getParameter("username"); 
            User physician = physicianService.getPhysicianByUsername(username);
            if (physician != null) { 
                physician.setAccessGranted(true);
                physicianService.updateUser(physician);
                String jsonResponse = new Gson().toJson("Access granted for " + physician.getUsername());
                printWriter.print(jsonResponse);
            } else { 
                String jsonResponse = new Gson().toJson("Physician not found for username: " + username);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                printWriter.print(jsonResponse);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}





