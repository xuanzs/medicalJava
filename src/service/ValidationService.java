/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import javax.swing.JOptionPane;
import repo.FileUserRepo;

public class ValidationService {
    public boolean createValidation(String name, String email, String password, String phone) {
        FileUserRepo userRepo = new FileUserRepo();
        
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return false;
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
            return false;
        } else if (!phone.matches("^01\\d{8,9}$")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid phone number.");
            return false;
        } else if (userRepo.emailExists(email)) {
            JOptionPane.showMessageDialog(null, "Email already exists.");
            return false;
        }
        
        return true;
    }
}
