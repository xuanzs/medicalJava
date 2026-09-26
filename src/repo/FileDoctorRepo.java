/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
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
                String[] parts = line.split(",");
                
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
}
