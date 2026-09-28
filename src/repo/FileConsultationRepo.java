/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.ArrayList;

public class FileConsultationRepo extends FileRepo {
    private ArrayList<String[]> al = new ArrayList<>();
    
    public FileConsultationRepo() {
        super("data/Consultations.txt");
        loadFile();
    }
    
    @Override
    protected void processLine(String line) {
        String[] parts = line.split("\\|");
        
        if (parts.length == 10) {
            al.add(parts);
        }
    }
    
    public ArrayList<String[]> returnAllConsultation() {
        return al;
    }
    
    public String createConsultationId() {
        int highest = 0;
        
        for (String[] consult : al) {
            String id = consult[0].trim();
            
            if (id.startsWith("C")) {
                try {
                    int number = Integer.parseInt(id.substring(1));
                    
                    if (number > highest) {
                        highest = number;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }
        
        return String.format("C%03d", highest + 1);
    }
    
    public boolean saveConsultation(String consultId, String appointId, String patientId, String doctorId, String date, String temp, String topBP, String botBP, String hr, String weight, String notes) {
        try {
            FileWriter fw = new FileWriter("data/Consultations.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(consultId + "|" + appointId + "|" + patientId + "|" + doctorId + "|" + date + "|" + temp + "|" + topBP + "/" + botBP + "|" + hr + "|" + weight + "|" + notes);
            bw.newLine();
            
            bw.close();
            fw.close();
            
            al.add(new String[] {consultId, appointId, patientId, doctorId, date, temp, topBP + "/" + botBP, hr, weight, notes});
            
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
}
