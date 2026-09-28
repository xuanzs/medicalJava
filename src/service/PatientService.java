/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.PatientInfo;
import model.User;
import repo.FilePatientRepo;
import repo.FileUserRepo;

public class PatientService {
    private FileUserRepo userRepo = new FileUserRepo();
    private FilePatientRepo patientRepo = new FilePatientRepo();
    
    private ArrayList<String[]> patientList;
    private ArrayList<PatientInfo> patientInfo = new ArrayList<>();
    
    public PatientService() {
        patientList = patientRepo.returnAllPatient();
        
        for (String[] pl : patientList) {
            String p = pl[0];
            String u = pl[1];
            String dob = pl[2];
            String bt = pl[3];
            String a = pl[4];
            
            System.out.println("PatientID: " + p);
            System.out.println("user id from patienttxt: " + u);
            
            User user = userRepo.findByUserId(u);
            System.out.println("Found user: " + user);
            
            if (user != null) {
                PatientInfo info = new PatientInfo(p,u,user.getName(),user.getGender(),user.getPhone(),dob,bt,a);
                
                patientInfo.add(info);
                System.out.println("Patient added");
            }
        }
        System.out.println("Total patient info: " + patientInfo.size());
    }
    
    public ArrayList<PatientInfo> getPatientInfo() {
        return patientInfo;
    }
    
    public PatientInfo findPatientInfoById(String patientId) {
        for (PatientInfo p : patientInfo) {
            if (p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        return null;
    }
    
    public PatientInfo findPatientInfoByUserId(String userId) {
        for (PatientInfo p : patientInfo) {
            if (p.getUserId().equals(userId)) {
                return p;
            }
        }
        return null;
    }
    
}
