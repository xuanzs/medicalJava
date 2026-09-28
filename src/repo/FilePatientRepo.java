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
            FileReader fr = new FileReader("data/Patients.txt");
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
}
