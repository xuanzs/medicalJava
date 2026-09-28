/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.*;

public abstract class FileRepo {
    protected String filePath;
    
    public FileRepo(String filePath) {
        this.filePath = filePath;
    }
    
    protected void loadFile() {
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            
            br.readLine();
            String line;
            while((line = br.readLine()) != null) {
                processLine(line);
            }
            
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    protected abstract void processLine(String line);
}
