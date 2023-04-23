package com.example.ehospital.BaseClasses;


import com.example.ehospital.enums.UserRole;
import com.example.ehospital.models.User;

public abstract class UserMethods {
    private static UserRole userRole;
    public abstract void login(String username, String password);
    public abstract void register(User user);

    public static UserRole getUserRole() {
        return userRole;
    }

    public static void setUserRole(UserRole userRole) {
        UserMethods.userRole = userRole;
    }
}