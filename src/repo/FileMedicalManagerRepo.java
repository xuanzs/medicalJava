/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.ArrayList;
import medManager.MedicalManager;

public class FileMedicalManagerRepo extends FileRepo {
    private MedicalManager medManager;
    private ArrayList<String[]> al = new ArrayList<>();
    
    public FileMedicalManagerRepo() {
        super("data/MedicalManager.txt");
        loadFile();
    }
    
    @Override
    protected void processLine(String line) {
        String[] parts = line.split(",");
        
        if (parts.length == 2) {
            String mm = parts[0].trim();
            String u = parts[1].trim();
            
            al.add(parts);
            medManager = new MedicalManager(mm, u);
        }
    }
    
    public ArrayList<String[]> returnAllMedicalManager() {
        return al;
    }
    
    public String generateMedicalManagerId() {
        int maxId = 0;
        for (String[] manager : al) {
            try {
                int number = Integer.parseInt(manager[0].trim().substring(1));
                if (number > maxId) maxId = number;
            } catch (Exception e) {
                System.out.println("Invalid Medical Manager ID: " + manager[0]);
            }
        }
        return String.format("M%03d", maxId + 1);
    }

    public boolean createMedicalManager(String userId) {
        for (String[] manager : al) {
            if (manager[1].trim().equalsIgnoreCase(userId)) return false;
        }
        String[] newManager = new String[]{generateMedicalManagerId(), userId};
        al.add(newManager);
        try {
            saveMedicalManagers();
            return true;
        } catch (IOException e) {
            al.remove(newManager);
            System.out.println(e);
            return false;
        }
    }
    
    private void saveMedicalManagers() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Medical Manager Id,User Id");
            bw.newLine();
            for (String[] manager : al) {
                bw.write(manager[0].trim() + "," + manager[1].trim());
                bw.newLine();
            }
        }
    }
    
    public boolean deleteByUserId(String userId) {
        String[] removed = null;
        for (String[] manager : al) {
            if (manager[1].trim().equalsIgnoreCase(userId)) {
                removed = manager;
                break;
            }
        }
        if (removed == null) return false;
        al.remove(removed);
        try {
            saveMedicalManagers();
            return true;
        } catch (IOException e) {
            al.add(removed);
            System.out.println(e);
            return false;
        }
    }
}
