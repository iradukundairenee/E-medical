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


    public List<User> listPharmacist() {
        List<User> Pharmacist = new ArrayList<>(TemporaryDatabase.getInstance().getUsers().values());
        java.util.Collections.sort(Pharmacist, Comparator.comparingInt(User::getAge));
        return Pharmacist;
    }
}
