/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Patient {
    private String patientId, userId, dob, bloodType, address;
    
    public Patient(String patientId, String userId, String dob, String bloodType, String address) {
        this.patientId = patientId;
        this.userId = userId;
        this.dob = dob;
        this.bloodType = bloodType;
        this.address = address;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getDob() {
        return dob;
    }
    
    public String getBloodType() {
        return bloodType;
    }
    
    public String getAddress() {
        return address;
    }
}
