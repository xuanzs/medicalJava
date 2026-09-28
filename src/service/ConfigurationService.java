/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Collection;
import model.ConsultationRate;
import model.InsuranceNetwork;
import repo.FileConsultationRateRepo;
import repo.FileInsuranceRepo;

/**
 *
 * @author wongkingsen
 */
public class ConfigurationService {
    private FileConsultationRateRepo rateRepo;
    private FileInsuranceRepo insuranceRepo;
    
    public ConfigurationService() {
        rateRepo = new FileConsultationRateRepo();
        insuranceRepo = new FileInsuranceRepo();
    }
    
    public Collection<ConsultationRate> getAllRates() {
        return rateRepo.getAllRates();
    }
    
    public ConsultationRate findByRateId(String rateId) {
        return rateRepo.findByRateId(rateId);
    }
    
    public boolean updateRate(String rateId, String rateText) {
        double rate;
        
        try {
            rate = Double.parseDouble(rateText.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Consultation rate must be a number.");
        }
        
        if (rate <= 0) {
            throw new IllegalArgumentException("Consultation rate must be greater than 0.");
        }
        
        return rateRepo.updateRate(rateId, rate);
    }
    
    public Collection<InsuranceNetwork> getAllInsurance() {
        return insuranceRepo.getAllInsurance();
    }
    
    public InsuranceNetwork findByInsuranceId(String insuranceId) {
        return insuranceRepo.findByInsuranceId(insuranceId);
    }
    
    public boolean updateInsurance(String insuranceId, String status) {
        if (!status.equalsIgnoreCase("Accepted") && !status.equalsIgnoreCase("Not Accepted")) {
            throw new IllegalArgumentException("Invalid insurance status.");
        }
        
        return insuranceRepo.updateInsurance(insuranceId, status);
    }
}
