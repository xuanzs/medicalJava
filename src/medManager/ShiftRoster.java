package medManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ryne
 */

import java.util.*;
import java.time.*;

public class ShiftRoster {
    protected String shiftId, doctorId;
    protected LocalDate shiftDate;
    protected LocalTime startTime, endTime;
    
    public String getShiftId(){return shiftId;}
    
    public String getDoctorId(){return doctorId;}
    
    public LocalDate getShiftDate(){return shiftDate;}
    
    public LocalTime getStartTime(){return startTime;}
    
    public LocalTime getEndTime(){return endTime;}
    
    public void setShiftId(String id){this.shiftId = id;}
    
    public void setDoctorId(String id){this.doctorId = id;}
    
    public void setShiftDate(LocalDate date){this.shiftDate = date;}
    
    public void setStartTime(LocalTime time){this.startTime = time;}
    
    public void setEndTime(LocalTime time){this.endTime = time;}
    
    public ShiftRoster(String shiftId, LocalDate date, LocalTime startTime, LocalTime endTime, String doctorId){
        this.shiftId = shiftId;
        this.doctorId = doctorId;
        this.shiftDate = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public void updateShift(LocalDate newDate, LocalTime newStartTime, LocalTime newEndTime, String newDoctorId){
        this.doctorId = newDoctorId;
        this.shiftDate = newDate;
        this.startTime = newStartTime;
        this.endTime = newEndTime;
    }
}
