
package com.example.ehospital.controllers;
import com.example.ehospital.models.Medecine;
import com.example.ehospital.services.MedecineService;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

@WebServlet("/medicines")
public class MedecineController extends HttpServlet  {

    private MedecineService medecineService = new MedecineService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/json");
        PrintWriter printWriter = res.getWriter();

        try {
            List<Medecine> medicines = medecineService.listMedicines();

            String jsonResponse = new Gson().toJson(medicines);
            printWriter.print(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            String jsonResponse =new Gson().toJson("Error getting medicines");
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            printWriter.print(jsonResponse);
        }
    }



    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.addHeader("Access-Control-Allow-Origin", "*");
    res.setContentType("application/json");
    PrintWriter printWriter = res.getWriter();

    try {
        // Get the medicine details from the request body
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Medecine medicine = new Gson().fromJson(requestBody, Medecine.class);

        // Add the medicine to the service
        medecineService.addMedicine(medicine.getName(), medicine.getPrice(), medicine.getExpirationDate());

        // Add the medicine to the HashMap
        Map<String, String> medicineMap = new HashMap<>();
        medicineMap.put("med-name", medicine.getName());
        medicineMap.put("med-price", medicine.getPrice().toString());
        medicineMap.put("med-expiration", medicine.getExpirationDate().toString());

        // Save the medicine to CSV file
        File file = new File("medicines.csv");
        boolean fileExists = file.exists();

        FileWriter fileWriter = new FileWriter(file, true);
        if (!fileExists) {
            fileWriter.write("med-name,med-price,med-expiration\n");
        }
        fileWriter.write(String.join(",", medicineMap.values()) + "\n");
        fileWriter.close();

        String jsonResponse = new Gson().toJson("Medicine created successfully");
        printWriter.print(jsonResponse);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();

        String jsonResponse = new Gson().toJson("Error creating medicine");
        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        printWriter.print(jsonResponse);
    } finally {
        printWriter.close();
    }
}



    
    
}
