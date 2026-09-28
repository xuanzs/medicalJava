/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

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
}
