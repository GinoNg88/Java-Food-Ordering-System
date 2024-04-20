/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package deliveryvendor;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 *
 * @author User
 */
public class FoodAssignment {
    public static void main(String[] args) throws IOException {
        File file = new File("customer.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        //Admin newAdmin = Admin.register();
        //String adminInfo = newAdmin.toString();
javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CustomerList userRegistrationUI = new CustomerList();
                userRegistrationUI.setVisible(true);
            }
        });
        // Call the UserDataWriter
        UserDataWriter.writeUserData("sampleUsername", "samplePassword", "sampleUserType");
    }
}

class UserDataWriter {
    public static void writeUserData(String username, String password, String userType) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customer.txt", true))) {
            String userData = username + "," + password + "," + userType;
            writer.write(userData);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



        
        
        
        
   
    

