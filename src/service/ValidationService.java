/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import javax.swing.JOptionPane;
import repo.FileUserRepo;
import model.User;

public class ValidationService {
    private FileUserRepo userRepo;
    
    public ValidationService() {
        userRepo = new FileUserRepo();
    }
    
    public void createValidation(String name, String email, String password, String phone) {        
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            throw new IllegalArgumentException("Please fill in all fields.");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Please enter a valid email address.");
        } else if (!phone.matches("^01\\d{8,9}$")) {
            throw new IllegalArgumentException("Please enter a valid phone number.");
        } else if (userRepo.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists.");
        }
    }
    
    public void updateValidation(String userId, String name, String email, String password, String phone) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            throw new IllegalArgumentException("Please fill in all fields.");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Please enter a valid email address.");
        } else if (!phone.matches("^01\\d{8,9}$")) {
            throw new IllegalArgumentException("Please enter a valid phone number.");
        }
        
        User emailOwner = userRepo.findByEmail(email);
        
        if (emailOwner != null && !emailOwner.getUserId().equalsIgnoreCase(userId)) {
            throw new IllegalArgumentException("Email already exists.");
        }
    }
    
    public boolean assignmentValidation(String doctorId, String managerId) {
        if (doctorId.isEmpty() || managerId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a doctor and a manager.");
            return false;
        }
        
        return true;
    }
}
