/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliveryvendor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class SettingsClass {
    
     public void updateUserDetails(Customer_1 user, String filePath, String name, String password, String email, String phoneno) {
    try {
        File file = new File(filePath);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "File does not exist: " + filePath);
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder updatedContent = new StringBuilder();
        String line;
        boolean userFound = false;
if (userFound = true){
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("/");
            if (parts.length >= 7 && parts[0].equals(user.getId())) {
                parts[1] = name;
                parts[2] = password;
                parts[3] = email;
                parts[4] = phoneno;
              System.out.println(parts[0]);
                System.out.println(user.getName());
                System.out.println(user.getPassword());
                
                // Retain other fields
            }
            updatedContent.append(String.join("/", parts)).append("\n");
        }
        reader.close();
}
      else if (!userFound) {
            JOptionPane.showMessageDialog(null, "User not found with ID: " + user.getId());
            return;
        }

        FileWriter writer = new FileWriter(file);
        writer.write(updatedContent.toString());
        writer.close();

        JOptionPane.showMessageDialog(null, "Details updated successfully.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error occurred while updating details: " + e.getMessage());
        e.printStackTrace();
    }
     }
     
     public void updateUserDetailsVR(VendorRunner user, String filePath, String name, String password, String email, String phoneno) {
    try {
        File file = new File(filePath);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "File does not exist: " + filePath);
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder updatedContent = new StringBuilder();
        String line;
        boolean userFound = false;
if (userFound = true){
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("/");
            if (parts.length >= 7 && parts[0].equals(user.getId())) {
                parts[1] = name;
                parts[2] = password;
                parts[3] = email;
                parts[4] = phoneno;
              System.out.println(parts[0]);
                System.out.println(user.getName());
                System.out.println(user.getPassword());
                
                // Retain other fields
            }
            updatedContent.append(String.join("/", parts)).append("\n");
        }
        reader.close();
}
      else if (!userFound) {
            JOptionPane.showMessageDialog(null, "User not found with ID: " + user.getId());
            return;
        }

        FileWriter writer = new FileWriter(file);
        writer.write(updatedContent.toString());
        writer.close();

        JOptionPane.showMessageDialog(null, "Details updated successfully.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error occurred while updating details: " + e.getMessage());
        e.printStackTrace();
    }
}
}
