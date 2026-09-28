/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author xuanchen
 */
public class ConsultationInfo {
    private String consultationId, appointmentId, patientId, doctorId, name, date, temp, bloodPressure, heartRate, weight, notes;

    public ConsultationInfo(String consultationId, String appointmentId, String patientId, String doctorId, String name, String date, String temp, String bloodPressure, String heartRate, String weight, String notes) {
        this.consultationId = consultationId;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.name = name;
        this.date = date;
        this.temp = temp;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.weight = weight;
        this.notes = notes;
    }
    
    public String getConsultationId() {
        return consultationId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }
    
    public String getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTemp() {
        return temp;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public String getWeight() {
        return weight;
    }

    public String getNotes() {
        return notes;
    }
}
