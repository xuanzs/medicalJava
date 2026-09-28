/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class MedicalManagerInfo {
    private String medicalManagerId, userId;
    
    public MedicalManagerInfo(String medManId, String userId) {
        this.medicalManagerId = medManId;
        this.userId = userId;
    }
    
    public String getMedicalManagerId() {
        return medicalManagerId;
    }
    
    public String getUserId() {
        return userId;
    }
}
