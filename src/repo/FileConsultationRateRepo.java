/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.*;
import model.ConsultationRate;

/**
 *
 * @author wongkingsen
 */
public class FileConsultationRateRepo {
    private final HashMap<String, ConsultationRate> map = new HashMap<>();
    
    public FileConsultationRateRepo() {
        try (BufferedReader br = reader()) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length == 3) {
                    String rateId = parts[0].trim();
                    String consultationType = parts[1].trim();
                    double baseRate = Double.parseDouble(parts[2].trim());
                    
                    ConsultationRate rate = new ConsultationRate(rateId, consultationType, baseRate);
                    
                    map.put(rateId, rate);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public Collection<ConsultationRate> getAllRates() {
        return map.values();
    }
    
    public ConsultationRate findByRateId(String rateId) {
        return map.get(rateId);
    }
    
    public boolean updateRate(String rateId, double newRate) {
        ConsultationRate rate = map.get(rateId);
        
        if (rate == null) {
            return false;
        }
        
        rate.setBaseRate(newRate);
        
        try {
            saveRates();
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public void saveRates() throws IOException {
        try (BufferedWriter bw = writer()) {
            bw.write("RateId|ConsultationType|BaseRate");
            bw.newLine();
            
            for (ConsultationRate rate : map.values()) {
                bw.write(rate.getRateId() + "," + rate.getConsultationType() + "," + String.format("%.2f", rate.getBaseRate()));
                bw.newLine();
            }
        }
    }
    
    // FileReader
    public BufferedReader reader() throws IOException {
        FileReader fr = new FileReader("data/consultationRate.txt");
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer() throws IOException {
        FileWriter fw = new FileWriter("data/consultationRate.txt");
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(boolean append) throws IOException {
        FileWriter fw = new FileWriter("data/consultationRate.txt", append);

        return new BufferedWriter(fw);
    }
}
