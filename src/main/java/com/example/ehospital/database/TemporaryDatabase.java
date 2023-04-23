package com.example.ehospital.database;

import com.example.ehospital.models.Medecine;
import com.example.ehospital.models.Prescription;
import com.example.ehospital.models.User;
import java.util.LinkedHashMap;


public class TemporaryDatabase {
    private final LinkedHashMap<Integer, User> users = new LinkedHashMap<>();
    private final LinkedHashMap<Integer, Medecine> medicines = new LinkedHashMap<>();
    private final LinkedHashMap<Integer, Prescription> prescriptions = new LinkedHashMap<>();

    private static final TemporaryDatabase instance = new TemporaryDatabase();

    public TemporaryDatabase() {
    }

    public static TemporaryDatabase getInstance() {
        return instance;
    }

    public LinkedHashMap<Integer, User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        int id = users.size() + 1;
        users.put(id, user);
    }

    public LinkedHashMap<Integer, Medecine> getMedicines() {
        return medicines;
    }

    public void addMedicine(Medecine medicine) {
        int id = medicines.size() + 1;
        medicines.put(id, medicine);
    }

    public LinkedHashMap<Integer, Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        int id = prescriptions.size() + 1;
        prescriptions.put(id, prescription);
    }
}
