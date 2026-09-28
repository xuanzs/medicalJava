/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import model.Patient;


public class FilePatientRepo {
    private HashMap<String, Patient> map = new HashMap<>();
    private ArrayList<String[]> al = new ArrayList<>();
    
    public FilePatientRepo() {
        try {
            FileReader fr = new FileReader("data/patients.txt");
            BufferedReader br = new BufferedReader(fr);
            
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                
                if (parts.length == 5) {
                    al.add(parts);
                    String p = parts[0].trim();
                    String u = parts[1].trim();
                    String dob = parts[2].trim();
                    String bt = parts[3].trim();
                    String a = parts[4].trim();
                    
                    Patient patient = new Patient(p, u, dob, bt, a);
                    map.put(p, patient);
                }
            }
            
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public ArrayList<String[]> returnAllPatient() {
        return al;
    }
    
    public String generatePatientId() {
        int maxId = 0;
        for (String[] patient : al) {
            try {
                int number = Integer.parseInt(patient[0].trim().substring(1));
                if (number > maxId) maxId = number;
            } catch (Exception e) {
                System.out.println("Invalid Patient ID: " + patient[0]);
            }
        }
        return String.format("P%03d", maxId + 1);
    }

    public boolean createPatient(String userId) {
        for (String[] patient : al) {
            if (patient[1].trim().equalsIgnoreCase(userId)) return false;
        }
        String[] newPatient = new String[]{generatePatientId(), userId, "Not Set", "Not Set", "Not Set"};
        al.add(newPatient);
        try {
            savePatients();
            return true;
        } catch (IOException e) {
            al.remove(newPatient);
            System.out.println(e);
            return false;
        }
    }
    
    public boolean deleteByUserId(String userId) {
        String[] removed = null;
        for (String[] patient : al) {
            if (patient[1].trim().equalsIgnoreCase(userId)) {
                removed = patient;
                break;
            }
        }
        if (removed == null) return false;
        al.remove(removed);
        try {
            savePatients();
            return true;
        } catch (IOException e) {
            al.add(removed);
            System.out.println(e);
            return false;
        }
    }

    private void savePatients() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/patients.txt"))) {
            bw.write("Patient ID|User ID|Date Of Birth|Blood Type|Address");
            bw.newLine();
            for (String[] patient : al) {
                bw.write(patient[0].trim() + "|" + patient[1].trim() + "|" + patient[2].trim() + "|" + patient[3].trim() + "|" + patient[4].trim());
                bw.newLine();
            }
        }
    }
}
