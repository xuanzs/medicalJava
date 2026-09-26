/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import javax.swing.JOptionPane;
import repo.FileUserRepo;
import model.User;

/**
 *
 * @author wongkingsen
 */
public class FileAssignmentRepo {
    FileUserRepo userRepo = new FileUserRepo();
    
    public boolean doctorAssigned(String doctorId) throws IOException {
        try (BufferedReader br = reader();) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String existingDoctorId = parts[0].trim();
                    if (existingDoctorId.equalsIgnoreCase(doctorId)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public void assignDoctor(String doctorId, String managerId) {
        User doctor = userRepo.findByUserId(doctorId);
        User manager = userRepo.findByUserId(managerId);
        
        try {
            if (!doctorAssigned(doctorId)) {
                try (BufferedWriter bw = writer(true);) {
                    bw.write(doctorId + "," + managerId + "\n");
                    
                    JOptionPane.showMessageDialog(null, "Doctor " + doctor.getName() + "assigned successfully to Manager " + manager.getName());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // FileReader
    public BufferedReader reader() throws IOException {
        FileReader fr = new FileReader("data/assignment.txt");
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer() throws IOException {
        FileWriter fw = new FileWriter("data/assignment.txt");
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(boolean append) throws IOException {
        FileWriter fw = new FileWriter("data/assignment.txt", append);

        return new BufferedWriter(fw);
    }
}
