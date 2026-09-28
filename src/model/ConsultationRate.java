/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wongkingsen
 */
public class ConsultationRate {
    private String rateId;
    private String consultationType;
    private double baseRate;
    
    public ConsultationRate(String rateId, String consultationType, double baseRate) {
        this.rateId = rateId;
        this.consultationType = consultationType;
        this.baseRate = baseRate;
    }
    
    public String getRateId() {
        return rateId;
    }
    
    public String getConsultationType() {
        return consultationType;
    }
    
    public double getBaseRate() {
        return baseRate;
    }
    
    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }
}
