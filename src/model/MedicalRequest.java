/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author xuanchen
 */
public class MedicalRequest {
    private String requestId, consultationId, patientId, doctorId, requestType, requestDate, reason, status;

    public MedicalRequest(String requestId, String consultationId, String patientId, String doctorId, String requestType, String requestDate, String reason, String status) {
        this.requestId = requestId;
        this.consultationId = consultationId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.requestType = requestType;
        this.requestDate = requestDate;
        this.reason = reason;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
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

    public String getRequestType() {
        return requestType;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }
}
