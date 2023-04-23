package com.example.ehospital.services;

import com.example.ehospital.BaseClasses.UserMethods;
import com.example.ehospital.database.TemporaryDatabase;
import com.example.ehospital.enums.UserRole;
import com.example.ehospital.models.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Function;


public class PhysicianService extends UserMethods {

    @Override
    public void login(String phoneNumber, String password){
        System.out.println("Physician Signed in");
        UserMethods.setUserRole(UserRole.physician);
    }

    @Override
    public void register(User physician) {
        LinkedHashMap<Integer, User> physicians  = TemporaryDatabase.getInstance().getUsers();
        physicians.put(new Random().nextInt(23), physician);
    }

    public void updateUser(User physician) {
        LinkedHashMap<Integer, User> physicians  = TemporaryDatabase.getInstance().getUsers();
        for (User user : physicians.values()) {
            if (user.getUserRole().equals(UserRole.physician.name()) && user.getUsername().equals(physician.getUsername())) {
                physicians.put(user.hashCode(), physician);
                break;
            }
        }
    }

    public List<User> listPhysicians() {
        List<User> physicians = new ArrayList<>();
        for (User user : TemporaryDatabase.getInstance().getUsers().values()) {
            if (user.getUserRole().equals(UserRole.physician.name())) {
                physicians.add(user);
            }
        }
        java.util.Collections.sort(physicians, Comparator.comparing((Function<User, String>) User::name));
        return physicians;
    }

    public User getPhysicianByUsername(String username) {
        for (User user : TemporaryDatabase.getInstance().getUsers().values()) {
            if (user.getUserRole().equals(UserRole.physician.name()) && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; 
    }

    public boolean isUserExist(String email) {
        LinkedHashMap<Integer, User> patients = TemporaryDatabase.getInstance().getUsers();
        for (User patient : patients.values()) {
            if (patient.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    

}
