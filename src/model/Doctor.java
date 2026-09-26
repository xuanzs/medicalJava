/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Doctor {
    private String doctorId, userId, specialization, medicalManagerId;
    
    public Doctor(String doctorId, String userId, String specialization, String medicalManagerId) {
        this.doctorId = doctorId;
        this.userId = userId;
        this.specialization = specialization;
        this.medicalManagerId = medicalManagerId;
    }
}
