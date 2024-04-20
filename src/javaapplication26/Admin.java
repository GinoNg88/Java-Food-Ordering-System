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
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User
 */

public class Admin extends javax.swing.JFrame {
    private JTable jTable1;
    public String LoginID;
    public double credit;
    public Admin(){
        
    }
    
    public boolean authenticateFromFile(String username, String password, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] dataRow = line.split("/");
                if (dataRow.length >= 2) {
                    String fileID = dataRow[0].trim();
                    String filePassword = dataRow[2].trim();
                    if (username.equals(fileID) && password.equals(filePassword)) {
                    
                        return true; 
                    }
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        return false;
    }
    

  public static String DeliveryID(String filePath) {
        ArrayList<String> ids = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("/");
                if (data.length > 0 && data[0].startsWith("D")) {
                    ids.add(data[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Find the highest ID
        String latestId = "D00";
        for (String id : ids) {
            if (id.compareTo(latestId) > 0) {
                latestId = id;
            }
        }
        return latestId;
    }
    
   public static String CustomerID(String filePath) {
        ArrayList<String> ids = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("/");
                if (data.length > 0 && data[0].startsWith("S")) {
                    ids.add(data[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Find the highest ID
        String latestId = "S00";
        for (String id : ids) {
            if (id.compareTo(latestId) > 0) {
                latestId = id;
            }
        }
        return latestId;
    }
   
   public static String generateNextID(String latestId) {
        String prefix = latestId.substring(0, 1);
        int num = Integer.parseInt(latestId.substring(1));
        num++;
        return prefix + String.format("%02d", num);
    }
    
  public static String VendorID(String filePath) {
        ArrayList<String> ids = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("/");
                if (data.length > 0 && data[0].startsWith("V")) {
                    ids.add(data[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Find the highest ID
        String latestId = "V00";
        for (String id : ids) {
            if (id.compareTo(latestId) > 0) {
                latestId = id;
            }
        }
        return latestId;
    }
    
    
    
            }
    
                
        
         
