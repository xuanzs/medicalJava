/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import model.User;

public class FileUserRepo {

    private final HashMap<String, User> loginMap = new HashMap<>();
    private final HashMap<String, User> map = new HashMap<>();

    public FileUserRepo() {
        loadFile();
    }

    private void loadFile() {
        loginMap.clear();
        map.clear();

        try {
            FileReader fr = new FileReader("data/User.txt");
            BufferedReader br = new BufferedReader(fr);

            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);

                if (parts.length == 7) {

                    String id = parts[0].trim();
                    String n = parts[1].trim();
                    String e = parts[2].trim().toLowerCase();
                    String p = parts[3];
                    String ph = parts[4].trim();
                    String g = parts[5].trim();
                    String r = parts[6].trim();

                    User user = new User(id, n, e, p, ph, g, r);

                    loginMap.put(e, user);
                    map.put(id, user);
                } else {
                    System.out.println("Row invalid");
                }
            }
            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    
    public Collection<User> getAllUsers() {
        return map.values();
    }
    public User findByEmail(String email) {
        return loginMap.get(email.trim().toLowerCase());
    }

    public User findByUserId(String userId) {
        return map.get(userId.trim());
    }

    public boolean updateUser(String userId, String name, String email, String phone, String gender) {
        try {
            Path path = Paths.get("data/User.txt");
            List<String> lines = Files.readAllLines(path);

            boolean found = false;

            for (int i = 1; i < lines.size(); i++) {

                String[] parts = lines.get(i).split(",", -1);

                if (parts.length == 7 && parts[0].trim().equals(userId.trim())) {
                    String password = parts[3];
                    String role = parts[6].trim();

                    lines.set(i, userId.trim() + "," + name.trim() + "," + email.trim().toLowerCase() + "," + password + "," + phone.trim() + "," + gender.trim() + "," + role);

                    found = true;
                    break;
                }
            }

            if (!found) {return false;}

            Files.write(path, lines);

            loadFile();

            return true;

        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean updatePassword(String userId, String newPassword) {
        try {
            Path path = Paths.get("data/User.txt");
            List<String> lines = Files.readAllLines(path);
            
            boolean found = false;
            
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                
                if (parts.length == 7 && parts[0].trim().equals(userId.trim())) {
                    parts[3] = newPassword;
                    
                    lines.set(i, String.join(",", parts));
                    
                    found = true;
                    break;
                }
            }
            
            if (!found) {return false;}
            
            Files.write(path, lines);
            loadFile();
              return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
            
//    public User findByUserId(String userId) {
//        for (User user : map.values()) {
//            if (user.getUserId().equalsIgnoreCase(userId)) {
//                return user;
//            }
//        }
//        return null;
//    }
    
    // FileReader
    public BufferedReader reader() throws IOException {
        FileReader fr = new FileReader("data/User.txt");
        
        return new BufferedReader(fr);
    }
    
    // FileWriter
    public BufferedWriter writer() throws IOException {
        FileWriter fw = new FileWriter("data/User.txt");
        
        return new BufferedWriter(fw);
    }
    
    public BufferedWriter writer(boolean append) throws IOException {
        FileWriter fw = new FileWriter("data/User.txt", append);

        return new BufferedWriter(fw);
    }
    
    // Generate userId
    public String generateUserId() {
        int maxId = 0;
        
        for (User user : map.values()) {
            String id = user.getUserId();
            
            try {
                int number = Integer.parseInt(id.substring(3));
                
                if (number > maxId) {
                    maxId = number;
                }
            } catch (Exception e) {
                System.out.println("Invalid User ID: " + id);
            }
        }
        
        return String.format("Uid%03d", maxId + 1);
    }
    
    // Create user
    public boolean createUser(String name, String email, String password, String phone, String gender, String role) {       
        if (map.containsKey(email)) {
            return false;
        }
        
        String userId = generateUserId();
        User user = new User(userId, name, email, password, phone, gender, role);

        map.put(email, user);
        
        try {
            saveUsers();
            return true;
        } catch (IOException e) {
            map.remove(email);
            System.out.println(e);
            return false;
        }
    }
    
    // Delete user
    public boolean deleteUser(String userId) {
        User user = findByUserId(userId);
        
        if (user == null) {
            return false;
        }
        
        map.remove(user.getEmail().trim().toLowerCase());
        
        try {
            saveUsers();
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    private void saveUsers() throws IOException {
        try (BufferedWriter bw = writer()) {
            bw.write("UserId|Name|Email|Password|Phone|Gender|Role");
            bw.newLine();
            
            for (User user : map.values()) {
                bw.write(user.getUserId() + "," + user.getName() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getPhone() + "," + user.getGender() + "," + user.getRole());
                bw.newLine();
            }
        }
    }
}
