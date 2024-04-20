/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliveryvendor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class VendorOrderClass extends FunctionClass {
    public void loadPendingTableData(String filePath,int[]displayindex, DefaultTableModel model,String ID) throws IOException {
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
                if("pending".equals(rowData[8])&&(ID.equals(rowData[6]))){
                   model.addRow(rowData); 
                }else{
                    
                }
            }
        }
    }
    
    public void loadOrderTableData(String filePath,int[]displayindex, DefaultTableModel model,String ID) throws IOException {
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
                if(("preparing".equals(rowData[8])||"ready".equals(rowData[8]))&&(ID.equals(rowData[6]))){
                   model.addRow(rowData); 
                }else{
                    
                }
            }
        }
    }
    
    public void modifyStatus(String[] lines, String orderId, String newStatus,JPanel Main) {
        for (int i = 1; i < lines.length; i++) { // Start from 1 to skip header
            String[] parts = lines[i].split("/");
            if (parts.length >= 12 && parts[0].equals(orderId)) {
                //System.out.println("Found order to modify: " + Arrays.toString(parts));
                if(checkReadyStatus(parts)){
                    parts[8] = newStatus; 
                    lines[i] = String.join("/", parts);
                    JOptionPane.showMessageDialog(Main, "Order Status Updated!");
                    break; // Stop searching after finding the order
                }else{
                    JOptionPane.showMessageDialog(Main, "Staus is ready initially.");
                }
                
            }
        }
    }
    
    public void Refund(String[] lines, String CustomerId, Double price,JPanel Main) {
        for (int i = 1; i < lines.length; i++) { // Start from 1 to skip header
            String[] parts = lines[i].split("/");
            if (parts.length >= 7 && parts[0].equals(CustomerId)) {
                Double newCredit = Double.valueOf(parts[7])+price;
                parts[7] = String.format("%.2f",newCredit);
                    lines[i] = String.join("/", parts);
                    JOptionPane.showMessageDialog(Main, "Food Charge refunded.");
                    break; // Stop searching after finding the order
            }
        }
    }
    
    public boolean checkReadyStatus(String[]parts){
        boolean bool = true;
        if("ready".equals(parts[8])){
            bool = false;
        }else{
            
        }
        return bool;
    }
}
