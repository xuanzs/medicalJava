/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wongkingsen
 */
public class HospitalAsset {
    private String assetId, assetName, assetType, location, status, departmentId, openingTime, closingTime;
    private int capacity;
    
    public HospitalAsset(String assetId, String assetName, String assetType, String location, String status, String departmentId, String openingTime, String closingTime, int capacity) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.assetType = assetType;
        this.location = location;
        this.status = status;
        this.departmentId = departmentId;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.capacity = capacity;
    }
    
    public String getAssetId() {
        return assetId;
    }
    
    public String getAssetName() {
        return assetName;
    }
    
    public String getAssetType() {
        return assetType;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getDepartmentId() {
        return departmentId;
    }
    
    public String getOpeningTime() {
        return openingTime;
    }
    
    public String getClosingTime() {
        return closingTime;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }
    
    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
