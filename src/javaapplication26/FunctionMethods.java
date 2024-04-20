/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package deliveryvendor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public interface FunctionMethods {
    public String[] readLines(String filePath) throws IOException;
    
    public void writeLinesToFile(String filePath, String[] lines) throws IOException ;
    
    public void modifyAttribute(String[] lines,int length, int indexChange, String Id, String newStatus);
    
    public void loadTableData(String filePath,int[]displayindex, DefaultTableModel model)  throws IOException;
    
}
