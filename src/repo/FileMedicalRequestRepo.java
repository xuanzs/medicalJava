/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.ArrayList;
import model.MedicalRequestInfo;

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
    
    public boolean approveMedicalRequest(String requestId) {
        boolean found = false;
        
        for (String[] request : al) {
            if(request[0].trim().equalsIgnoreCase(requestId)) {
                request[7] = "Approved";
                found = true;
                break;
            }
        }
        
        if (!found) {
            return false;
        }
        
        try {
            saveAllMedicalRequests();
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public void saveAllMedicalRequests() throws IOException {
        try (BufferedWriter bw = writer()) {
            bw.write("Request ID|Consultation ID|Patient ID|Doctor ID|RequestType|Request Date|Reason|Status");
            bw.newLine();
            
            for (String[] request : al) {
                bw.write(request[0] + "|" + request[1] + "|" + request[2] + "|" + request[3] + "|" + request[4] + "|" + request[5] + "|" + request[6] + "|" + request[7]);
                bw.newLine();
            }
        }
    }
        
    // FileReader
    public BufferedReader reader() throws IOException {
        FileReader fr = new FileReader("data/Requests.txt");
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer() throws IOException {
        FileWriter fw = new FileWriter("data/Requests.txt");
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(boolean append) throws IOException {
        FileWriter fw = new FileWriter("data/Requests.txt", append);

        return new BufferedWriter(fw);
    }
}
