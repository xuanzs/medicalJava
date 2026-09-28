/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wongkingsen
 */
public class Admin {
    String adminId, userId;
    
    public Admin(String adminId, String userId) {
        this.adminId = adminId;
        this.userId = userId;
    }
    
    public String getAdminId() {
        return adminId;
    }
    
    public String getUserId() {
        return userId;
    }
}
