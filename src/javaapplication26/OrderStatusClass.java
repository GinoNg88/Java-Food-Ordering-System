/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliveryvendor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.nio.file.Files.lines;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class OrderStatusClass extends FunctionClass{
    public void loadTableData(String filePath,int[]displayindex, DefaultTableModel model,String ID, JLabel lblStatus) throws IOException {
        super.loadTableData(filePath, displayindex, model);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("/");
                Object[] rowData = new Object[displayindex.length];
                //Ensure that displayindex has elements
                if (displayindex.length > 0) {
                    for (int i = 0; i < displayindex.length; i++) {
                        // Verify the index access is within bounds
                        int columnIndex = displayindex[i];
                        if (columnIndex < data.length) {
                            rowData[i] = data[columnIndex];
                        } else {
                            // Handle the case when the index is out of bounds
                            rowData[i] = "N/A"; // or another suitable value
                        }
                    }
                }
                // Additional conditions specific to DeliveryTaskClass
                if((ID.equals(rowData[7]))&&(!("completed").equals(rowData[8]))){
                    model.addRow(rowData);                                        
                }else{                   
                    
                }
            }
        }
    }
    

    
    public boolean checkOrderCount(String filePath,int[]displayindex, DefaultTableModel model,String ID) throws IOException {
        super.loadTableData(filePath, displayindex, model);
        boolean bool = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("/");
                Object[] rowData = new Object[displayindex.length];
                //Ensure that displayindex has elements
                if (displayindex.length > 0) {
                    for (int i = 0; i < displayindex.length; i++) {
                        // Verify the index access is within bounds
                        int columnIndex = displayindex[i];
                        if (columnIndex < data.length) {
                            rowData[i] = data[columnIndex];
                        } else {
                            // Handle the case when the index is out of bounds
                            rowData[i] = "N/A"; // or another suitable value
                        }
                    }
                }
                // Additional conditions specific to DeliveryTaskClass
                if((ID.equals(rowData[7]))&&(!("completed").equals(rowData[8]))&&(!("canceled").equals(rowData[8]))){
                    model.addRow(rowData);       
                }
                if(model.getRowCount()>0){                    
                    bool= false;
                    break;
                } 
            }
            model.setColumnCount(0);
            model.setRowCount(0);
        }
        return bool;
    }
    
    public void Refund(String[] lines, String CustomerId,JPanel Main) {
        for (int i = 1; i < lines.length; i++) { // Start from 1 to skip header
            String[] parts = lines[i].split("/");
            if (parts.length >= 7 && parts[0].equals(CustomerId)) {
                Double newCredit = Double.parseDouble(parts[7])+5.00;
                parts[7] = String.format("%.2f",newCredit);
                    lines[i] = String.join("/", parts);
                    JOptionPane.showMessageDialog(Main, "delivery fee refunded.");
                    break; // Stop searching after finding the order
            }
        }
    }
    
    public void ChangeType (JPanel Main,String filePath,String filePath1,String OrderID,String CustomerID){
        int result = JOptionPane.showConfirmDialog(
                Main,
                "There is no delivery man at the moment, please change your order to pick up or dine in. Yes as pick up, and no as dine in.",
                "TypeChange",
                JOptionPane.YES_NO_OPTION);       
        
        if(result == JOptionPane.YES_OPTION){
            OrderStatusClass obj1 = new OrderStatusClass();
            try {                
                String [] lines = obj1.readLines(filePath);
                obj1.modifyAttribute(lines,9,9,OrderID,"pickup");
                obj1.writeLinesToFile(filePath, lines); 
                
                String [] lines2 = obj1.readLines(filePath1);
                obj1.Refund(lines2,CustomerID,Main);
                obj1.writeLinesToFile(filePath1, lines2);
            } catch (IOException ex) {
                Logger.getLogger(OrderStatusClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(Main,"Order type Updated.");
                
        }else if(result == JOptionPane.NO_OPTION){
            OrderStatusClass obj1 = new OrderStatusClass();
            try {                
                String [] lines = obj1.readLines(filePath);
                obj1.modifyAttribute(lines,9,9,OrderID,"dinein");
                obj1.writeLinesToFile(filePath, lines);
                
                String [] lines2 = obj1.readLines(filePath1);
                obj1.Refund(lines2,CustomerID,Main);
                obj1.writeLinesToFile(filePath1, lines2);
            } catch (IOException ex) {
                Logger.getLogger(OrderStatusClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(Main,"Order type Updated.");    
        }else{
            
        }
        
        
    }
    
    
    
}
