/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import medManager.MedicalManager;
import model.MedicalManagerInfo;
import model.User;
import repo.FileMedicalManagerRepo;
import repo.FileUserRepo;

public class MedicalManagerService {
    private FileMedicalManagerRepo managerRepo = new FileMedicalManagerRepo();
    private FileUserRepo userRepo = new FileUserRepo();
    
    private ArrayList<String[]> managerList = new ArrayList<>();
    private ArrayList<MedicalManagerInfo> managerInfo = new ArrayList<>();
    
    public MedicalManagerService() {
        managerList = managerRepo.returnAllMedicalManager();
        
        for (String[] ml : managerList) {
            String mm = ml[0].trim();
            String u = ml[1].trim();
            
            User user = userRepo.findByUserId(u);
            
            if (user != null) {
                MedicalManagerInfo info = new MedicalManagerInfo(mm, user.getUserId());
                
                managerInfo.add(info);
            }
        }
    }
    
    public ArrayList<MedicalManagerInfo> returnAllMMInfo() {
        return managerInfo;
    }
    
    public MedicalManagerInfo findByUserid(String userId) {
        for (MedicalManagerInfo mm : managerInfo) {
            if (mm.getUserId().equals(userId)) {
                return mm;
            }
        }
        return null;
    }
}
