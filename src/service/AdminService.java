/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Collection;
import model.User;
import repo.FileAdminRepo;
import repo.FileDoctorRepo;
import repo.FileMedicalManagerRepo;
import repo.FilePatientRepo;
import repo.FileUserRepo;

/**
 *
 * @author wongkingsen
 */
public class AdminService {
    private FileUserRepo userRepo;
    private FileDoctorRepo doctorRepo;
    private FileAdminRepo adminRepo;
    private FileMedicalManagerRepo managerRepo;
    private FilePatientRepo patientRepo;
    private ValidationService validationService;
    
    public AdminService() {
        userRepo = new FileUserRepo();
        doctorRepo = new FileDoctorRepo();
        adminRepo = new FileAdminRepo();
        managerRepo = new FileMedicalManagerRepo();
        patientRepo = new FilePatientRepo();
        validationService = new ValidationService(userRepo);
    }

    public boolean createUser(String name, String email, String password, String phone, String gender, String role) {
        validationService.createValidation(name, email, password, phone);
        boolean userCreated = userRepo.createUser(name, email, password, phone, gender, role);
        if (!userCreated) return false;

        User newUser = userRepo.findByEmail(email);
        if (newUser == null) return false;

        boolean profileCreated;
        if (role.equalsIgnoreCase("Doctor")) {
            profileCreated = doctorRepo.createDoctor(newUser.getUserId());
        } else if (role.equalsIgnoreCase("MedicalManager")) {
            profileCreated = managerRepo.createMedicalManager(newUser.getUserId());
        } else if (role.equalsIgnoreCase("Patient")) {
            profileCreated = patientRepo.createPatient(newUser.getUserId());
        } else if (role.equalsIgnoreCase("Admin")) {
            profileCreated = adminRepo.createAdmin(newUser.getUserId());
        } else {
            userRepo.deleteUser(newUser.getUserId());
            throw new IllegalArgumentException("Invalid role.");
        }

        if (!profileCreated) {
            userRepo.deleteUser(newUser.getUserId());
            return false;
        }
        return true;
    }
    
    public boolean updateUser(String userId, String name, String email, String phone, String gender) {
        User existingUser = userRepo.findByUserId(userId);

        if (existingUser == null) {
            throw new IllegalArgumentException("User not found.");
        }

        validationService.updateValidation(userId, name, email, existingUser.getPassword(), phone);

        return userRepo.updateUser(userId, name, email, phone, gender);
    }

    public boolean deleteUser(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("Please select a user.");
        }
        
        User user = userRepo.findByUserId(userId);

        if (user == null) {
            return false;
        }

        String role = user.getRole();

        if (role.equalsIgnoreCase("Doctor")) {
            doctorRepo.deleteByUserId(userId);
        } else if (role.equalsIgnoreCase("MedicalManager")) {
            managerRepo.deleteByUserId(userId);
        } else if (role.equalsIgnoreCase("Patient")) {
            patientRepo.deleteByUserId(userId);
        } else if (role.equalsIgnoreCase("Admin")) {
            adminRepo.deleteByUserId(userId);
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
