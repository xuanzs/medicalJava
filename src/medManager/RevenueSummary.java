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

public class RevenueSummary {
    protected String summaryId;
    protected LocalDate dateGenerated;
    protected double totalIncome, totalExpenses;
    
    public String getSummaryId(){return summaryId;}
    
    public LocalDate getDateGenerated(){return dateGenerated;}
    
    public double getTotalIncome(){return totalIncome;}
    
    public double getTotalExpenses(){return totalExpenses;}
    
    public void setSummaryId(String id){this.summaryId = id;}
    
    public void setDateGenerated(LocalDate date){this.dateGenerated = date;}
    
    public void setTotalIncome(double income){this.totalIncome = income;}
    
    public void setTotalExpenses(double expenses){this.totalExpenses = expenses;}
    
    public RevenueSummary(String id, LocalDate date, double income, double expenses){
        this.summaryId = id;
        this.dateGenerated = date;
        this.totalIncome = income;
        this.totalExpenses = expenses;
    }
    
    public String[] displaySummary(){
        String line = String.format("%s, %s, %.2f, %.2f", summaryId, dateGenerated, totalIncome, totalExpenses);
        return line.split(", ");
    }
}
