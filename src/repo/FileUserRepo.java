/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import model.User;
import model.Admin;
import model.MedicalManager;
import model.Doctor;
import model.Patient;

public class FileUserRepo {
    private final HashMap<String, User> map = new HashMap<>();
    
    public FileUserRepo() {
        try (BufferedReader br = reader("data/User.txt");) {
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
                    
                    User user = createUserObject(id, n, e, p, ph, g, r);
                    
                    if (user != null) {map.put(e, user);}
                    
                } else {
                    System.out.println("Row invalid");
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    
    public User findByEmail(String email) {
        return map.get(email.trim().toLowerCase());
    }
    
    // Check duplicate email
    public boolean emailExists(String email) {
        return map.containsKey(email.trim().toLowerCase());
    }
    
    // FileReader
    public BufferedReader reader(String filePath) throws IOException {
        FileReader fr = new FileReader(filePath);
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(String filePath, boolean append) throws IOException {
        FileWriter fw = new FileWriter(filePath, append);

        return new BufferedWriter(fw);
    }
    
    // Generate userId
    public String generateUserId() throws IOException {
        String lastLine = null;
        String lastUserId;
        
        try (BufferedReader br = reader("data/User.txt");) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lastLine = line;
                }
            }
        }
                
        if (lastLine != null) {
            String[] parts = lastLine.split(",");
            lastUserId = parts[0].trim();
        } else {
            return "Uid001";
        }
        
        int number = Integer.parseInt(lastUserId.substring(3)) + 1;
        
        return String.format("Uid%03d", number);
    }
    
    // Create user
    public void createUser(String name, String email, String password, String phone, String gender, String role) {       
        try {
            String userId = generateUserId();
            String userData = userId + "," + name + "," + email + "," + password + "," + phone + "," + gender + "," + role + "\n";
            
            try (BufferedWriter bw = writer("data/User.txt", true);) {
                bw.write(userData);

                JOptionPane.showMessageDialog(null, "User created successfully.");
            }
            
            User user = createUserObject(userId, name, email, password, phone, gender, role);

            if (user != null) {map.put(email, user);}
            
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    
    // Delete user
    
    
    // Create user object
    public User createUserObject(String userId, String name, String email, String password, String phone, String gender, String role) {
        User user = null;
        
        switch(role.toLowerCase()) {
            case "admin":
                user = new Admin(userId, name, email, password, phone, gender, role);
                break;
            case "medicalmanager":
                user = new MedicalManager(userId, name, email, password, phone, gender, role);
                break;
            case "doctor":
                user = new Doctor(userId, name, email, password, phone, gender, role);
                break;
            case "patient":
                user = new Patient(userId, name, email, password, phone, gender, role);
                break;
        }
        
        return user;
    }
}
