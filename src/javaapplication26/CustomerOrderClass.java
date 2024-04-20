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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class CustomerOrderClass extends FunctionClass {
    @Override
    public void loadTableData(String filePath,int[]displayindex, DefaultTableModel model) throws IOException {
        super.loadTableData(filePath, displayindex, model);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = reader.readLine(); //to skip first header line because there is not condition for showing foods
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
                model.addRow(rowData);                
            }
        }
    }
    
    
    public void Payment(String[] lines, String CustomerId,JPanel Main,double price) {
        for (int i = 1; i < lines.length; i++) { // Start from 1 to skip header
            String[] parts = lines[i].split("/");
            if (parts.length >= 7 && parts[0].equals(CustomerId)) {
                Double newCredit = Double.parseDouble(parts[7])-price;
                parts[7] = String.format("%.2f",newCredit);
                    lines[i] = String.join("/", parts);
                    JOptionPane.showMessageDialog(Main, "payment successful.");
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
        // Append the new row to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(newRowData);
            writer.newLine();  // Add a new line after the new row
        }
    }
    
    
    
    public String writeNewRow( String OrderID,String Date,String FoodName, int Quantity, String PricePaid,String Vendor,String VendorID,String CustomerID,String Status,String Type,String Destination,String DmID,String Restaurant,String Review) throws IOException {
        
            // Append the new row to the end of the file
            String newRow = OrderID+"/"+Date+"/"+FoodName+"/"+Integer.toString(Quantity)+"/"+PricePaid+"/"+Vendor+"/"+VendorID+"/"+CustomerID+"/"+Status+"/"+Type+"/"+Destination+"/"+DmID+"/"+Restaurant+"/"+Review;
            return newRow;
        
    }
    
    
     public void Initialize(String filePath, JLabel lblOrderID,JLabel lblFoodName,JLabel lblFoodPrice,JLabel lblRestaurant, JTextField txtQuantity,JComboBox ComboType) throws IOException {
        // Read the existing content to determine the next ID
        CustomerOrderClass obj = new CustomerOrderClass();
        String lastID = obj.determineLastID(filePath);
        String nextID = obj.incrementID(lastID);
        lblOrderID.setText("O"+nextID);
        lblFoodName.setText("none chosen");
        lblFoodPrice.setText("none chosen");
        lblRestaurant.setText("none chosen");
        txtQuantity.setText("");
        ComboType.setSelectedIndex(0);
        
    }
     
      

    public String ReadAmount(String filePath,String LoginID)throws IOException{
        String amount = "0.00";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("/");
                if (parts.length > 7&& parts[7].length() > 1&& parts[0].equals(LoginID)) {
                    amount = parts[7];  // Exclude the 'F' prefix
                    //lastID = id;
                }
            }
        }
        return amount;
    }
}
