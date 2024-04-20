/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliveryvendor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class DeliveryStatusClass extends DeliveryClass{
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
                if("delivery assigned".equals(rowData[7])&&(ID.equals(rowData[9]))&&(!("completed").equals(rowData[6]))){
                    if("preparing".equals(rowData[6])){
                        lblStatus.setText("Vendor is preparing the food...");
                        model.addRow(rowData); 
                        break;                                                
                    }else if("ready".equals(rowData[6])){
                        lblStatus.setText( "The food is ready to deliver, please head to the restaurant ASAP.");
                        model.addRow(rowData);
                        break;
                    }else{
                        
                    }                                       
                }else{                   
                    
                }
            }
        }
    }
    
    public boolean checkTaskCount(String filePath,int[]displayindex, DefaultTableModel model,String ID) throws IOException {
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
                if("delivery assigned".equals(rowData[7])&&(ID.equals(rowData[9]))&&(!("completed").equals(rowData[6]))){
                    model.addRow(rowData);       
                }
                if(model.getRowCount()>1){                    
                    bool= false;
                    break;
                } 
            }
            model.setColumnCount(0);
            model.setRowCount(0);
        }
        return bool;
    }
    
    
}
