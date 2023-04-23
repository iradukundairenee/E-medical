package com.example.ehospital.services;
import com.example.ehospital.BaseClasses.UserMethods;
import com.example.ehospital.database.TemporaryDatabase;
import com.example.ehospital.enums.UserRole;
import com.example.ehospital.models.User;
import java.util.LinkedHashMap;
import java.util.Random;



public class PatientService extends UserMethods {
    @Override
    public void login(String username, String password){
        System.out.println("Patient Signed in");
        UserMethods.setUserRole(UserRole.patient);
    }

    @Override
    public void register(User patient) {
        LinkedHashMap<Integer, User> patients  = TemporaryDatabase.getInstance().getUsers();
        patients.put(new Random().nextInt(23), patient);
    }

    public boolean isUserExist(String username) {
        LinkedHashMap<Integer, User> patients = TemporaryDatabase.getInstance().getUsers();
        for (User patient : patients.values()) {
            if (patient.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    
}
