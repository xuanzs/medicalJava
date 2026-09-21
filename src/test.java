/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

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

public class test {   
    
    public static void main(String[] args) throws IOException{
        
        String email,password;
        HashMap<String, User> db = new HashMap<>();
        
        try {
            FileReader fr = new FileReader("User.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = null;
            br.readLine();
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(parts);
                
                String e = parts[0];
                String u = parts[1];
                String p = parts[2];
                String n = parts[3];
                String ph = parts[4];
                String g = parts[5];
                
                db.put(e, new User(u,p,n,ph,g));

            }
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter email");
            email = sc.next();
            System.out.println("Please enter password");
            password = sc.next();
            
            if (db.containsKey(email)) {
                User user = db.get(email);
                
                if (user.getPassword().equals(password)) {
                    System.out.println("WelcomeBack");
                } else {
                    System.out.println("Password Invalid");
                }
            } else {
                System.out.println("Email Invalid");
            }
            
            br.close();
            fr.close();
            System.out.println(db.get(email));
            
            
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
