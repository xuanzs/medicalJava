/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import model.Admin;

/**
 *
 * @author wongkingsen
 */
public class FileAdminRepo {
    HashMap<String, Admin> map = new HashMap<>();
    
    public FileAdminRepo() {
        try (BufferedReader br = reader()) {
            String line;
            br.readLine();
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                
                if (parts.length == 2) {
                    String adminId = parts[0].trim();
                    String userId = parts[1].trim();

                    Admin admin = new Admin(adminId, userId);
                    map.put(userId, admin);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public String generateAdminId() {
        int maxId = 0;
        for (Admin admin : map.values()) {
            try {
                int number = Integer.parseInt(admin.getAdminId().substring(1));
                if (number > maxId) maxId = number;
            } catch (Exception e) {
                System.out.println("Invalid Admin ID: " + admin.getAdminId());
            }
        }
        return String.format("A%03d", maxId + 1);
    }

    public boolean createAdmin(String userId) {
        if (map.containsKey(userId)) return false;
        Admin admin = new Admin(generateAdminId(), userId);
        map.put(userId, admin);
        try {
            saveAdmins();
            return true;
        } catch (IOException e) {
            map.remove(userId);
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteByUserId(String userId) {
        Admin removed = map.remove(userId);
        if (removed == null) return false;
        try {
            saveAdmins();
            return true;
        } catch (IOException e) {
            map.put(userId, removed);
            System.out.println(e);
            return false;
        }
    }

    private void saveAdmins() throws IOException {
        try (BufferedWriter bw = writer()) {
            bw.write("adminId,userId");
            bw.newLine();
            for (Admin admin : map.values()) {
                bw.write(admin.getAdminId() + "," + admin.getUserId());
                bw.newLine();
            }
        }
    }
    
    public Admin findByUserId(String userId) {
        return map.get(userId);
    }

    // FileReader
    public BufferedReader reader() throws IOException {
        FileReader fr = new FileReader("data/Admin.txt");
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer() throws IOException {
        FileWriter fw = new FileWriter("data/Admin.txt");
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(boolean append) throws IOException {
        FileWriter fw = new FileWriter("data/Admin.txt", append);

        return new BufferedWriter(fw);
    }
}
