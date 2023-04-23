
package com.example.ehospital.controllers;
import com.example.ehospital.models.Prescription;
import com.example.ehospital.services.PrescriptionServices;
import com.google.gson.Gson;
import io.jsonwebtoken.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prescription")
public class PrescriptionController extends HttpServlet  {
    private PrescriptionServices prescriptionServices = new PrescriptionServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, java.io.IOException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/json");
        PrintWriter printWriter = res.getWriter();

        try {
            List<Prescription> prescription = prescriptionServices.listMedicines();
            String jsonResponse = new Gson().toJson(prescription);
            printWriter.print(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            String jsonResponse =new Gson().toJson("Error getting medicines");
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            printWriter.print(jsonResponse);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, java.io.IOException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/json");
        PrintWriter printWriter = res.getWriter();
        try {

            String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            Prescription prescription = new Gson().fromJson(requestBody, Prescription.class);
    
            java.util.Date utilDate = prescription.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Add the medicine to the service
            prescriptionServices.addPrescription(prescription.getUsername(),prescription.getMedicineName(),prescription.getDosage(),prescription.getInstructions(),sqlDate);

        // Add prescription to the HashMap
        Map<String, String> prescriptionMap = new HashMap<>();
        prescriptionMap.put("username", prescription.getUsername());
        prescriptionMap.put("medicineName", prescription.getMedicineName());
        prescriptionMap.put("dosage", prescription.getDosage().toString());
        prescriptionMap.put("instructions", prescription.getInstructions());
        prescriptionMap.put("date", prescription.getDate().toString());
            // Return a success response
            String jsonResponse = new Gson().toJson("prescription created successfully");
            printWriter.print(jsonResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            String jsonResponse = new Gson().toJson("Error creating prescription");
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            printWriter.print(jsonResponse);
        } finally {
            printWriter.close();
        }
    }
}

