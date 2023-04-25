package com.example.ehospital.services;

import com.example.ehospital.models.Medecine;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedecineService {
    private List<Medecine> medicines = new ArrayList<>();

    public List<Medecine> listMedicines() {
        return medicines;
    }

    public void addMedicine(String name, Double price, Date expirationDate) {
        Medecine medicine = new Medecine(name, price, expirationDate);
        medicines.add(medicine);
    }
}

