/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Consultation {
    private String consultId, patientId, doctorId, date, temp, bloodPressure, heartRate, weight, notes;
    
    public Consultation(String consultId, String patientId, String doctorId, String date, String temp, String bloodPressure, String heartRate, String weight, String notes) {
        this.consultId = consultId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.temp = temp;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.weight = weight;
        this.notes = notes;
    }
    
    public String getConsultId() {
        return consultId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
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
