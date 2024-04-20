/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliveryvendor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Customer_1 {
    private String id;
    private String name;
    private String password;
    private String email;
    private String phoneNo;
    private String userType;
    private String gender;
    private double amount;
    
   public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void fileWriter(String userData, DefaultTableModel model, String file){
     try (FileWriter myWriter = new FileWriter("resources/Users.txt", true);
             BufferedWriter bWriter = new BufferedWriter(myWriter);
             PrintWriter pWriter = new PrintWriter(bWriter)) {
            pWriter.println(userData);
            JOptionPane.showMessageDialog(null, "User Registered Successfully");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error Occurred, Please Try Again");
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Get the first line and set columns names to the JTable model
            String firstLine = br.readLine();
            if (firstLine != null) {
                String[] columnsName = firstLine.split(",");
                model.setColumnIdentifiers(columnsName);

                // Read lines from the file and add data to the JTable model
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
        // Get the first line and set columns names to the JTable model
        String firstLine = br.readLine();
        if (firstLine != null) {
            String[] columnsName = firstLine.split(",");
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setColumnIdentifiers(columnsName);

            // Read lines from the file and add data to the JTable model
            br.lines().forEach(line -> {
                String[] dataRow = line.split("/");
                model.addRow(dataRow);
            });
        }
    } catch (IOException ex) {
        java.util.logging.Logger.getLogger(CustomerList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }}
    
   
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
   
public static Customer_1 getUserDetailsById(String filePath, String targetId) {
    Customer_1 user = null;

    try {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] userDetails = line.split("/");

            if (userDetails.length == 8) {
                String id = userDetails[0];
                // Check if the ID from the file matches the target ID
                if (id.equals(targetId)) {
                    String name = userDetails[1];
                    String password = userDetails[2];
                    String email = userDetails[3];
                    String phoneNo = userDetails[4];
                    String userType = userDetails[5];
                    String gender = userDetails[6];
                    double amount = Double.parseDouble(userDetails[7]);

                    user = new Customer_1();
                      
                    user.setId(id);
                    user.setName(name);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setPhoneNo(phoneNo);
                    user.setUserType(userType);
                    user.setGender(gender);
                    user.setAmount(amount);
                    break; // Found the user, so exit the loop
                }
            }
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return user;
}

public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getUserType() {
        return userType;
    }

    public String getGender() {
        return gender;
    }

    public double getAmount() {
        return amount;
    }
        // Displaying the details of all users created
        
    }

    
