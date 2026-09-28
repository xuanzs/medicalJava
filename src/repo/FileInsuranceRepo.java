/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.*;
import model.InsuranceNetwork;

/**
 *
 * @author wongkingsen
 */
public class FileInsuranceRepo {
    private final HashMap<String, InsuranceNetwork> map = new HashMap<>();
    
    public FileInsuranceRepo() {
        try (BufferedReader br = reader()) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length == 3) {
                    String insuranceId = parts[0].trim();
                    String insuranceName = parts[1].trim();
                    String status = parts[2].trim();
                    
                    InsuranceNetwork insurance = new InsuranceNetwork(insuranceId, insuranceName, status);
                    
                    map.put(insuranceId, insurance);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public Collection<InsuranceNetwork> getAllInsurance() {
        return map.values();
    }
    
    public InsuranceNetwork findByInsuranceId(String insuranceId) {
        return map.get(insuranceId);
    }
    
    public boolean updateInsurance(String insuranceId, String status) {
        InsuranceNetwork insurance = map.get(insuranceId);
        
        if (insurance == null) {
            return false;
        }
        
        insurance.setStatus(status);
        
        try {
            saveInsurance();
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public void saveInsurance() throws IOException {
        try (BufferedWriter bw = writer()) {
            bw.write("InsuranceId|InsuranceName|Status");
            bw.newLine();
            
            for (InsuranceNetwork insurance : map.values()) {
                bw.write(insurance.getInsuranceId() + "," + insurance.getInsuranceName() + "," + insurance.getStatus());
                bw.newLine();
            }
        }
    }
    
    // FileReader
    public BufferedReader reader() throws IOException {
        FileReader fr = new FileReader("data/insuranceNetwork.txt");
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer() throws IOException {
        FileWriter fw = new FileWriter("data/insuranceNetwork.txt");
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(boolean append) throws IOException {
        FileWriter fw = new FileWriter("data/insuranceNetwork.txt", append);

        return new BufferedWriter(fw);
    }
}
