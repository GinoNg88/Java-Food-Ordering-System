/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliveryvendor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ListClass {
    public void fileWriter(String userData, DefaultTableModel model, String file){
     try (FileWriter myWriter = new FileWriter(file, true);
             BufferedWriter bWriter = new BufferedWriter(myWriter);
             PrintWriter pWriter = new PrintWriter(bWriter)) {
            pWriter.println(userData);
            JOptionPane.showMessageDialog(null, "Process Successful");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error Occurred, Please Try Again");
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String firstLine = br.readLine();
            if (firstLine != null) {
                String[] columnsName = firstLine.split(",");
                model.setColumnIdentifiers(columnsName);

                br.lines().forEach(line -> {
                    String[] dataRow = line.split("/");
                    model.addRow(dataRow);
                });
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CustomerList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void topupReminder(int selectedRowIndex){
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a user to top-up");
        } 
    }
    
  public void readFile(String filePath, JTable table) {
    File file = new File(filePath);
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String firstLine = br.readLine();
        if (firstLine != null) {
            String[] columnsName = firstLine.split(",");
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setColumnIdentifiers(columnsName);

            String line;
            while ((line = br.readLine()) != null) {
                String[] dataRow = line.split("/");
                model.addRow(dataRow);
            }
        }
    } catch (IOException ex) {
        java.util.logging.Logger.getLogger(CustomerList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
}
    
   
   public void updateAmount(int selectedRowIndex, int topupAmount, String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder fileContent = new StringBuilder();

            int currentRow = 0;
            while ((line = br.readLine()) != null) {
                if (currentRow == selectedRowIndex + 1) {
                    String[] parts = line.split("/");
                    int currentAmount = Integer.parseInt(parts[7]);
                    int updatedAmount = currentAmount + topupAmount;
                    parts[7] = String.valueOf(updatedAmount);
                    line = String.join("/", parts);
                }
                fileContent.append(line).append("\n");
                currentRow++;
            }
            br.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(fileContent.toString());
            writer.close();

            JOptionPane.showMessageDialog(null, "Amount updated successfully for the selected user.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while updating the amount.");
        }
    }

   public void editReminder(DefaultTableModel model, int selectedRowIndex){
       if (selectedRowIndex == -1 ){
           JOptionPane.showMessageDialog(null, "Please Select a Row");
       }
   }
  public void editHandler(DefaultTableModel model, int rowIndex, String username, String password, String email, String phoneNo, String usertype, String gender, String filePath1) {
    String existingAmount = ""; 
    String[] parts = {}; 

    try {
        
        File file = new File(filePath1);

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder fileContent = new StringBuilder();

        String line;
        int currentRow = 0;
        while ((line = br.readLine()) != null) {
            if (currentRow == rowIndex + 1) {
                parts = line.split("/");
                parts[1] = username;
                parts[2] = password;
                parts[3] = email;
                parts[4] = phoneNo;
                parts[6] = gender;

                existingAmount = parts[7];

                line = String.join("/", parts);

            }
            fileContent.append(line).append("\n");
            currentRow++;
        }
        br.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath1));
        writer.write(fileContent.toString());
        writer.close();

        String[] updatedRow = {parts[0], username, password, email, phoneNo, usertype, gender, existingAmount};
        for (int i = 0; i < updatedRow.length; i++) {
            model.setValueAt(updatedRow[i], rowIndex, i);
        }

        JOptionPane.showMessageDialog(null, "Row updated successfully.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error occurred while updating the row.");
        e.printStackTrace();
    }
}
  public void editHandlerVendor(DefaultTableModel model, int rowIndex, String username, String password, String email, String phoneNo, String usertype, String gender, String filePath){
    String[] parts = {}; 
    try {
        
        File file = new File(filePath);

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder fileContent = new StringBuilder();

        String line;
        int currentRow = 0;
        while ((line = br.readLine()) != null) {
            if (currentRow == rowIndex + 1) {
                parts = line.split("/");
                parts[1] = username;
                parts[2] = password;
                parts[3] = email;
                parts[4] = phoneNo;
                parts[6] = gender;


                line = String.join("/", parts);

            }
            fileContent.append(line).append("\n");
            currentRow++;
        }
        br.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(fileContent.toString());
        writer.close();

        // Update the table model with the edited row
        String[] updatedRow = {parts[0], username, password, email, phoneNo, usertype, gender};
        for (int i = 0; i < updatedRow.length; i++) {
            model.setValueAt(updatedRow[i], rowIndex, i);
        }

        JOptionPane.showMessageDialog(null, "Row updated successfully.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error occurred while updating the row.");
        e.printStackTrace();
    }}
  
  void deleteRow(DefaultTableModel model, int selectedRowIndex, String filePath) {
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        } else {
            model.removeRow(selectedRowIndex);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                // Write column names
                for (int i = 0; i < model.getColumnCount(); i++) {
                    writer.write(model.getColumnName(i));
                    if (i < model.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");

                // Write data
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        writer.write(model.getValueAt(i, j).toString());
                        if (j < model.getColumnCount() - 1) {
                            writer.write("/");
                        }
                    }
                    writer.write("\n");
                }

                JOptionPane.showMessageDialog(null, "Row deleted successfully.");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error occurred while deleting the row.");
                e.printStackTrace();
            }



        }
  }
}
