/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileAppointmentRepo extends FileRepo {
    private ArrayList<String[]> al = new ArrayList<>();
    
    public FileAppointmentRepo() {
        super("data/Appointments.txt");
        loadFile();
    }
    
    @Override
    protected void processLine(String line) {
        String[] parts = line.split("\\|");
        
        if (parts.length == 6) {
            al.add(parts);
        }
    }
    
    public ArrayList<String[]> returnAllAppoint() {
        return al;
    }
    
    public boolean updateStatus(String appointmentId, String newStatus) {
        try {
            Path path = Paths.get("data/Appointments.txt");
            
            List<String> lines = Files.readAllLines(path);
            boolean found = false;
            
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split("\\|", -1);
                
                if (parts.length == 6 && parts[0].trim().equals(appointmentId.trim())) {
                    parts[5] = newStatus;
                    
                    lines.set(i, String.join("|", parts));
                    
                    found = true;
                    break;
                }
            }
            
            if (found) {
                Files.write(path, lines);
            }
            
            return found;
        } catch (IOException e ) {
            System.out.println(e);
            return false;
        }
    }
}
