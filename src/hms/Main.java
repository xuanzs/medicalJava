package hms;

public class Main {

    public static void main(String[] args) {

        Patient p = new Patient("U001", "Ali", "ali@email.com", 
                                "pass123", "012345678", "Male",
                                "PT-001", "123 Main Street");

        new PatientDashboard(p);

    }

}