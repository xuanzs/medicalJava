/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import model.Doctor;

public class FileDoctorRepo {
    HashMap<String, Doctor> map = new HashMap<>();
    
    public FileDoctorRepo() {
        try {
            FileReader fr = new FileReader("data/Doctor.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            br.readLine();
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                
                if (parts.length == 4) {
                    String d = parts[0].trim();
                    String u = parts[1].trim();
                    String s = parts[2].trim();
                    String mm = parts[3].trim();
                    
                    Doctor doctor = new Doctor(d,u,s,mm);
                    map.put(u, doctor);
                }
            }
            
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public Doctor findByUserId(String userId) {
        return map.get(userId);
    }
    
    public Collection<Doctor> getAllDoctors() {
        return map.values();
    }
    
    public boolean assignMedicalManager(String doctorUserId, String managerUserId) {
        Doctor doctor = map.get(doctorUserId);
        
        if (doctor == null) {
            return false;
        }
        
        doctor.setMedicalManagerId(managerUserId);
        
        try {
            saveDoctors();
            
            return true;
        } catch (IOException e) {
            System.out.println(e);
            
            return false;
        }
    }
    
    public void saveDoctors() throws IOException {
        try (BufferedWriter bw = writer()) {
            bw.write("DoctorId|UserId|Specialization|MedicalManagerId");
            bw.newLine();
            
            for (Doctor doctor : map.values()) {
                bw.write(doctor.getDoctorId() + "," + doctor.getUserId() + "," + doctor.getSpecialization() + "," + doctor.getMedicalManagerId());
                bw.newLine();
            }
        }
    }
    
    public boolean createDoctor(String userId) {
        if (map.containsKey(userId)) {
            return false;
        }

        Doctor doctor = new Doctor(generateDoctorId(), userId, "Not Set", "");
        map.put(userId, doctor);

        try {
            saveDoctors();
            return true;
        } catch (IOException e) {
            map.remove(userId);
            System.out.println(e);
            return false;
        }
    }
    
    public String generateDoctorId() {
        int maxId = 0;

        for (Doctor doctor : map.values()) {
            try {
                int number = Integer.parseInt(doctor.getDoctorId().substring(1));
                if (number > maxId) {
                    maxId = number;
                }
            } catch (Exception e) {
                System.out.println("Invalid Doctor ID: " + doctor.getDoctorId());
            }
        }

        return String.format("D%03d", maxId + 1);
    }
        
    // FileReader
    public BufferedReader reader() throws IOException {
        FileReader fr = new FileReader("data/Doctor.txt");
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer() throws IOException {
        FileWriter fw = new FileWriter("data/Doctor.txt");
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(boolean append) throws IOException {
        FileWriter fw = new FileWriter("data/Doctor.txt", append);

        return new BufferedWriter(fw);
    }
}
