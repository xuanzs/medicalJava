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
        validationService = new ValidationService(userRepo);
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
    
    public boolean assignDoctor(String doctorUserId, String managerUserId) {
        if (doctorUserId == null || managerUserId == null || doctorUserId.isEmpty() || managerUserId.isEmpty()) {
            throw new IllegalArgumentException("Please select a doctor and a medical manager.");
        }
        
        User doctorUser = userRepo.findByUserId(doctorUserId);
        User managerUser = userRepo.findByUserId(managerUserId);
        
        if (doctorUser == null) {
            throw new IllegalArgumentException("Doctor not found.");
        }
        
        if (!doctorUser.getRole().equalsIgnoreCase("Doctor")) {
            throw new IllegalArgumentException("Selected user is not a doctor.");
        }
        
        if (managerUser == null) {
            throw new IllegalArgumentException("Medical manager not found.");
        }
        
        if (!managerUser.getRole().equalsIgnoreCase("MedicalManager")) {
            throw new IllegalArgumentException("Selected user is not a medical manager.");
        }
        
        if (doctorRepo.findByUserId(doctorUserId) == null) {
            throw new IllegalArgumentException("Doctor profile not found.");
        }

        return doctorRepo.assignMedicalManager(doctorUserId,managerUserId);
    }
    
    public Collection<User> getAllUsers() {
        return userRepo.getAllUsers();
    }
    
    public User findByUserId(String userId) {
        return userRepo.findByUserId(userId);
    }
}
