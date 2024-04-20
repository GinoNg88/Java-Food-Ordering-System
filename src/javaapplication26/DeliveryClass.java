/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class DeliveryClass implements FunctionMethods{
    protected int[] displayColumnIndices;
    //protected String ID;
    public DeliveryClass(){
        
    }
    public String[] readLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            //this.reader = reader;
            return reader.lines().toArray(String[]::new);
        }
    }
    
    public void writeLinesToFile(String filePath, String[] lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
    
    public void modifyAttribute(String[] lines,int length, int indexChange, String orderId, String newStatus) {
        for (int i = 1; i < lines.length; i++) { // Start from 1 to skip header
            String[] parts = lines[i].split("/");
            if (parts.length >= length && parts[0].equals(orderId)) {
                parts[indexChange] = newStatus; 
                lines[i] = String.join("/", parts);
                break; // Stop searching after finding the order
            }
        }
    }
    
    public void loadTableData(String filePath,int[]displayindex, DefaultTableModel model) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the header line and set column names
            String headerLine = reader.readLine();
            String[] allColumns = headerLine.split(",");
            // Find the indices of columns to display
            displayColumnIndices = displayindex;
            //model.setColumnIdentifiers(allColumns);
            for (int columnIndex : displayColumnIndices) {
                model.addColumn(allColumns[columnIndex]);
            }
            // Read data lines and add them to the table model
            
        }
    }
    
    
    
    
}
