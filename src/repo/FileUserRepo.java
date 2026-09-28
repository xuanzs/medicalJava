/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import model.User;

public class FileUserRepo {
    private final HashMap<String, User> map = new HashMap<>();
    
    public FileUserRepo() {
        try (BufferedReader br = reader()) {
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
                    
                    User user = new User(id, n, e, p, ph, g, r);
                    
                    if (user != null) {map.put(e, user);}
                    
                } else {
                    System.out.println("Row invalid");
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    
    public Collection<User> getAllUsers() {
        return map.values();
    }
    
    public User findByEmail(String email) {
        return map.get(email.trim().toLowerCase());
    }
    
    public User findByUserId(String userId) {
        for (User user : map.values()) {
            if (user.getUserId().equalsIgnoreCase(userId)) {
                return user;
            }
        }
        return null;
    }
    
    // FileReader
    public BufferedReader reader() throws IOException {
        FileReader fr = new FileReader("data/User.txt");
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer() throws IOException {
        FileWriter fw = new FileWriter("data/User.txt");
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(boolean append) throws IOException {
        FileWriter fw = new FileWriter("data/User.txt", append);

        return new BufferedWriter(fw);
    }
    
    // Generate userId
    public String generateUserId() {
        int maxId = 0;
        
        for (User user : map.values()) {
            String id = user.getUserId();
            
            try {
                int number = Integer.parseInt(id.substring(3));
                
                if (number > maxId) {
                    maxId = number;
                }
            } catch (Exception e) {
                System.out.println("Invalid User ID: " + id);
            }
        }
        
        return String.format("Uid%03d", maxId + 1);
    }
    
    // Create user
    public boolean createUser(String name, String email, String password, String phone, String gender, String role) {       
        if (map.containsKey(email)) {
            return false;
        }
        
        String userId = generateUserId();
        User user = new User(userId, name, email, password, phone, gender, role);

        map.put(email, user);
        
        try {
            saveUsers();
            return true;
        } catch (IOException e) {
            map.remove(email);
            System.out.println(e);
            return false;
        }
    }
    
    // Delete user
    public boolean deleteUser(String userId) {
        User user = findByUserId(userId);
        
        if (user == null) {
            return false;
        }
        
        map.remove(user.getEmail().trim().toLowerCase());
        
        try {
            saveUsers();
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    private void saveUsers() throws IOException {
        try (BufferedWriter bw = writer()) {
            bw.write("UserId|Name|Email|Password|Phone|Gender|Role");
            bw.newLine();
            
            for (User user : map.values()) {
                bw.write(user.getUserId() + "," + user.getName() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getPhone() + "," + user.getGender() + "," + user.getRole());
                bw.newLine();
            }
        }
    }
}
