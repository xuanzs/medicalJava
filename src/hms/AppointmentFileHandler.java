package hms;

import java.io.*;
import java.util.ArrayList;

public class AppointmentFileHandler {

    private static final String FILE_NAME = "data/Appointments.txt";

    public static void saveAppointment(Appointment appt) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, true));
            pw.println(appt.toString());
            pw.close();
        } catch (IOException e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
    }

    public static ArrayList<Appointment> loadAllAppointments() {
        ArrayList<Appointment> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                list.add(new Appointment(p[0], p[1], p[2], p[3], p[4], p[5]));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No appointments file found.");
        }
        return list;
    }

    public static void saveAllAppointments(ArrayList<Appointment> list) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, false));
            pw.println("Appointment ID|Patient ID|Doctor ID|Appointment Date|Appointment Time|Status");
            for (Appointment appt : list) {
                pw.println(appt.toString());
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error saving appointments: " + e.getMessage());
        }
    }
    
    // appointment id generator
    public static String generateAppointmentId() {
        ArrayList<Appointment> appointments = loadAllAppointments();
        
        int highest = 0;
        
        for (Appointment a : appointments) {
            String id = a.getAppointmentId();
            
            if (id.startsWith("A")) {
                try {
                    int number = Integer.parseInt(id.substring(1));
                    
                    if (number > highest) {
                        highest = number;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }
        return String.format("A%03d", highest + 1);
    }

}