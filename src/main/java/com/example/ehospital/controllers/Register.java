package com.example.ehospital.controllers;

import com.example.ehospital.helpers.ValidatePassword;
import com.example.ehospital.models.User;
import com.example.ehospital.services.PatientService;
import com.example.ehospital.services.PharmacistService;
import com.example.ehospital.services.PhysicianService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/register")
public class Register extends HttpServlet {
    /**
     *
     */
    PrintWriter printWriter;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        printWriter = res.getWriter();
        try {
            PatientService patient = new PatientService();
            PharmacistService pharmacist = new PharmacistService();
            PhysicianService physician = new PhysicianService();
            LinkedHashMap<Integer, User> lhmUsers = new LinkedHashMap<>();
        
            String jsonString = req.getReader().lines().collect(Collectors.joining());
            User myObject = new Gson().fromJson(jsonString, User.class);
            System.out.println(myObject.getUserRole());
            System.out.println(myObject.getAge());

     switch (myObject.getUserRole()) {
        case "patient":
       HashMap<String, String> response = new HashMap<>();
    if (!ValidatePassword.patientPassword(myObject.getPassword())) {
        response.put("success", "false");
        response.put("message", "Password should be between 4 and 6 characters");
    } else if (patient.isUserExist(myObject.getUsername())) { 
        response.put("success", "false");
        response.put("message", "Username already exists. Please choose a different username.");
    } else {
        patient.register(myObject);
        response.put("success", "true");
        response.put("message", "Account created successfully");
    }

    String jsonResponse = new Gson().toJson(response);
    res.setContentType("application/json");
    printWriter.print(jsonResponse);
    break;


    case "pharmacist":
    HashMap<String, String> responsePharmacist = new HashMap<>();
    int statusCode = 200;
    if (!ValidatePassword.pharmacistPassword(myObject.getPassword())) {
        statusCode = 400;
        responsePharmacist.put("success", "false");
        responsePharmacist.put("message", "Password should be between 9 and 10 characters");
    } else if (pharmacist.isUserExist(myObject.getPhoneNumber())) {
        statusCode = 409;
        responsePharmacist.put("success", "false");
        responsePharmacist.put("message", "Phone number already exists");
    } else {
        pharmacist.register(myObject);
        responsePharmacist.put("success", "true");
        responsePharmacist.put("message", "Account created successfully");
    }

    String jsonResponsePharmacist = new Gson().toJson(responsePharmacist);
    res.setContentType("application/json");
    res.setStatus(statusCode);
    printWriter.print(jsonResponsePharmacist);
    break;


            case "physician":
            HashMap<String, String> responsePhysician = new HashMap<>();
            if (!ValidatePassword.physicianPassword(myObject.getPassword())) {
                responsePhysician.put("success", "false");
                responsePhysician.put("message", "Password should be between 7 and 8 characters");
            } else if (physician.isUserExist(myObject.getEmail())) {
                responsePhysician.put("success", "false");
                responsePhysician.put("message", "Email already exists");
            } else {
                physician.register(myObject);
                responsePhysician.put("success", "true");
                responsePhysician.put("message", "Account created successfully");
            }
        
            String jsonResponsePhysician = new Gson().toJson(responsePhysician);
            res.setContentType("application/json");
            printWriter.print(jsonResponsePhysician);
            break;
        

        default:
        HashMap<String, String> responseDefault = new HashMap<>();
        responseDefault.put("success", "false");
        responseDefault.put("message", "The role you have specified does not exist. Please make sure you have entered the correct role and try again.");

        String jsonResponseDefault = new Gson().toJson(responseDefault);
        res.setContentType("application/json");
        printWriter.print(jsonResponseDefault);
        break;
    }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}
