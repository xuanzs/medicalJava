/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Collection;
import model.User;
import repo.FileDoctorRepo;
import repo.FileUserRepo;

/**
 *
 * @author wongkingsen
 */
public class AdminService {
    private FileUserRepo userRepo;
    private FileDoctorRepo doctorRepo;
    private ValidationService validationService;
    
    public AdminService() {
        userRepo = new FileUserRepo();
        doctorRepo = new FileDoctorRepo();
        validationService = new ValidationService();
    }
    
    public boolean createUser(String name, String email, String password, String phone, String gender, String role) {
        validationService.createValidation(name, email, password, phone);
        
        return userRepo.createUser(name, email, password, phone, gender, role);
    }
    
//    public boolean updateUser(String userId, String name, String email, String password, String phone,)
    
    public boolean deleteUser(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("Please select a user.");
        }
        
        return userRepo.deleteUser(userId);
    }
    
    public Collection<User> getAllUsers() {
        return userRepo.getAllUsers();
    }
    
    public User findByUserId(String userId) {
        return userRepo.findByUserId(userId);
    }
}
