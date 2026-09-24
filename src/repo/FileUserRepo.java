/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.HashMap;
import model.User;
//import model.Admin;
//import model MedicalManager;
import model.Doctor;
//import model patient;

public class FileUserRepo {
    private final HashMap<String, User> map = new HashMap<>();
    
    public FileUserRepo() {
        try {
            FileReader fr = new FileReader("data/User.txt");
            BufferedReader br = new BufferedReader(fr);
            
            br.readLine();
            String line;
            while((line = br.readLine()) != null) {
                
                String[] parts = line.split(",");
                
                if (parts.length == 7) {
                    
                    String id = parts[0].trim();
                    String n = parts[1].trim();
                    String e = parts[2].trim().toLowerCase();
                    String p = parts[3];
                    String ph = parts[4].trim();
                    String g = parts[5].trim();
                    String r = parts[6].trim();
                    
                    User user = null;
                    
                    switch(r.toLowerCase()) {
//                        case "admin":
//                            user = new Admin(id, n, e, p, ph, g, r);
//                            break;
//                        case "medicalmanager":
//                            user = new MedicalManager(id, n, e, p, ph, g, r);
//                            break;
                        case "doctor":
                            user = new Doctor(id, n, e, p, ph, g, r);
                            break;
//                        case "patient":
//                            user = new Patient(id, n, e, p, ph, g, r);
//                            break;
                    }
                    
                    if (user != null) {map.put(e, user);}
                    
                } else {
                    System.out.println("Row invalid");
                }
            }
            br.close();
            fr.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    
    public User findByEmail(String email) {
        return map.get(email.trim().toLowerCase());
    }
    
}
