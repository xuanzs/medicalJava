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
                String[] parts = line.split(",");
                
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
