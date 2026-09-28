/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.util.*;
import model.HospitalAsset;

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
                
                if (parts.length == 9) {
                    String id = parts[0].trim();
                    String n = parts[1].trim();
                    String t = parts[2].trim();
                    String l = parts[3].trim();
                    String s = parts[4].trim();
                    String d = parts[5].trim();
                    String o = parts[6].trim();
                    String c = parts[7].trim();
                    int ca = Integer.parseInt(parts[8].trim());
                    
                    HospitalAsset asset = new HospitalAsset(id, n, t, l, s, d, o, c, ca);
                    
                    if (asset != null) {map.put(id, asset);}
                } else {
                    System.out.println("Row invalid");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }  
    
    public HospitalAsset findByAssetId(String assetId) {
        return map.get(assetId);
    }
    
    public Collection<HospitalAsset> getAllAssets() {
        return map.values();
    }
    
    public boolean updateAsset(String assetId, String status, String departmentId, String openingTime, String closingTime, int capacity) {
        HospitalAsset asset = map.get(assetId);
        
        if (asset != null) {
            asset.setStatus(status);
            asset.setDepartmentId(departmentId);
            asset.setOpeningTime(openingTime);
            asset.setClosingTime(closingTime);
            asset.setCapacity(capacity);
            
            try (BufferedWriter bw = writer();) {
                bw.write("AssetId|AssetName|AssetType|Location|Status|DepartmentId|OpeningTime|ClosingTime|Capacity");
                bw.newLine();
                for (HospitalAsset a : map.values()) {
                    String assetData = a.getAssetId() + "," + a.getAssetName() + "," + a.getAssetType() + "," + a.getLocation() + "," + a.getStatus() + "," + a.getDepartmentId() + "," + a.getOpeningTime() + "," + a.getClosingTime() + "," + a.getCapacity() + "\n";
                    bw.write(assetData);
                }
                return true;
            } catch (IOException e) {
                System.out.println(e);
                return false;
            }
        }
        
        return false;
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
