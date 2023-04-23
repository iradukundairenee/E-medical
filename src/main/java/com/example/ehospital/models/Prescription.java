package com.example.ehospital.models;

import java.util.Date;

public class Prescription {
    private String username; 
    private String medicineName;
    private String dosage;
    private String instructions;
    private Date date;

    public Prescription(String username, String medicineName, String dosage, String instructions, Date date) {
        this.username = username;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.instructions = instructions;
        this.date = date;
    }

    public Prescription(String username2, String medicineName2, String dosage2, String instructions2) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
