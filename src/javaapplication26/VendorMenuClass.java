/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliveryvendor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class VendorMenuClass extends FunctionClass {
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
                if(ID.equals(rowData[4])){
                   model.addRow(rowData); 
                }else{
                    
                }
            }
        }
    }
    
    public void modifyDetails(String[] lines, String foodId, String newName,String newPrice) {
        for (int i = 1; i < lines.length; i++) { // Start from 1 to skip header
            String[] parts = lines[i].split("/");
            if (parts.length >= 5 && parts[0].equals(foodId)) {
                
                parts[1] = newName; 
                parts[2]= newPrice;
                lines[i] = String.join("/", parts);
                //System.out.println("Modified order: " + Arrays.toString(parts));
                break; // Stop searching after finding the order
            }
        }
    }
    
    public String determineLastID(String filePath) throws IOException {
        String lastID = "00";  // Default ID if no rows exist

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("/");
                if (parts.length > 0&& parts[0].length() > 1) {
                    String id = parts[0].substring(1);  // Exclude the 'F' prefix
                    lastID = id;
                }
            }
        }

        return lastID;
    }

    public String incrementID(String lastID) {
        int idNumber = Integer.parseInt(lastID);
        idNumber++;  // Increment the ID
        return String.format("%02d", idNumber);  // Format as two digits
    }
    
    public void addRowWithIncrementedIDToFile(String filePath, String newRowData) throws IOException {
        // Read the existing content to determine the next ID
        VendorMenuClass obj = new VendorMenuClass();
        String lastID = obj.determineLastID(filePath);
        String nextID = obj.incrementID(lastID);

        // Create the new row with the incremented ID
        String newRow = "F" + nextID +"/"+ newRowData;

        // Append the new row to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(newRow);
            writer.newLine();  // Add a new line after the new row
        }
    }
    
    public String writeNewRow( String newFoodName, String newPrice, String Vendor,String VendorID,String Restaurant) throws IOException {
        
            // Append the new row to the end of the file
            String newRow = newFoodName+"/"+newPrice+"/"+Vendor+"/"+VendorID+"/"+Restaurant;
            return newRow;
        
    }
    public void deleteRow(String filePath, String foodIdToRemove) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Check if the line starts with the specified Food ID
                if (!currentLine.startsWith(foodIdToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }

            writer.close();
            reader.close();

            // Replace the original file with the temporary file
            Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Row with Food ID '" + foodIdToRemove + "' removed successfully.");

                   
            
    }
    
     public void Initialize(String filePath, JLabel lblID,JTextField txtName, JTextField txtPrice) throws IOException {
        // Read the existing content to determine the next ID
        VendorMenuClass obj = new VendorMenuClass();
        String lastID = obj.determineLastID(filePath);
        String nextID = obj.incrementID(lastID);
        lblID.setText("F"+nextID);
        txtName.setText("");
        txtPrice.setText("");
        
    }
    
}
