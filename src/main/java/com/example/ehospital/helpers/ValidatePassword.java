package com.example.ehospital.helpers;

public class ValidatePassword {
    private static final ValidatePassword instance = new ValidatePassword();
    public static ValidatePassword getInstance() {
        return instance;
    }
    
    public static Boolean patientPassword(String password) {
        return password.length() >= 4 && password.length() <= 6;
    }

    public static Boolean physicianPassword(String password) {
        return password.length() >= 7 && password.length() <= 8;
    }

    public static Boolean pharmacistPassword(String password) {
        return password.length() >= 9 && password.length() <= 10;
    }
    
    
}
