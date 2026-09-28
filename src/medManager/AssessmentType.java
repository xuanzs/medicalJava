package medManager;
import java.io.*;
import java.util.*;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ryne
 */
public class AssessmentType {
    protected String assessmentTypeId, assessmentTypeName, description;
    
    public String getAssessmentTypeId(){return assessmentTypeId;}
    
    public String getAssessmentTypeName(){return assessmentTypeName;}
    
    public String getDescription(){return description;}
    
    public void setAssessmentTypeId(String id){this.assessmentTypeId = id;}
    
    public void setAssessmentTypeName(String name){this.assessmentTypeName = name;}
    
    public void setDescription(String desc){this.description = desc;}
    
    public AssessmentType(String id, String name, String desc){
        this.assessmentTypeId = id;
        this.assessmentTypeName = name;
        this.description = desc;
    }
    
    public void createType(){
        try{
            FileWriter fw = new FileWriter("data/assessmentType.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(String.format("%s, %s, %s", assessmentTypeId, assessmentTypeName, description));
            bw.newLine();
            
            bw.close();
            fw.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateType(String id, String newName, String newDesc){
        try{
            FileReader fr = new FileReader("data/assessmentType.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = null;
            List<AssessmentType> lines = new ArrayList<>();
            
            while((line = br.readLine()) != null){
                String[] items = line.split(", ");
                AssessmentType assessmentType = new AssessmentType(items[0], items[1], items[2]);
                if (assessmentType.assessmentTypeId.equals(id)){
                    assessmentType.assessmentTypeName = newName;
                    assessmentType.description = newDesc;
                }
                lines.add(assessmentType);
            }
            
            br.close();
            fr.close();
            
            // Write information back to assessmentType.txt file
            FileWriter fw = new FileWriter("data/assessmentType.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(AssessmentType item : lines){
                bw.write(String.format("%s, %s, %s", item.assessmentTypeId, item.assessmentTypeName, item.description));
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
}
