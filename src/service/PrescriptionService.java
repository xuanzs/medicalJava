/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.PatientInfo;
import model.PrescriptionInfo;
import repo.FileConsultationRepo;
import repo.FilePrescriptionRepo;

/**
 *
 * @author xuanchen
 */
public class PrescriptionService {
    private FilePrescriptionRepo prescriptRepo;
    private PatientService patientService;
    
    private ArrayList<String[]> prescriptList;
    private ArrayList<PrescriptionInfo> prescriptInfo = new ArrayList<>();
    
    public PrescriptionService() {
        prescriptRepo = new FilePrescriptionRepo();
        patientService = new PatientService();
        
        reloadPrescription();
        
    }
    
    public void reloadPrescription() {
        prescriptRepo = new FilePrescriptionRepo();
        prescriptList = prescriptRepo.returnAllPrescription();
        prescriptInfo.clear();
        
        for (String[] pl : prescriptList) {
            String pr = pl[0].trim();
            String c = pl[1].trim();
            String p = pl[2].trim();
            String mn = pl[4].trim();
            String d = pl[5].trim();
            String f = pl[6].trim();
            String dur = pl[7].trim();
            String i = pl[8].trim();
            String id = pl[9].trim();
            
            PatientInfo patient = patientService.findPatientInfoById(p);
            
            if (patient != null) {
                PrescriptionInfo info = new PrescriptionInfo(pr,c,p,patient.getName(),id,mn,d,f,dur,i);
                
                prescriptInfo.add(info);
            }
        }
    }
    
    public String createPrescription(String consultId, String patientId, String doctorId, String medName, String dosage, String freq, String duration, String instructions, String issueDate) {
        if (medName == null || medName.trim().isEmpty()) {
            return "Medication Name is required.";
        }
        
        if (dosage == null || dosage.trim().isEmpty()) {
            return "Dosage is required.";
        }
        
        if (freq == null || freq.trim().isEmpty()) {
            return "Frequency is required.";
        }
        
        if (duration == null || duration.trim().isEmpty()) {
            return "Duration is required.";
        }
        
        if (instructions == null || instructions.trim().isEmpty()) {
            return "Instructions is required.";
        }
        
        String prescriptionId = prescriptRepo.createPrescriptionId();
        
        boolean saved = prescriptRepo.savePrescription(prescriptionId, consultId, patientId, doctorId, medName, dosage, freq, duration, instructions, issueDate);
        if (!saved) {return "Failed to save prescription.";}
        
        reloadPrescription();
        
        return null;
        
    }
    
    public boolean hasPrescription(String consultId) {
        return prescriptRepo.existsByConsultationId(consultId);
    }
    
    public ArrayList<PrescriptionInfo> getPrescriptionInfo() {
        return prescriptInfo;
    }
    
    public PrescriptionInfo findPrescriptionById(String prescriptId) {
        for (PrescriptionInfo p : prescriptInfo) {
            if (p.getPrescriptId().equals(prescriptId)) {
                return p;
            }
        }
        return null;
    }
}
