package hms;

public abstract class User {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String gender;
    
    public User(String userId, String name, String email, String password, String phone, String gender) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
    }
    
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getGender() { return gender; }
    
    
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    
    
    @Override
    public abstract String toString();
    
    
    
    
    }