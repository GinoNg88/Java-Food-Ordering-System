/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliveryvendor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class DeliveryHistoryClass extends DeliveryClass{
    public void loadTableData(String filePath,int[]displayindex, DefaultTableModel model,String ID) throws IOException {
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
                if("delivery assigned".equals(rowData[7])&&(ID.equals(rowData[9]))&&("completed".equals(rowData[6]))) {
                    model.addRow(rowData);
                } else {
                    // Handle other conditions if needed
                }
            }
        }
    }
    
    public double calculateRevenue(String filePath, double revenue, String ID) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the header line and set column names
            String headerLine = reader.readLine();
            String[] allColumns = headerLine.split(",");
            
            // Read data lines and add them to the table model
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split("/");
                if("delivery assigned".equals(rowData[9])&&(ID.equals(rowData[11]))&&("completed".equals(rowData[8]))){
                   revenue = revenue + 5;                   
                }else{
                    
                }                
            }
        }
        return revenue;        
    }
    
}
