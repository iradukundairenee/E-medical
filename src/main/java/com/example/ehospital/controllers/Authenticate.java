package com.example.ehospital.controllers;

import com.example.ehospital.BaseClasses.UserMethods;
import com.example.ehospital.database.TemporaryDatabase;
import com.example.ehospital.enums.UserRole;
import com.example.ehospital.helpers.JwtTokenProvider;
import com.example.ehospital.models.User;
import com.example.ehospital.services.PatientService;
import com.example.ehospital.services.PharmacistService;
import com.example.ehospital.services.PhysicianService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








@WebServlet("/login")
public class Authenticate extends HttpServlet {
  
   

    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
    protected void processRequest() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest();
    }

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.addHeader("Access-Control-Allow-Origin", "*");
    String requestData = req.getReader().lines().collect(Collectors.joining());
    User jsonData = new Gson().fromJson(requestData, User.class);

    LinkedHashMap<Integer, User> mappedUsers;
    mappedUsers = TemporaryDatabase.getInstance().getUsers();
    UserMethods.setUserRole(null);

    boolean userFound = false;
    for (User user : usersList(mappedUsers)) {
        if (user.getUsername().equals(jsonData.getUsername()) && user.getPassword().equals(jsonData.getPassword())) {
            userFound = true;
            handleLogin(user.getUserRole().toLowerCase(), user.getUsername(), String.valueOf(user.getPassword()));
        }
    }

    authResponse(resp, userFound);
}
private ArrayList<User> usersList(LinkedHashMap<Integer, User> mappedUsers) {
    ArrayList<User> usersList = new ArrayList<>();

    for (Map.Entry<Integer, User> entry : mappedUsers.entrySet()) {
        User umData = entry.getValue();
        usersList.add(umData);
    }
    return usersList;
}

/**
 * @param resp
 * @param userFound
 */
private void authResponse(HttpServletResponse resp, boolean userFound) {
    PrintWriter out;
    try {
        out = resp.getWriter();
        if (!userFound) {
            out.print("Invalid credentials");
            return;
        }

        UserRole role = UserMethods.getUserRole();
        // String username = UserMethods.getUsern;
        Map<String, String> responseMsg = new HashMap<>();
        responseMsg.put("success", "true");
        responseMsg.put("message", "Logged in successfully");
        // responseMsg.put("username", username);
        String roleAsString = role.toString();
        responseMsg.put("role", roleAsString);
        
        String jsonResponse = new Gson().toJson(responseMsg);
        resp.setContentType("application/json");
        out.print(jsonResponse);
    } catch (Exception e) {
        System.out.print(e.getMessage());
    }
}






    private void handleLogin(String role, String username, String password) {
        switch (role) {
            case "patient": {
                PatientService patient = new PatientService();
                patient.login(username, password);
                break;
            }
            case "pharmacist": {
                PharmacistService pharmacist = new PharmacistService();
                pharmacist.login(username, password);
                break;
            }
            case "physician": {
                PhysicianService physician = new PhysicianService();
                physician.login(username, password);
                break;
            }
            default:
                break;
        }
    
    }
}
