package com.retailpos.main;

import java.util.List;
import java.util.Scanner;

import com.retailpos.model.Bill;
import com.retailpos.model.Customer;
import com.retailpos.model.Product;
import com.retailpos.service.BillingService;
import com.retailpos.service.InventoryService;
import com.retailpos.service.UserService;
import com.retailpos.util.FileUtil;

/*
Developer Name : Suraj Arya
Responsibility : Main Application & Integration
Concepts Used  : OOP, File Handling, Exception Handling
*/

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        InventoryService inventoryService = new InventoryService();
        BillingService billingService = new BillingService(inventoryService);

        loadProducts(inventoryService);

        try {
            if (!userService.login(sc))
                return;

            int choice;
            do {
                System.out.println("\n1. View Products");
                System.out.println("2. Create Bill");
                System.out.println("0. Exit");
                System.out.print("Choice: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        inventoryService.viewProducts();
                        break;

                    case 2:
                        createBill(sc, billingService);
                        break;
                }
            } while (choice != 0);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // Load products from file
    private static void loadProducts(InventoryService inventoryService) {
        List<String> data = FileUtil.readFromFile("products.txt");
        for (String line : data) {
            String[] p = line.split(",");
            inventoryService.addProduct(
                    new Product(
                            Integer.parseInt(p[0]),
                            p[1],
                            Double.parseDouble(p[2]),
                            Integer.parseInt(p[3])));
        }
    }

    // Billing flow
    private static void createBill(Scanner sc, BillingService billingService) throws Exception {

        System.out.print("Customer ID: ");
        int cid = Integer.parseInt(sc.nextLine());

        System.out.print("Customer Name: ");
        String cname = sc.nextLine();

        Customer customer = new Customer(cid, cname, "NA");
        Bill bill = new Bill((int) (Math.random() * 1000), customer);

        while (true) {
            System.out.print("Product ID (0 to finish): ");
            int pid = Integer.parseInt(sc.nextLine());
            if (pid == 0)
                break;

            System.out.print("Quantity: ");
            int qty = Integer.parseInt(sc.nextLine());

            billingService.addProductToBill(bill, pid, qty);
        }

        System.out.println("\n----- INVOICE -----");
        System.out.println(bill);

        FileUtil.writeToFile("bills.txt", bill.toString(), true);
    }
}
