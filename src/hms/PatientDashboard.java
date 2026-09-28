package hms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PatientDashboard extends JFrame {

    private Patient patient;

    public PatientDashboard(Patient patient) {
        this.patient = patient;

        this.setTitle("Patient Dashboard - " + patient.getName());
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnBook = new JButton("Book Appointment");
        JButton btnView = new JButton("View My Appointments");
        JButton btnHistory = new JButton("View Medical History");
        JButton btnFeedback = new JButton("Submit Feedback");
        JButton btnProfile = new JButton("Edit Profile");

        panel.add(btnBook);
        panel.add(btnView);
        panel.add(btnHistory);
        panel.add(btnFeedback);
        panel.add(btnProfile);

        btnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String doctorId = JOptionPane.showInputDialog("Enter Doctor ID:");
                if (doctorId == null) return;
                String date = JOptionPane.showInputDialog("Enter Date (YYYY-MM-DD):");
                if (date == null) return;
                String time = JOptionPane.showInputDialog("Enter Time (e.g. 10:00):");
                if (time == null) return;
//                String apptId = "APT-" + System.currentTimeMillis();
                String apptId = AppointmentFileHandler.generateAppointmentId();
                Appointment appt = new Appointment(apptId, patient.getPatientId(),
                                                   doctorId, date, time, "Booked");
                AppointmentFileHandler.saveAppointment(appt);
                JOptionPane.showMessageDialog(null, "Appointment booked!");
            }
        });

                btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Appointment> allAppts = AppointmentFileHandler.loadAllAppointments();
                ArrayList<Appointment> myAppts = new ArrayList<>();
                System.out.println("allAppts: " + allAppts);
                System.out.println("myAppts: " + myAppts);
                for (Appointment a : allAppts) {
                    if (a.getPatientId().equals(patient.getPatientId())) {
                        myAppts.add(a);
                    }
                }
                if (myAppts.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No appointments found.");
                    return;
                }
                String[] options = new String[myAppts.size()];
                for (int i = 0; i < myAppts.size(); i++) {
                    options[i] = myAppts.get(i).getDate() + " " + myAppts.get(i).getTime()
                                 + " - Dr:" + myAppts.get(i).getDoctorId()
                                 + " [" + myAppts.get(i).getStatus() + "]";
                }
                String choice = (String) JOptionPane.showInputDialog(null,
                    "Your Appointments:", "Appointments",
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (choice == null) return;
                int index = java.util.Arrays.asList(options).indexOf(choice);
                Appointment selected = myAppts.get(index);
                String[] actions = {"Cancel", "Reschedule", "Close"};
                int action = JOptionPane.showOptionDialog(null,
                    "What do you want to do?", "Action",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, actions, actions[2]);
                if (action == 0) {
                    selected.setStatus("Cancelled");
                    AppointmentFileHandler.saveAllAppointments(allAppts);
                    JOptionPane.showMessageDialog(null, "Appointment cancelled.");
                } else if (action == 1) {
                    String newDate = JOptionPane.showInputDialog("New date (YYYY-MM-DD):");
                    if (newDate == null) return;
                    String newTime = JOptionPane.showInputDialog("New time (e.g. 10:00):");
                    if (newTime == null) return;
                    selected.setDate(newDate);
                    selected.setTime(newTime);
//                    selected.setStatus("Rescheduled");
                    selected.setStatus("Booked");
                    AppointmentFileHandler.saveAllAppointments(allAppts);
                    JOptionPane.showMessageDialog(null, "Appointment rescheduled.");
                }
            }
        });

        btnFeedback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String doctorId = JOptionPane.showInputDialog("Enter Doctor ID:");
                if (doctorId == null) return;
                String rating = JOptionPane.showInputDialog("Enter Rating (1-5):");
                if (rating == null) return;
                String comment = JOptionPane.showInputDialog("Enter Comment:");
                if (comment == null) return;
                String fbId = "FB-" + System.currentTimeMillis();
                Feedback fb = new Feedback(fbId, patient.getPatientId(),
                                           doctorId, Integer.parseInt(rating),
                                           comment, "23/09/2026");
                FeedbackFileHandler.saveFeedback(fb);
                JOptionPane.showMessageDialog(null, "Feedback submitted!");
            }
        });

        btnProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog("Enter new name:", patient.getName());
                String newAddress = JOptionPane.showInputDialog("Enter new address:", patient.getAddress());
                if (newName != null) patient.setName(newName);
                if (newAddress != null) patient.setAddress(newAddress);
                JOptionPane.showMessageDialog(null, "Profile updated!");
            }
        

          });   // <-- this closes btnProfile

                btnHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                    "No medical records found.\nRecords are added by your doctor after consultation.");
            }
        });

        this.add(panel);
        this.setVisible(true);
    }

}