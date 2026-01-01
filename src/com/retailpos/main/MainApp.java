package com.retailpos.main;

import java.util.Scanner;
import com.retailpos.service.UserService;

/*
Developer Name : Suraj Arya
Responsibility : Main Application & Menu Handling
Concepts Used  : OOP, Scanner, Method Calling, Packages
*/

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("================================");
        System.out.println("     RETAIL POS SYSTEM");
        System.out.println("================================");

        try {
            if (userService.login(sc)) {
                int choice;
                do {
                    System.out.println("\n----- MAIN MENU -----");
                    System.out.println("1. Product Management");
                    System.out.println("2. Billing");
                    System.out.println("3. Customer Management");
                    System.out.println("0. Exit");
                    System.out.print("Enter choice: ");

                    choice = Integer.parseInt(sc.nextLine());

                    switch (choice) {
                        case 1:
                            System.out.println("Product Module (Handled by Person 3)");
                            break;
                        case 2:
                            System.out.println("Billing Module (Handled by Person 3)");
                            break;
                        case 3:
                            System.out.println("Customer Module (Handled by Person 2)");
                            break;
                        case 0:
                            System.out.println("Thank you for using Retail POS System.");
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                } while (choice != 0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
