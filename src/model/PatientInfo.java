/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class PatientInfo {
    private String patientId, userId, name, gender, phone, dob, bloodType, address;
    
    public PatientInfo(String patientId, String userId, String name, String gender, String phone, String dob, String bloodType, String address) {
        this.patientId = patientId;
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
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
    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public String getPhone() {
        return phone;
    }
    public String getDOB() {
        return dob;
    }
    public String getBloodType() {
        return bloodType;
    }
    public String getAddress() {
        return address;
    }
}
