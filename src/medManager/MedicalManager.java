package medManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ryne
 */

import java.io.*;
import javax.swing.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class MedicalManager {
//    protected String managerId;
    protected String managerId, userId;
    
    public String getManagerId(){return managerId;}
    public String getUserId() {return userId;}
    
    public void setManagerId(String managerId){this.managerId = managerId;}
    
    public MedicalManager(String managerId, String userId){
        this.managerId = managerId;
        this.userId = userId;
    }
    
    public void createDepartment(String departmentId, String departmentName, String description){
        try{
            FileWriter fw = new FileWriter("data/departments.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(String.format("%s, %s, %s", departmentId, departmentName, description));
            bw.newLine();
            
            bw.close();
            fw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateDepartment(String id, String newName, String newDesc){
        try{
            FileReader fr = new FileReader("data/departments.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = null;
            List<String> lines = new ArrayList<>();
            
            while((line = br.readLine()) != null){
                String[] items = line.split(", ");
                if (items[0].equals(id)){
                    line = String.format("%s, %s, %s", id, newName, newDesc);
                }
                lines.add(line);
            }
            
            br.close();
            fr.close();
            
            // Write information back to departments.txt file
            FileWriter fw = new FileWriter("data/departments.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(String item : lines){
                bw.write(item);
                bw.newLine();
            }
            
            bw.close();
            fw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void designShift(String shiftId, LocalDate date, LocalTime startTime, LocalTime endTime, String doctorId){
        try{
            FileWriter fw = new FileWriter("data/shiftRoster.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(String.format("%s, %s, %s, %s, %s", shiftId, date, startTime, endTime, doctorId));
            // %s converts LocalDate to String (yyyy-MM-dd)
            // %s converts LocalTime to String (HH:mm)
            bw.newLine();
            
            bw.close();
            fw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void modifyShift(String shiftId, LocalDate newDate, LocalTime newStartTime, LocalTime newEndTime, String newDoctorId){
        try{
            FileReader fr = new FileReader("data/shiftRoster.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = null;
            List<ShiftRoster> lines = new ArrayList<>();
            
            while((line = br.readLine()) != null){
                String[] items = line.split(", ");                
                ShiftRoster shift = new ShiftRoster(items[0], LocalDate.parse(items[1]), LocalTime.parse(items[2]), LocalTime.parse(items[3]), items[4]);
                if(shift.getShiftId().equals(shiftId)){
                    shift.updateShift(newDate, newStartTime, newEndTime, newDoctorId);
                }
                lines.add(shift);
            }
            
            br.close();
            fr.close();
            
            // Write information back to shiftRoster.txt file
            FileWriter fw = new FileWriter("data/shiftRoster.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(ShiftRoster item : lines){
                bw.write(String.format("%s, %s, %s, %s, %s", item.getShiftId(), item.getShiftDate(), item.getStartTime(), item.getEndTime(), item.getDoctorId()));
                bw.newLine();
            }
            
            bw.close();
            fw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Change to List<RevenueSummaries> and List<HospitalMetrics>
    public List<HospitalMetrics> viewHospitalMetrics(){
        try{
            FileReader fr = new FileReader("data/HospitalMetrics.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = null;
            List<HospitalMetrics> lines = new ArrayList<>();
            
            while((line = br.readLine()) != null){
                String[] items = line.split(", ");
                HospitalMetrics metrics = new HospitalMetrics(items[0], LocalDate.parse(items[1]), Integer.parseInt(items[2]), Double.parseDouble(items[3]));
                lines.add(metrics);
            }
            
            br.close();
            fr.close();
            
            return lines;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
    
    public List<RevenueSummary> viewRevenueSummaries(){
        try{
            FileReader fr = new FileReader("data/revenueSummaries.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = null;
            List<RevenueSummary> lines = new ArrayList<>();
            
            while((line = br.readLine()) != null){
                String[] items = line.split(", ");
                RevenueSummary summary = new RevenueSummary(items[0], LocalDate.parse(items[1]), Double.parseDouble(items[2]), Double.parseDouble(items[3]));
                lines.add(summary);
            }
            
            br.close();
            fr.close();
            
            return lines;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}
