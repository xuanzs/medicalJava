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
public class FileMedicalRequestRepo extends FileRepo {
    private ArrayList<String[]> al = new ArrayList<>();
    
    public FileMedicalRequestRepo() {
        super("data/Requests.txt");
        loadFile();
    }
    
    @Override
    protected void processLine(String line) {
        String[] parts = line.split("\\|");
        
        if (parts.length == 8) {
            al.add(parts);
        }
    }
    
    public ArrayList<String[]> returnAllMedicalRequest() {
        return al;
    }
    
    public String createMedicalRequestId() {
        int highest = 0;
        
        for (String[] request : al) {
            String id = request[0].trim();
            
            if (id.startsWith("R")) {
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
        return String.format("R%03d", highest + 1);
    }
    
    public boolean saveMedicalRequest(String requestId, String consultId, String patientId, String doctorId, String requestType, String requestDate, String reason, String status) {
        try {
            FileWriter fw = new FileWriter("data/Requests.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(requestId + "|" + consultId + "|" + patientId + "|" + doctorId + "|" + requestType + "|" + requestDate + "|" + reason + "|" + status);
            bw.newLine();
            
            bw.close();
            fw.close();
            
            al.add(new String[] {requestId, consultId, patientId, doctorId, requestType, requestDate, reason, status});
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean existsByConsultationId(String consultId) {
        for (String[] r : al) {
            if (r[1].trim().equals(consultId.trim())) {
                return true;
            }
        }
        return false;
    }
}
