package com.example.ehospital.models;

import java.util.Date;

public class Consultation {
    private String patientName;
    private String doctorName;
    private Date appointmentDate;
    private String writtenConsultation;

    public Consultation(String patientName, String doctorName, Date appointmentDate, String writtenConsultation) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
        this.writtenConsultation = writtenConsultation;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getWrittenConsultation() {
        return writtenConsultation;
    }

    public void setWrittenConsultation(String writtenConsultation) {
        this.writtenConsultation = writtenConsultation;
    }
}
