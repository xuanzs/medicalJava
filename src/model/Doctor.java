/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

<<<<<<< HEAD
/**
 *
 * @author xuanchen
 */
public class Doctor extends User {
    public Doctor(String userId, String name, String email, String password, String phone, String gender, String role) {
        super(userId, name, email, password, phone, gender, role);
=======
public class Doctor {
    private String doctorId, userId, specialization, medicalManagerId;
    
    public Doctor(String doctorId, String userId, String specialization, String medicalManagerId) {
        this.doctorId = doctorId;
        this.userId = userId;
        this.specialization = specialization;
        this.medicalManagerId = medicalManagerId;
>>>>>>> origin/kaixuan
    }
}
