/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

import java.io.*;

/**
 *
 * @author wongkingsen
 */
public class AdminStaff {
    private String adminId;
    
    public AdminStaff(String userId, String password, String name, String phone, String gender, String role, String adminId) {
//        super(userId, password, name, phone, gender, "AdminStaff");
        this.adminId = adminId;
    }
    
    // Create user
    public void createUser() throws IOException {
        FileWriter fw = new FileWriter("users.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write
    }
    
    // Read user
    public void readUser() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        
        FileReader fr = new FileReader("users.txt");
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            if (data.length == 7) {
                User user = new User(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
            }

            users.add(user);
        }
    }
    
    //
}
