/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Prescription {
    private String prescriptionId, consultationId, patientId, doctorId, medicationName, dosage, frequency, duration, instructions, issueDate;

    public Prescription(String prescriptionId, String consultationId, String patientId, String doctorId, String medicationName, String dosage, String frequency, String duration, String instructions, String issueDate) {
        this.prescriptionId = prescriptionId;
        this.consultationId = consultationId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.instructions = instructions;
        this.issueDate = issueDate;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public String getConsultationId() {
        return consultationId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getDuration() {
        return duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getIssueDate() {
        return issueDate;
    }
}
