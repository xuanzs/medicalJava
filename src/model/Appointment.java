/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Appointment {
    private String appointId, patientId, patientName, date, time, status;
    
    public Appointment(String appointId, String patientId, String patientName, String date, String time, String status) {
        this.appointId = appointId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.date = date;
        this.time = time;
        this.status = status;
    }
    
    public String getAppointId() {
        return appointId;
    }
    public String getPatientId() {
        return patientId;
    }
    public String getPatientName() {
        return patientName;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getStatus() {
        return status;
    }
}
