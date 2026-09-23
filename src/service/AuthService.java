/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.User;
import repo.FileUserRepo;

public class AuthService {
    private final FileUserRepo userRepo;
    
    public AuthService(FileUserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        
        if (user != null) {
            if (user.getPassword().equals(password)) {return user;}
            else {return null;}
        }
        return null;
    }
}
