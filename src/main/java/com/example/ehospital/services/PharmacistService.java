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


public class PharmacistService extends UserMethods {
     
    @Override
    public void login(String email, String password){
        System.out.println("Pharmacist");
        UserMethods.setUserRole(UserRole.pharmacist);
    }

    @Override
    public void register(User pharmacist) {
        LinkedHashMap<Integer, User> pharmacists  = TemporaryDatabase.getInstance().getUsers();
        pharmacists.put(new Random().nextInt(23), pharmacist);
    }


    public User getPharmacistByUsername(String username) {
        for (User user : TemporaryDatabase.getInstance().getUsers().values()) {
            if (user.getUserRole().equals(UserRole.pharmacist.name()) && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; 
    }


    public void updateUser(User pharmacist) {
        LinkedHashMap<Integer, User> pharmacists  = TemporaryDatabase.getInstance().getUsers();
        for (User user : pharmacists.values()) {
            if (user.getUserRole().equals(UserRole.pharmacist.name()) && user.getUsername().equals(pharmacist.getUsername())) {
                pharmacists .put(user.hashCode(), pharmacist);
                break;
            }
        }
    }




    public List<User> listPharmacists() {
        List<User> pharmacist = new ArrayList<>();
        for (User user : TemporaryDatabase.getInstance().getUsers().values()) {
            if (user.getUserRole().equals(UserRole.pharmacist.name())) {
                pharmacist.add(user);
            }
        }
        java.util.Collections.sort(pharmacist, Comparator.comparing((Function<User, String>) User::name));
        return pharmacist;
    }

   

    public boolean isUserExist(String phoneNumber) {
        LinkedHashMap<Integer, User> users = TemporaryDatabase.getInstance().getUsers();
        for (User user : users.values()) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
    
    
}
