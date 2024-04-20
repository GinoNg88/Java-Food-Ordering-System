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
public class DeliveryTaskClass extends DeliveryClass {
    @Override
    public void loadTableData(String filePath,int[]displayindex, DefaultTableModel model) throws IOException {
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
                if ("delivery unassigned".equals(rowData[7]) && ("preparing".equals(rowData[6]) || "ready".equals(rowData[6]))) {
                    model.addRow(rowData);
                } else {
                    // Handle other conditions if needed
                }
            }
        }
    }
    
    
    public boolean checkStatus(String filePath, int[] displayindex, String ID) throws IOException {
        boolean bool = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the header line and set column names
            reader.readLine();           
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("/");
                Object[] rowData = new Object[displayindex.length];
                if (displayindex.length > 1) {
                    for (int i = 0; i < displayindex.length; i++) {
                        int columnIndex = displayindex[i];
                        rowData[i] = data[columnIndex];
                    }
                }
                if ("delivery assigned".equals(rowData[7]) && (ID.equals(rowData[9])) && (!("completed").equals(rowData[6]))) {
                    bool = false;  
                    break;
                }
            }       
        }
        return bool;
    }
        
    

    
    
    
}
