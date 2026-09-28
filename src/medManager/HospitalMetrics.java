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

public class HospitalMetrics {
    protected String metricsId;
    protected LocalDate dateGenerated;
    protected int totalPatients;
    protected double averageWaitTime;
    
    public String getMetricsId(){return metricsId;}
    
    public LocalDate getDateGenerated(){return dateGenerated;}
    
    public int getTotalPatients(){return totalPatients;}
    
    public double getAverageWaitTime(){return averageWaitTime;}
    
    public void setMetricsId(String id){this.metricsId = id;}
    
    public void setDateGenerated(LocalDate date){this.dateGenerated = date;}
    
    public void setTotalPatients(int total){this.totalPatients = total;}
    
    public void setAverageWaitTime(double time){this.averageWaitTime = time;}
    
    public HospitalMetrics(String id, LocalDate date, int total, double time){
        this.metricsId = id;
        this.dateGenerated = date;
        this.totalPatients = total;
        this.averageWaitTime = time;
    }
    
    public String[] displayMetrics(){
        String line = String.format("%s, %s, %d, %.2f", metricsId, dateGenerated, totalPatients, averageWaitTime);
        return line.split(", ");
    }
}
