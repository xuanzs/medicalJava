package hms;

import java.io.*;
import java.util.ArrayList;

public class PatientFileHandler {

    private static final String FILE_NAME = "patients.txt";

    // SAVE one patient to file
    public static void savePatient(Patient patient) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(patient.toString());
            pw.close();
        } catch (IOException e) {
            System.out.println("Error saving patient: " + e.getMessage());
        }
    }

    // LOAD all patients from file
    public static ArrayList<Patient> loadAllPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Patient p = new Patient(parts[0], parts[1], parts[2],
                                        parts[3], parts[4], parts[5],
                                        parts[6], parts[7]);
                patients.add(p);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No patient file found. Starting fresh.");
        }
        return patients;
    }

}