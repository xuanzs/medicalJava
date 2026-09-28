/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import repo.FileAssetRepo;

/**
 *
 * @author wongkingsen
 */
public class AssetService {
    private FileAssetRepo assetRepo;
    
    public AssetService() {
        assetRepo = new FileAssetRepo();
    }
    
    public boolean validation(String assetId, String status, String departmentId, String openingTime, String closingTime, int capacity) {
        boolean result = true;
        
        if (assetId == null) {
            result = false;
            throw new IllegalArgumentException("Please select an asset.");
        } else if (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Closed") && !status.equalsIgnoreCase("Maintenance") && !status.equalsIgnoreCase("Renovation")) {
            result = false;
            throw new IllegalArgumentException("Invalid operational status.");
        } else if (departmentId == null) {
            result = false;
            throw new IllegalArgumentException("Please select a department.");
        } else if (capacity <= 0) {
            result = false;
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        
        try {
            LocalTime open = LocalTime.parse(openingTime);
            LocalTime close = LocalTime.parse(closingTime);
            
            if (!close.isAfter(open)) {
                result = false;
                throw new IllegalArgumentException("Closing time must be later than opening time.");
            }
        } catch (DateTimeParseException e) {
            result = false;
            throw new IllegalArgumentException("Time must use HH:mm format.");
        }
        
        return result;
    }
}
