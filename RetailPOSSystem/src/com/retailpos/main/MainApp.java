/*
Developer Name : Suraj Arya
PRN Number : STT-25128071920
Responsibility: Main Application - Menu Driven Program & User Authentication
Concepts Used : Scanner, Switch-Case, Menu Driven Programming, Exception Handling,
                File Handling, User Authentication, Modular Design
*/

package com.retailpos.main;

import com.retailpos.model.Product;
import com.retailpos.model.Customer;
import com.retailpos.model.Bill;
import com.retailpos.service.InventoryService;
import com.retailpos.service.BillingService;
import com.retailpos.exception.*;
import com.retailpos.util.FileUtil;
import com.retailpos.util.ValidationUtil;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Main Application class for Retail POS System
 * Provides menu-driven interface for all operations
 */
public class MainApp {

    private static Scanner scanner = new Scanner(System.in);
    private static InventoryService inventoryService;
    private static BillingService billingService;
    private static String currentUser;
    private static String currentUserRole;

    /**
     * Main method - Entry point of application
     */
    public static void main(String[] args) {

        // Initialize files
        FileUtil.initializeFiles();

        // Initialize services
        inventoryService = new InventoryService();
        billingService = new BillingService(inventoryService);

        // Display welcome screen
        displayWelcomeScreen();

        // User authentication
        try {
            if (!authenticateUser()) {
                System.out.println("Authentication failed. Exiting...");
                return;
            }
        } catch (AuthenticationException e) {
            System.out.println("✗ " + e.getMessage());
            return;
        }

        // Main menu loop
        boolean running = true;
        while (running) {
            try {
                displayMainMenu();
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        productManagementMenu();
                        break;
                    case "2":
                        inventoryManagementMenu();
                        break;
                    case "3":
                        billingMenu();
                        break;
                    case "4":
                        customerManagementMenu();
                        break;
                    case "5":
                        reportsMenu();
                        break;
                    case "6":
                        running = false;
                        exitApplication();
                        break;
                    default:
                        System.out.println("✗ Invalid choice. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }

    /**
     * Display welcome screen
     */
    private static void displayWelcomeScreen() {
        System.out.println("\n");
        System.out.println("==================================================================");
        System.out.println("                                                                  ");
        System.out.println("           ██████╗ ███████╗████████╗ █████╗ ██╗██╗              ");
        System.out.println("           ██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║██║              ");
        System.out.println("           ██████╔╝█████╗     ██║   ███████║██║██║              ");
        System.out.println("           ██╔══██╗██╔══╝     ██║   ██╔══██║██║██║              ");
        System.out.println("           ██║  ██║███████╗   ██║   ██║  ██║██║███████╗         ");
        System.out.println("           ╚═╝  ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝╚══════╝         ");
        System.out.println("                                                                  ");
        System.out.println("                    POS SYSTEM v1.0                              ");
        System.out.println("                                                                  ");
        System.out.println("==================================================================");
        System.out.println("                  Developed by Team of RetailRangers              ");
        System.out.println("     (Suraj Arya, Aryan Pandey, Vaivabhai Anand, Atul Mishra)     ");
        System.out.println("==================================================================\n");
    }

    /**
     * User authentication
     * 
     * @return True if authenticated
     * @throws AuthenticationException If authentication fails
     */
    private static boolean authenticateUser() throws AuthenticationException {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║              USER LOGIN               ║");
        System.out.println("╚═══════════════════════════════════════╝");

        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {
            try {
                System.out.print("Username: ");
                String username = scanner.nextLine().trim();

                System.out.print("Password: ");
                String password = scanner.nextLine().trim();

                // Read users from file
                ArrayList<String> users = FileUtil.readFromFile(FileUtil.USERS_FILE);

                for (String line : users) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3) {
                        if (parts[0].equals(username) && parts[1].equals(password)) {
                            currentUser = username;
                            currentUserRole = parts[2];
                            System.out.println("\n✓ Login successful!");
                            System.out.println("Welcome, " + username + " (" + currentUserRole + ")");
                            Thread.sleep(1000);
                            return true;
                        }
                    }
                }

                attempts++;
                System.out.println("✗ Invalid credentials. Attempts remaining: " + (maxAttempts - attempts));

            } catch (IOException e) {
                throw new AuthenticationException("Error reading user data: " + e.getMessage());
            } catch (InterruptedException e) {
                // Ignore
            }
        }

        throw new AuthenticationException("Maximum login attempts exceeded");
    }

    /**
     * Display main menu
     */
    private static void displayMainMenu() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║           MAIN MENU                   ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║ 1. Product Management                 ║");
        System.out.println("║ 2. Inventory Management               ║");
        System.out.println("║ 3. Billing System                     ║");
        System.out.println("║ 4. Customer Management                ║");
        System.out.println("║ 5. Reports                            ║");
        System.out.println("║ 6. Exit                               ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    /**
     * Product Management Menu
     */
    private static void productManagementMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║      PRODUCT MANAGEMENT               ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ 1. Add New Product                    ║");
            System.out.println("║ 2. View All Products                  ║");
            System.out.println("║ 3. Search Product                     ║");
            System.out.println("║ 4. Update Product                     ║");
            System.out.println("║ 5. Delete Product                     ║");
            System.out.println("║ 6. Back to Main Menu                  ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        addProduct();
                        break;
                    case "2":
                        inventoryService.viewAllProducts();
                        break;
                    case "3":
                        searchProduct();
                        break;
                    case "4":
                        updateProduct();
                        break;
                    case "5":
                        deleteProduct();
                        break;
                    case "6":
                        back = true;
                        break;
                    default:
                        System.out.println("✗ Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }

    /**
     * Add new product
     */
    private static void addProduct() {
        try {
            System.out.println("\n--- Add New Product ---");

            String productId = inventoryService.generateNextProductId();
            System.out.println("Product ID (Auto-generated): " + productId);

            System.out.print("Product Name: ");
            String name = scanner.nextLine().trim();
            ValidationUtil.validateNotEmpty(name, "Product Name");

            System.out.print("Category: ");
            String category = scanner.nextLine().trim();
            ValidationUtil.validateNotEmpty(category, "Category");

            System.out.print("Price: ");
            String priceStr = scanner.nextLine().trim();
            double price = ValidationUtil.validatePositiveDouble(priceStr, "Price");

            System.out.print("Stock Quantity: ");
            String stockStr = scanner.nextLine().trim();
            int stock = ValidationUtil.validatePositiveInteger(stockStr, "Stock Quantity");

            System.out.print("Description: ");
            String description = scanner.nextLine().trim();

            Product product = new Product(productId, name, category, price, stock, description);
            inventoryService.addProduct(product);

        } catch (InvalidInputException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    /**
     * Search product
     */
    private static void searchProduct() {
        System.out.print("\nEnter search term (ID/Name/Category): ");
        String searchTerm = scanner.nextLine().trim();

        ArrayList<Product> results = inventoryService.searchProducts(searchTerm);

        if (results.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("\n--- Search Results ---");
            for (Product product : results) {
                product.displayProduct();
            }
        }
    }

    /**
     * Update product
     */
    private static void updateProduct() {
        try {
            System.out.print("\nEnter Product ID to update: ");
            String productId = scanner.nextLine().trim();

            Product existingProduct = inventoryService.getProduct(productId);
            existingProduct.displayProduct();

            System.out.println("\nEnter new details (press Enter to keep current value):");

            System.out.print("Product Name [" + existingProduct.getProductName() + "]: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty())
                existingProduct.setProductName(name);

            System.out.print("Category [" + existingProduct.getCategory() + "]: ");
            String category = scanner.nextLine().trim();
            if (!category.isEmpty())
                existingProduct.setCategory(category);

            System.out.print("Price [" + existingProduct.getPrice() + "]: ");
            String priceStr = scanner.nextLine().trim();
            if (!priceStr.isEmpty()) {
                double price = ValidationUtil.validatePositiveDouble(priceStr, "Price");
                existingProduct.setPrice(price);
            }

            System.out.print("Stock Quantity [" + existingProduct.getStockQuantity() + "]: ");
            String stockStr = scanner.nextLine().trim();
            if (!stockStr.isEmpty()) {
                int stock = ValidationUtil.validatePositiveInteger(stockStr, "Stock");
                existingProduct.setStockQuantity(stock);
            }

            System.out.print("Description [" + existingProduct.getDescription() + "]: ");
            String description = scanner.nextLine().trim();
            if (!description.isEmpty())
                existingProduct.setDescription(description);

            inventoryService.updateProduct(productId, existingProduct);

        } catch (ProductNotFoundException | InvalidInputException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    /**
     * Delete product
     */
    private static void deleteProduct() {
        try {
            System.out.print("\nEnter Product ID to delete: ");
            String productId = scanner.nextLine().trim();

            Product product = inventoryService.getProduct(productId);
            product.displayProduct();

            System.out.print("Are you sure you want to delete this product? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes")) {
                inventoryService.deleteProduct(productId);
            } else {
                System.out.println("Delete operation cancelled.");
            }

        } catch (ProductNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    /**
     * Inventory Management Menu
     */
    private static void inventoryManagementMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║     INVENTORY MANAGEMENT              ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ 1. Check Stock                        ║");
            System.out.println("║ 2. Add Stock                          ║");
            System.out.println("║ 3. Low Stock Alert                    ║");
            System.out.println("║ 4. Inventory Value                    ║");
            System.out.println("║ 5. Back to Main Menu                  ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        checkStock();
                        break;
                    case "2":
                        addStock();
                        break;
                    case "3":
                        inventoryService.displayLowStockAlert();
                        break;
                    case "4":
                        displayInventoryValue();
                        break;
                    case "5":
                        back = true;
                        break;
                    default:
                        System.out.println("✗ Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }

    /**
     * Check stock for a product
     */
    private static void checkStock() {
        try {
            System.out.print("\nEnter Product ID: ");
            String productId = scanner.nextLine().trim();

            Product product = inventoryService.getProduct(productId);

            System.out.println("\n--- Stock Information ---");
            System.out.println("Product ID       : " + product.getProductId());
            System.out.println("Product Name     : " + product.getProductName());
            System.out.println("Available Stock  : " + product.getStockQuantity());
            System.out.println("Status           : " + (product.isInStock() ? "In Stock" : "Out of Stock"));

        } catch (ProductNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    /**
     * Add stock to a product
     */
    private static void addStock() {
        try {
            System.out.print("\nEnter Product ID: ");
            String productId = scanner.nextLine().trim();

            Product product = inventoryService.getProduct(productId);
            System.out.println("Current Stock: " + product.getStockQuantity());

            System.out.print("Enter quantity to add: ");
            String qtyStr = scanner.nextLine().trim();
            int quantity = ValidationUtil.validatePositiveInteger(qtyStr, "Quantity");

            inventoryService.addStock(productId, quantity);

            System.out.println("New Stock: " + inventoryService.getProduct(productId).getStockQuantity());

        } catch (ProductNotFoundException | InvalidInputException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    /**
     * Display total inventory value
     */
    private static void displayInventoryValue() {
        double totalValue = inventoryService.getTotalInventoryValue();
        int productCount = inventoryService.getProductCount();

        System.out.println("\n--- Inventory Summary ---");
        System.out.println("Total Products       : " + productCount);
        System.out.println("Total Inventory Value: Rs. " + String.format("%.2f", totalValue));
    }

    /**
     * Billing Menu
     */
    private static void billingMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║        BILLING SYSTEM                 ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ 1. Create New Bill                    ║");
            System.out.println("║ 2. View All Bills                     ║");
            System.out.println("║ 3. Search Bill                        ║");
            System.out.println("║ 4. Back to Main Menu                  ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        createBill();
                        break;
                    case "2":
                        billingService.viewAllBills();
                        break;
                    case "3":
                        searchBill();
                        break;
                    case "4":
                        back = true;
                        break;
                    default:
                        System.out.println("✗ Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }

    /**
     * Create new bill
     */
    private static void createBill() {
        try {
            System.out.println("\n--- Create New Bill ---");

            System.out.print("Enter Customer ID: ");
            String customerId = scanner.nextLine().trim();

            Bill bill = billingService.createBill(customerId, currentUser);
            System.out.println("Bill created: " + bill.getBillId());

            boolean addingItems = true;
            while (addingItems) {
                System.out.print("\nEnter Product ID (or 'done' to finish): ");
                String productId = scanner.nextLine().trim();

                if (productId.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.print("Enter Quantity: ");
                String qtyStr = scanner.nextLine().trim();
                int quantity = ValidationUtil.validatePositiveInteger(qtyStr, "Quantity");

                try {
                    billingService.addItemToBill(bill, productId, quantity);
                } catch (ProductNotFoundException | InsufficientStockException e) {
                    System.out.println("✗ " + e.getMessage());
                    System.out.print("Continue adding items? (yes/no): ");
                    if (!scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                        addingItems = false;
                    }
                }
            }

            if (bill.getItemCount() > 0) {
                System.out.print("\nConfirm billing? (yes/no): ");
                String confirm = scanner.nextLine().trim().toLowerCase();

                if (confirm.equals("yes")) {
                    billingService.completeBilling(bill);
                } else {
                    System.out.println("Billing cancelled.");
                }
            } else {
                System.out.println("No items added. Billing cancelled.");
            }

        } catch (InvalidInputException | ProductNotFoundException | InsufficientStockException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    /**
     * Search bill
     */
    private static void searchBill() {
        System.out.print("\nEnter search term (Bill ID/Customer ID/Name): ");
        String searchTerm = scanner.nextLine().trim();

        ArrayList<Bill> results = billingService.searchBills(searchTerm);

        if (results.isEmpty()) {
            System.out.println("No bills found.");
        } else {
            System.out.println("\n--- Search Results ---");
            for (Bill bill : results) {
                bill.displayInvoice();
            }
        }
    }

    /**
     * Customer Management Menu
     */
    private static void customerManagementMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║     CUSTOMER MANAGEMENT               ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ 1. Add New Customer                   ║");
            System.out.println("║ 2. View All Customers                 ║");
            System.out.println("║ 3. Search Customer                    ║");
            System.out.println("║ 4. Back to Main Menu                  ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        addCustomer();
                        break;
                    case "2":
                        billingService.viewAllCustomers();
                        break;
                    case "3":
                        searchCustomer();
                        break;
                    case "4":
                        back = true;
                        break;
                    default:
                        System.out.println("✗ Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }

    /**
     * Add new customer
     */
    private static void addCustomer() {
        try {
            System.out.println("\n--- Add New Customer ---");

            String customerId = billingService.generateNextCustomerId();
            System.out.println("Customer ID (Auto-generated): " + customerId);

            System.out.print("Customer Name: ");
            String name = scanner.nextLine().trim();
            ValidationUtil.validateNotEmpty(name, "Customer Name");

            System.out.print("Phone Number: ");
            String phone = scanner.nextLine().trim();
            ValidationUtil.validatePhoneNumber(phone);

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            ValidationUtil.validateEmail(email);

            Customer customer = new Customer(customerId, name, phone, email);
            billingService.addCustomer(customer);

        } catch (InvalidInputException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    /**
     * Search customer
     */
    private static void searchCustomer() {
        System.out.print("\nEnter search term (ID/Name/Phone): ");
        String searchTerm = scanner.nextLine().trim();

        ArrayList<Customer> results = billingService.searchCustomers(searchTerm);

        if (results.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("\n--- Search Results ---");
            for (Customer customer : results) {
                customer.displayCustomer();
            }
        }
    }

    /**
     * Reports Menu
     */
    private static void reportsMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║           REPORTS                     ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ 1. Sales Report                       ║");
            System.out.println("║ 2. Inventory Report                   ║");
            System.out.println("║ 3. Customer Report                    ║");
            System.out.println("║ 4. Back to Main Menu                  ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    billingService.generateSalesReport();
                    break;
                case "2":
                    inventoryService.viewAllProducts();
                    displayInventoryValue();
                    break;
                case "3":
                    billingService.viewAllCustomers();
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("✗ Invalid choice.");
            }
        }
    }

    /**
     * Exit application
     */
    private static void exitApplication() {
        System.out.println("\n==================================================================");
        System.out.println("              Thank you for using Retail POS System!              ");
        System.out.println("                           Goodbye!                               ");
        System.out.println("==================================================================\n");
        scanner.close();
    }
}