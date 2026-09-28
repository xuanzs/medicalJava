/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.AppointmentInfo;
import model.PatientInfo;
import repo.FileAppointmentRepo;

public class AppointmentService {
    private FileAppointmentRepo appointRepo = new FileAppointmentRepo();
    private PatientService patientService = new PatientService();
    
    private ArrayList<AppointmentInfo> appointmentInfo = new ArrayList<>();
    
    public AppointmentService() {
        ArrayList<String[]> appointList = appointRepo.returnAllAppoint();
        
        for (String[] al : appointList) {
            String a = al[0].trim();
            String p = al[1].trim();
            String d = al[2].trim();
            String da = al[3].trim();
            String t = al[4].trim();
            String s = al[5].trim();
            
            PatientInfo patient = patientService.findPatientInfoById(p);
            
            if (patient != null) {
                AppointmentInfo info = new AppointmentInfo(a,p,patient.getName(),d,da,t,s);
                
                appointmentInfo.add(info);
            }
        }
    }
    
    public ArrayList<AppointmentInfo> getAppointmentInfo() {
        return appointmentInfo;
    }
    
    public int getTodayAppointmentCount(String doctorId) {
        int count = 0;
        String today = java.time.LocalDate.now().toString();

        for (AppointmentInfo a : appointmentInfo) {
            if (a.getDoctorId().equals(doctorId) && a.getDate().equals(today) && a.getStatus().toLowerCase().equals("booked")) {
                count++;
            }
        }

        return count;
    }
}
