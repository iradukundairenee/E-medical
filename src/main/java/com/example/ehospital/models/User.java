package com.example.ehospital.models;

import com.example.ehospital.enums.UserRole;


public class User {
    private String name;
    private String username;
    private String phoneNumber;
    private String email;
    private String gender;
    private int age;
    private String userRole;
    private String password;
    private boolean accessGranted;

    public User() {
    }
   
    public String name() {
        return name;
    }

    public void getName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   public int getAge() {
    return age;
}


    public void setAge(int age) {
        this.age = age;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isAccessGranted() {
        return accessGranted;
    }

    public void setAccessGranted(boolean accessGranted) {
        this.accessGranted = accessGranted;
    }
}
    

