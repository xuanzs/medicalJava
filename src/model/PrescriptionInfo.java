/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author xuanchen
 */
public class PrescriptionInfo {
    private String prescriptId, consultId, patientId, doctorId, patientName, issueDate, medName, dosage, freq, duration, instructions;

    public PrescriptionInfo(String prescriptId, String consultId, String patientId, String doctorId, String patientName, String issueDate, String medName, String dosage, String freq, String duration, String instructions) {
        this.prescriptId = prescriptId;
        this.consultId = consultId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.issueDate = issueDate;
        this.medName = medName;
        this.dosage = dosage;
        this.freq = freq;
        this.duration = duration;
        this.instructions = instructions;
    }

    public String getPrescriptId() {
        return prescriptId;
    }

    public String getConsultId() {
        return consultId;
    }

    public String getPatientId() {
        return patientId;
    }
    
    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getMedName() {
        return medName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getFreq() {
        return freq;
    }

    public String getDuration() {
        return duration;
    }

    public String getInstructions() {
        return instructions;
    }
}
