/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wongkingsen
 */
public class InpatientWard extends HospitalAsset {
    public InpatientWard(String assetId, String assetName, String assetType, String location, String status, String departmentId, String openingTime, String closingTime, int capacity) {
        super(assetId, assetName, assetType, location, status, departmentId, openingTime, closingTime, capacity);
    }
}
