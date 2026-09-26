/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.HashMap;
import model.ConsultationRoom;
import model.HospitalAsset;
import model.ImagingRoom;
import model.InpatientWard;
import model.Lab;

/**
 *
 * @author wongkingsen
 */
public class FileAssetRepo {
    private final HashMap<String, HospitalAsset> map = new HashMap<>();
    
    public FileAssetRepo() {
        try (BufferedReader br= reader();) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                
                if (parts.length == 6) {
                    String id = parts[0].trim();
                    String n = parts[1].trim();
                    String t = parts[2].trim();
                    String l = parts[3].trim();
                    String s = parts[4].trim();
                    String d = parts[5].trim();
                    
                    HospitalAsset asset = createAssetObject(id, n, t, l, s, d);
                    
                    if (asset != null) {map.put(id, asset);}
                } else {
                    System.out.println("Row invalid");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }  
    
    // Generate assetId
//    public String generateUserId() throws IOException {
//        String lastLine = null;
//        String lastUserId;
//        
//        try (BufferedReader br = reader("data/User.txt");) {
//            br.readLine();
//            String line;
//            while((line = br.readLine()) != null) {
//                if (!line.trim().isEmpty()) {
//                    lastLine = line;
//                }
//            }
//        }
//                
//        if (lastLine != null) {
//            String[] parts = lastLine.split(",");
//            lastUserId = parts[0].trim();
//        } else {
//            return "Uid001";
//        }
//        
//        int number = Integer.parseInt(lastUserId.substring(3)) + 1;
//        
//        return String.format("Uid%03d", number);
//    }
    
    // Create user object
    public HospitalAsset createAssetObject(String assetId, String assetName, String assetType, String location, String status, String departmentId) {
        HospitalAsset asset = null;
        
        switch(assetType.toLowerCase()) {
            case "consultationroom":
                asset = new ConsultationRoom(assetId, assetName, assetType, location, status, departmentId);
                break;
            case "inpatientward":
                asset = new InpatientWard(assetId, assetName, assetType, location, status, departmentId);
                break;
            case "lab":
                asset = new Lab(assetId, assetName, assetType, location, status, departmentId);
                break;
            case "imagingroom":
                asset = new ImagingRoom(assetId, assetName, assetType, location, status, departmentId);
                break;
        }
        
        return asset;
    }
    
    // FileReader
    public BufferedReader reader() throws IOException {
        FileReader fr = new FileReader("data/hospitalAsset.txt");
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer() throws IOException {
        FileWriter fw = new FileWriter("data/hospitalAsset.txt");
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(boolean append) throws IOException {
        FileWriter fw = new FileWriter("data/hospitalAsset.txt", append);

        return new BufferedWriter(fw);
    }
}
