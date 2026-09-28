/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author xuanchen
 */
public class FilePrescriptionRepo extends FileRepo {
    private ArrayList<String[]> al = new ArrayList<>();
    
    public FilePrescriptionRepo() {
        super("data/Prescriptions.txt");
        loadFile();
    }
    
    @Override
    protected void processLine(String line) {
        String[] parts = line.split("\\|");
        
        if (parts.length == 10) {
            al.add(parts);
        }
    }
    
    public ArrayList<String[]> returnAllPrescription() {
        return al;
    }
    
    public String createPrescriptionId() {
        int highest = 0;
        
        for (String[] prescrip : al) {
            String id = prescrip[0].trim();
            
            if (id.startsWith("PR")) {
                try {
                    int number = Integer.parseInt(id.substring(2));
                    
                    if (number > highest) {
                        highest = number;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }
        return String.format("PR%03d", highest + 1);
    }
    
    public boolean savePrescription(String prescriptId, String consultId, String patientId, String doctorId, String medName, String dosage, String freq, String duration, String instructs, String issueDate) {
        try {
            FileWriter fw = new FileWriter("data/Prescriptions.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(prescriptId + "|" + consultId + "|" + patientId + "|" + doctorId + "|" + medName + "|" + dosage + "|" + freq + "|" + duration + "|" + instructs + "|" + issueDate);
            bw.newLine();
            
            bw.close();
            fw.close();
            
            al.add(new String[] {prescriptId, consultId, patientId, doctorId, medName, dosage, freq, duration, instructs, issueDate});
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean existsByConsultationId(String consultId) {
        for (String[] p : al) {
            if (p[1].trim().equals(consultId.trim())) {
                return true;
            }
        }

        return false;
    }
}
