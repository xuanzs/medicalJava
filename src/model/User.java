/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author xuanchen
 */
public abstract class User {
    private String userId, name, email, password, phone, gender, role;
    
    public User(String userId, String name, String email, String password, String phone, String gender, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getRole() {
        return role;
    }
}
