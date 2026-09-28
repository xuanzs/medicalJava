package medManager;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ryne
 */
public class Department {
    protected String departmentId, departmentName, description;
    
    public String getDepartmentId(){return departmentId;}
    
    public String getDepartmentName(){return departmentName;}
    
    public String getDescription(){return description;}
    
    public void setDepartmentId(String id){this.departmentId = id;}
    
    public void setDepartmentName(String name){this.departmentName = name;}
    
    public void setDescription(String desc){this.description = desc;}
    
    public Department(String id, String name, String desc){
        this.departmentId = id;
        this.departmentName = name;
        this.description = desc;
    }
}
