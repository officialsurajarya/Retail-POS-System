package com.retailpos.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.retailpos.exception.AuthenticationException;

/*
Developer Name : Suraj Arya
Responsibility : User Login & Authentication
Concepts Used  : File Handling, Exception Handling, String Handling
*/

public class UserService {

    private static final String USER_FILE = "users.txt";

    public boolean login(Scanner sc) throws AuthenticationException {
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        return authenticate(username, password);
    }

    private boolean authenticate(String username, String password)
            throws AuthenticationException {

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                // format: username,password,role
                String[] data = line.split(",");

                if (data[0].equals(username) && data[1].equals(password)) {
                    System.out.println("Login successful! Role: " + data[2]);
                    return true;
                }
            }
        } catch (Exception e) {
            throw new AuthenticationException("Unable to read user file.");
        }

        throw new AuthenticationException("Invalid Username or Password!");
    }
}
