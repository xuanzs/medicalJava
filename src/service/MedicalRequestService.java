/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.MedicalRequestInfo;
import model.PatientInfo;
import repo.FileMedicalRequestRepo;

/**
 *
 * @author xuanchen
 */
public class MedicalRequestService {
    private FileMedicalRequestRepo requestRepo;
    private PatientService patientService;
    
    private ArrayList<String[]> requestList;
    private ArrayList<MedicalRequestInfo> requestInfo = new ArrayList<>();
    
    public MedicalRequestService() {
//        requestRepo = new FileMedicalRequestRepo();
        patientService = new PatientService();
        
        reloadMedicalRequest();
    }
    
    public void reloadMedicalRequest() {
        requestRepo = new FileMedicalRequestRepo();
        requestList = requestRepo.returnAllMedicalRequest();
        requestInfo.clear();
        
        for (String[] rl : requestList) {
            String r = rl[0].trim();
            String c = rl[1].trim();
            String p = rl[2].trim();
            String d = rl[3].trim();
            String rt = rl[4].trim();
            String rd = rl[5].trim();
            String rea = rl[6].trim();
            String s = rl[7].trim();
            
            PatientInfo patient = patientService.findPatientInfoById(p);
            
            if (patient != null) {
                MedicalRequestInfo info = new MedicalRequestInfo(r, c, p, d, patient.getName(), rd, rt, s, rea);
                
                requestInfo.add(info);
            }
        }
    }
    
    public String createMedicalRequest(String consultId, String patientId, String doctorId, String requestType, String requestDate, String reason, String status) {
        if (requestType.toLowerCase().equals("select") || requestType.trim().isEmpty()) {
            return "Request Type is required.";
        }
        
        if (reason == null || reason.trim().isEmpty()) {
            return "Reason is required.";
        }
        
        if (reason.contains("|")) {return "Reason cannot contain | symbol.";}
        
        reason = reason.replace("\r", " ").replace("\n", " ").trim();
        
        String medicalRequestId = requestRepo.createMedicalRequestId();
        
        boolean saved = requestRepo.saveMedicalRequest(medicalRequestId, consultId, patientId, doctorId, requestType, requestDate, reason, status);
        if (!saved) {return "Failed to save medical request.";}
        
        reloadMedicalRequest();
        return null;
    }
    
    public boolean hasMedicalRequest(String consultId) {
        return requestRepo.existsByConsultationId(consultId);
    }
    
    public ArrayList<MedicalRequestInfo> getMedicalRequestInfo() {
        return requestInfo;
    }
    
    public MedicalRequestInfo findMedicalRequestById(String requestId) {
        for (MedicalRequestInfo mr : requestInfo) {
            if (mr.getRequestId().equals(requestId)) {
                return mr;
            }
        }
        return null;
    }
    
    public String approveMedicalRequest(String requestId) {
        if (requestId == null || requestId.trim().isEmpty()) {
            return "Please select a request.";
        }
        
        MedicalRequestInfo request = findMedicalRequestById(requestId);
        
        if (request == null) {
            return "Request not found.";
        }
        
        if (!request.getStatus().equalsIgnoreCase("Pending")) {
            return "This request has already been processed";
        }
        
        boolean approved = requestRepo.approveMedicalRequest(requestId);
        
        if (!approved) {
            return "Failed to approve request.";
        }
        
        reloadMedicalRequest();
        return null;
    }
    
    public String getDoctorIdByRequestId(String requestId) {
        for (String[] request : requestList) {
            if (request[0].trim().equalsIgnoreCase(requestId)) {
                return request[3].trim();
            }
        }

        return "";
    }
    
    public int getPendingRequestCount(String doctorId) {
        int count = 0;
        
        for (String[] r : requestList) {
            String d = r[3].trim();
            String s = r[7].trim();
            
            if (d.equals(doctorId) && s.toLowerCase().equals("pending")) {
                count++;
            }
        }
        
        return count;
    }
}
