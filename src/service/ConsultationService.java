/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import exception.InvalidVitalSignsException;
import model.ConsultationInfo;
import model.PatientInfo;
import repo.FileAppointmentRepo;
import repo.FileConsultationRepo;

/**
 *
 * @author xuanchen
 */
public class ConsultationService {
    private String lastCreatedConsultId;
    
    private FileConsultationRepo consultRepo;
    private FileAppointmentRepo appointRepo;
    
    private PatientService patientService;
    
    private ArrayList<String[]> consultList;
    private ArrayList<ConsultationInfo> consultInfo = new ArrayList<>();
    
    public ConsultationService() {
        consultRepo = new FileConsultationRepo();
        appointRepo = new FileAppointmentRepo();
        patientService = new PatientService();
        
        reloadConsultations();
    }
    
    public void reloadConsultations() {
        consultRepo = new FileConsultationRepo();
        
        consultList = consultRepo.returnAllConsultation();
        
        consultInfo.clear();
        
        for (String[] cl : consultList) {
            String c = cl[0].trim();
            String a = cl[1].trim();
            String p = cl[2].trim();
            String d = cl[3].trim();
            String da = cl[4].trim();
            String t = cl[5].trim();
            String bp = cl[6].trim();
            String hr = cl[7].trim();
            String w = cl[8].trim();
            String n = cl[9].trim();
            
//            System.out.println("Patient Id: " + p);
            
            PatientInfo patient = patientService.findPatientInfoById(p);
//            System.out.println("Patient Info: " + patient);
            
            if (patient != null) {
                ConsultationInfo info = new ConsultationInfo(c,a,p,d,patient.getName(),da,t,bp,hr,w,n);
                
                consultInfo.add(info);
//                System.out.println("Added");
            }
            
        }
    }
    
    public String createConsultation(String appointId, String patientId, String doctorId, String date, String temp, String topBP, String botBP, String hr, String weight, String notes) throws InvalidVitalSignsException {
        double temperature, patientWeight;
        int topBloodPressure, bottomBloodPressure, heartRate;
        
        if (notes == null || notes.trim().isEmpty()) {
            return "Notes are required.";
        }
        
        try {
            temperature = Double.parseDouble(temp.trim());
        } catch (NumberFormatException e) {
            throw new InvalidVitalSignsException("Temperature must be a decimal number.");
        }
        
        if (temperature <= 0) {
            throw new InvalidVitalSignsException("Temperature must be greater than 0.");
        }
        
        try {topBloodPressure = Integer.parseInt(topBP.trim()); bottomBloodPressure = Integer.parseInt(botBP.trim());}
        catch (NumberFormatException e) {throw new InvalidVitalSignsException("Blood Pressure must be an integer");}
        
        if (topBloodPressure <= 0 || bottomBloodPressure <= 0) {throw new InvalidVitalSignsException("Blood Pressure must be greater than 0.");}
        
        try {heartRate = Integer.parseInt(hr.trim());}
        catch (NumberFormatException e) {throw new InvalidVitalSignsException("Heart Rate must be an integer.");}
        
        if (heartRate <= 0) {throw new InvalidVitalSignsException("Heart Rate must be greater than 0.");}
        
        try {patientWeight = Double.parseDouble(weight.trim());}
        catch (NumberFormatException e) {throw new InvalidVitalSignsException("Weight must be a decimal number.");}
        
        if (patientWeight <= 0) {throw new InvalidVitalSignsException("Weight must be greater than 0.");}
        
        String consultationId = consultRepo.createConsultationId();
        
        boolean saved = consultRepo.saveConsultation(consultationId, appointId, patientId, doctorId, date, temp, topBP, botBP, hr, weight, notes);
        
        if (!saved) {return "Failed to save consultation.";}
        
        lastCreatedConsultId = consultationId;
        
        boolean updated = appointRepo.updateStatus(appointId, "Completed");
        
        if (!updated) {return "Consultation saved, but appointment status could not be udpated.";}
        
        reloadConsultations();
        
        return null;
    }
    
    public ArrayList<ConsultationInfo> getConsultationInfo() {
        return consultInfo;
    }
    
    public String getLastCreatedConsultationId() {
        return lastCreatedConsultId;
    }
    
    public ConsultationInfo findConsultationById(String consultId) {
        for (ConsultationInfo c : consultInfo) {
            if (c.getConsultationId().equals(consultId)) {
                return c;
            }
        }
        return null;
    }
    
    public int getTotalConsultationCount(String doctorId) {
        int count = 0;

        for (ConsultationInfo c : consultInfo) {
            if (c.getDoctorId().equals(doctorId)) {
                count++;
            }
        }
        return count;
    }
}
