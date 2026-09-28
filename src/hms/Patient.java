package hms;

public class Patient extends User {

    private String patientId;
    private String address;

    public Patient(String userId, String name, String email, 
                   String password, String phone, String gender,
                   String patientId, String address) {
        super(userId, name, email, password, phone, gender);
        this.patientId = patientId;
        this.address = address;
    }

    public String getPatientId() { return patientId; }
    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

        @Override
    public String toString() {
        return getUserId() + "," + getName() + "," + getEmail() + ","
               + getPassword() + "," + getPhone() + "," + getGender() + ","
               + patientId + "," + address;
    }
    

}