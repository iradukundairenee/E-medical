package com.example.ehospital.services;
import com.example.ehospital.models.Prescription;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;




public class PrescriptionServices {
    private List<Prescription> prescriptions= new ArrayList<>();

    public List<Prescription> listMedicines() {
        return prescriptions;
    }
    public void addPrescription(String username,String medicineName,String dosage,String instructions,Date date) {
        Prescription prescription =new Prescription(username, medicineName, dosage, instructions,date);
        prescriptions.add(prescription);
    }
   
   
 
}
