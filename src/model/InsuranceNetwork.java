/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wongkingsen
 */
public class InsuranceNetwork {
    private String insuranceId, insuranceName, status;
    
    public InsuranceNetwork(String insuranceId, String insuranceName, String status) {
        this.insuranceId = insuranceId;
        this.insuranceName = insuranceName;
        this.status = status;
    }
    
    public String getInsuranceId() {
        return insuranceId;
    }
    
    public String getInsuranceName() {
        return insuranceName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
