/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliveryvendor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class CustomerNotificationClass {
 public void displayNotificationsForID(String filePath, JTable table, String targetID) {
    File file = new File(filePath);
    DefaultTableModel model = (DefaultTableModel) table.getModel();

    // Set the column name to "Notification" for the JTable model
    model.setColumnIdentifiers(new String[]{"Notification"});

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        // Skip the first line (containing column headers)
        br.readLine();

        // Read lines from the file and add notification data matching the ID to the JTable model
        String line;
        while ((line = br.readLine()) != null) {
            String[] dataRow = line.split("/", 2); // Split line into parts using slash as delimiter
            if (dataRow.length == 2 && dataRow[0].equals(targetID)) {
                model.addRow(new String[]{dataRow[1]}); // Add notification data to the table
            }
        }
    } catch (IOException ex) {
        java.util.logging.Logger.getLogger(CustomerList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
}
}
