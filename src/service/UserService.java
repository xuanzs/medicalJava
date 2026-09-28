/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.User;
import repo.FileUserRepo;

public class UserService {
    private FileUserRepo userRepo = new FileUserRepo();

    public String updateUser(String userId, String name, String email, String phone, String gender) {
        if (name == null || name.trim().isEmpty()) {
            return "Name is required.";
        }

        if (email == null || email.trim().isEmpty()) {
            return "Email is required.";
        }

        if (phone == null || phone.trim().isEmpty()) {
            return "Phone is required.";
        }

        User existingEmail = userRepo.findByEmail(email);

        if (existingEmail != null && !existingEmail.getUserId().equals(userId)) {
            return "Email is already used by another user.";
        }

        boolean updated = userRepo.updateUser(userId, name, email, phone, gender);

        if (!updated) {return "Failed to update profile.";}

        return null;
    }

    public User findByUserId(String userId) {
        return userRepo.findByUserId(userId);
    }
    
    public String changePassword(String userId, String prevPass, String newPass) {
        User user = userRepo.findByUserId(userId);
        
        if (user == null) {
            return "User not found.";
        }
        
        if (prevPass == null || prevPass.isEmpty()) {
            return "Previous password is required.";
        }
        
        if (newPass == null || newPass.isEmpty()) {
            return "New password is required.";
        }
        
        if (!user.getPassword().equals(prevPass)) {
            return "Previous password is required.";
        }
        
        if (user.getPassword().equals(newPass)) {
            return "New password cannot be the same as previous password.";
        }
        
        boolean updated = userRepo.updatePassword(userId, newPass);
        
        if(!updated) {
            return "Failed to change password.";
        }
        
        return null;
    }
}
