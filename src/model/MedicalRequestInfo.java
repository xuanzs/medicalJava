/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author xuanchen
 */
public class MedicalRequestInfo {
    private String requestId, consultId, patientId, doctorId, patientName, requestDate, requestType, status, reason;

    public MedicalRequestInfo(String requestId, String consultId, String patientId, String doctorId, String patientName, String requestDate, String requestType, String status, String reason) {
        this.requestId = requestId;
        this.consultId = consultId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.requestDate = requestDate;
        this.requestType = requestType;
        this.status = status;
        this.reason = reason;
    }

    public String getRequestId() {
        return requestId;
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

    public String getRequestDate() {
        return requestDate;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
}
