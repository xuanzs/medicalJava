
import java.io.*;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

class User {
    protected String userId, password, name, phone, gender;
    
    public User(String userId, String password, String name, String phone, String gender) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }
    
    public String getUserId() {return userId;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public String getPhone() {return phone;}
    public String getGender() {return gender;}
    
}

public class Login {
    protected String email, password;
    
    public Login() {
        this.email = "";
        this.password = "";
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean LoginValidation(String email, String password) {
        
        HashMap<String, User> db = new HashMap<>();
        
        try {
            FileReader fr = new FileReader("User.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine();
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                String e = parts[0];
                String u = parts[1];
                String p = parts[2];
                String n = parts[3];
                String ph = parts[4];
                String g = parts[5];
                
                db.put(e, new User(u,p,n,ph,g));
                
            }
            br.close();
            fr.close();
            
        } catch(IOException e) {
            System.out.println(e);
        }
        
        if (db.containsKey(email)) {
            User user = db.get(email);
            
            if (user.getPassword().equals(password)) {return true;}
        }
        
        return false;
        
    }
    
}
