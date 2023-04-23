package com.example.ehospital.services;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.example.ehospital.BaseClasses.UserMethods;
import com.example.ehospital.database.TemporaryDatabase;
import com.example.ehospital.enums.UserRole;
import com.example.ehospital.models.User;

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

   


    public List<User> listPhysicians() {
        List<User> physicians = new ArrayList<>(TemporaryDatabase.getInstance().getUsers().values());
        java.util.Collections.sort(physicians, Comparator.comparing((Function<User, String>) User::name));
        return physicians;
    }

  
    
}
