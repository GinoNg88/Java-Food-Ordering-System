package deliveryvendor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class VendorRunner {
    private String id;
    private String name;
    private String password;
    private String email;
    private String phoneNo;
    private String userType;
    private String gender;
    
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

   
public static VendorRunner getUserDetailsById(String filePath, String targetId) {
    VendorRunner user = null;

    try {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] userDetails = line.split("/");

            if (userDetails.length == 7) {
                String id = userDetails[0];
                // Check if the ID from the file matches the target ID
                if (id.equals(targetId)) {
                    String name = userDetails[1];
                    String password = userDetails[2];
                    String email = userDetails[3];
                    String phoneNo = userDetails[4];
                    String userType = userDetails[5];
                    String gender = userDetails[6];

                    user = new VendorRunner();
                    user.setId(id);
                    user.setName(name);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setPhoneNo(phoneNo);
                    user.setUserType(userType);
                    user.setGender(gender);
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
}
