/*
Developer Name : Vaibhavi Anand
PRN Number : STT-25128071922
Responsibility: Business Logic - Billing Service
Concepts Used : ArrayList, HashMap, File Handling, Exception Handling, Collections,
                Business Logic Implementation, Transaction Management
*/

package com.retailpos.service;

import com.retailpos.model.Bill;
import com.retailpos.model.Customer;
import com.retailpos.model.Product;
import com.retailpos.exception.ProductNotFoundException;
import com.retailpos.exception.InsufficientStockException;
import com.retailpos.exception.InvalidInputException;
import com.retailpos.util.FileUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Service class for billing operations
 * Handles all billing and customer-related business logic
 */
public class BillingService {

    private HashMap<String, Customer> customers;
    private ArrayList<Bill> bills;
    private InventoryService inventoryService;

    /**
     * Constructor - loads customers and bills from file
     */
    public BillingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.customers = new HashMap<>();
        this.bills = new ArrayList<>();
        loadCustomers();
        loadBills();
    }

    /**
     * Load customers from file
     */
    private void loadCustomers() {
        try {
            ArrayList<String> lines = FileUtil.readFromFile(FileUtil.CUSTOMERS_FILE);
            for (String line : lines) {
                Customer customer = Customer.fromFileString(line);
                if (customer != null) {
                    customers.put(customer.getCustomerId(), customer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading customers: " + e.getMessage());
        }
    }

    /**
     * Save customers to file
     */
    private void saveCustomers() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Customer customer : customers.values()) {
                lines.add(customer.toFileString());
            }
            FileUtil.writeAllLines(FileUtil.CUSTOMERS_FILE, lines, false);
        } catch (IOException e) {
            System.err.println("Error saving customers: " + e.getMessage());
        }
    }

    /**
     * Load bills from file
     */
    private void loadBills() {
        try {
            ArrayList<String> lines = FileUtil.readFromFile(FileUtil.BILLS_FILE);
            for (String line : lines) {
                Bill bill = Bill.fromFileString(line);
                if (bill != null) {
                    bills.add(bill);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading bills: " + e.getMessage());
        }
    }

    /**
     * Save bill to file
     * 
     * @param bill Bill to save
     */
    private void saveBill(Bill bill) {
        try {
            FileUtil.writeToFile(FileUtil.BILLS_FILE, bill.toFileString(), true);
            bills.add(bill);
        } catch (IOException e) {
            System.err.println("Error saving bill: " + e.getMessage());
        }
    }

    /**
     * Add new customer
     * 
     * @param customer Customer to add
     * @throws InvalidInputException If customer ID already exists
     */
    public void addCustomer(Customer customer) throws InvalidInputException {
        if (customers.containsKey(customer.getCustomerId())) {
            throw new InvalidInputException("Customer with ID " + customer.getCustomerId() + " already exists");
        }
        customers.put(customer.getCustomerId(), customer);
        saveCustomers();
        System.out.println("✓ Customer added successfully!");
    }

    /**
     * Get customer by ID
     * 
     * @param customerId Customer ID
     * @return Customer object
     * @throws InvalidInputException If customer not found
     */
    public Customer getCustomer(String customerId) throws InvalidInputException {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new InvalidInputException("Customer with ID " + customerId + " not found");
        }
        return customer;
    }

    /**
     * View all customers
     */
    public void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }

        System.out.println("\n==================================================================");
        System.out.println("                      CUSTOMER LIST                               ");
        System.out.println("==================================================================");
        System.out.println(String.format("%-10s %-20s %-15s %-25s %-12s",
                "ID", "Name", "Phone", "Email", "Total Spent"));
        System.out.println("------------------------------------------------------------------");

        for (Customer customer : customers.values()) {
            System.out.println(customer.toString());
        }

        System.out.println("==================================================================");
        System.out.println("Total Customers: " + customers.size());
        System.out.println("==================================================================\n");
    }

    /**
     * Search customers by name or phone
     * 
     * @param searchTerm Search term
     * @return ArrayList of matching customers
     */
    public ArrayList<Customer> searchCustomers(String searchTerm) {
        ArrayList<Customer> results = new ArrayList<>();
        String lowerSearchTerm = searchTerm.toLowerCase();

        for (Customer customer : customers.values()) {
            if (customer.getCustomerName().toLowerCase().contains(lowerSearchTerm) ||
                    customer.getPhoneNumber().contains(searchTerm) ||
                    customer.getCustomerId().toLowerCase().contains(lowerSearchTerm)) {
                results.add(customer);
            }
        }

        return results;
    }

    /**
     * Create a new bill
     * 
     * @param customerId  Customer ID
     * @param cashierName Cashier name
     * @return New Bill object
     * @throws InvalidInputException If customer not found
     */
    public Bill createBill(String customerId, String cashierName) throws InvalidInputException {
        Customer customer = getCustomer(customerId);
        String billId = generateNextBillId();
        return new Bill(billId, customerId, customer.getCustomerName(), cashierName);
    }

    /**
     * Add item to bill
     * 
     * @param bill      Bill object
     * @param productId Product ID
     * @param quantity  Quantity
     * @throws ProductNotFoundException   If product not found
     * @throws InsufficientStockException If insufficient stock
     */
    public void addItemToBill(Bill bill, String productId, int quantity)
            throws ProductNotFoundException, InsufficientStockException {

        // Check stock availability
        inventoryService.checkStock(productId, quantity);

        // Get product details
        Product product = inventoryService.getProduct(productId);

        // Add item to bill
        bill.addItem(productId, product.getProductName(), quantity, product.getPrice());

        System.out.println("✓ Item added to bill: " + product.getProductName() + " x " + quantity);
    }

    /**
     * Complete billing transaction
     * 
     * @param bill Bill object
     * @throws ProductNotFoundException   If product not found during stock update
     * @throws InsufficientStockException If stock becomes insufficient
     */
    public void completeBilling(Bill bill)
            throws ProductNotFoundException, InsufficientStockException {

        // Reduce stock for all items
        for (Bill.BillItem item : bill.getItems()) {
            inventoryService.reduceStock(item.getProductId(), item.getQuantity());
        }

        // Update customer purchase history
        try {
            Customer customer = getCustomer(bill.getCustomerId());
            customer.addPurchase(bill.getBillId(), bill.getTotalAmount());
            saveCustomers();
        } catch (InvalidInputException e) {
            System.err.println("Error updating customer: " + e.getMessage());
        }

        // Save bill
        saveBill(bill);

        // Display invoice
        bill.displayInvoice();

        System.out.println("✓ Transaction completed successfully!");
    }

    /**
     * View all bills
     */
    public void viewAllBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills generated yet.");
            return;
        }

        System.out.println("\n==================================================================");
        System.out.println("                      BILLS HISTORY                               ");
        System.out.println("==================================================================");
        System.out.println(String.format("%-10s %-10s %-20s %-20s %-15s",
                "Bill ID", "Cust ID", "Customer Name", "Date", "Amount"));
        System.out.println("------------------------------------------------------------------");

        for (Bill bill : bills) {
            System.out.println(String.format("%-10s %-10s %-20s %-20s Rs.%-10.2f",
                    bill.getBillId(),
                    bill.getCustomerId(),
                    bill.getCustomerName(),
                    bill.getBillDate(),
                    bill.getTotalAmount()));
        }

        System.out.println("==================================================================");
        System.out.println("Total Bills: " + bills.size());
        System.out.println("==================================================================\n");
    }

    /**
     * Search bills by customer ID or bill ID
     * 
     * @param searchTerm Search term
     * @return ArrayList of matching bills
     */
    public ArrayList<Bill> searchBills(String searchTerm) {
        ArrayList<Bill> results = new ArrayList<>();
        String lowerSearchTerm = searchTerm.toLowerCase();

        for (Bill bill : bills) {
            if (bill.getBillId().toLowerCase().contains(lowerSearchTerm) ||
                    bill.getCustomerId().toLowerCase().contains(lowerSearchTerm) ||
                    bill.getCustomerName().toLowerCase().contains(lowerSearchTerm)) {
                results.add(bill);
            }
        }

        return results;
    }

    /**
     * Get bill by ID
     * 
     * @param billId Bill ID
     * @return Bill object
     * @throws InvalidInputException If bill not found
     */
    public Bill getBill(String billId) throws InvalidInputException {
        for (Bill bill : bills) {
            if (bill.getBillId().equals(billId)) {
                return bill;
            }
        }
        throw new InvalidInputException("Bill with ID " + billId + " not found");
    }

    /**
     * Generate sales report
     */
    public void generateSalesReport() {
        if (bills.isEmpty()) {
            System.out.println("No sales data available.");
            return;
        }

        double totalSales = 0.0;
        double totalGST = 0.0;
        int totalItems = 0;

        for (Bill bill : bills) {
            totalSales += bill.getTotalAmount();
            totalGST += bill.getGstAmount();
            totalItems += bill.getItemCount();
        }

        System.out.println("\n==================================================================");
        System.out.println("                      SALES REPORT                                ");
        System.out.println("==================================================================");
        System.out.println("Total Bills Generated    : " + bills.size());
        System.out.println("Total Items Sold         : " + totalItems);
        System.out.println("Total Sales (incl GST)   : Rs. " + String.format("%.2f", totalSales));
        System.out.println("Total GST Collected      : Rs. " + String.format("%.2f", totalGST));
        System.out.println("Total Sales (excl GST)   : Rs. " + String.format("%.2f", (totalSales - totalGST)));
        System.out.println("Average Bill Amount      : Rs. " + String.format("%.2f", (totalSales / bills.size())));
        System.out.println("==================================================================\n");
    }

    /**
     * Generate next bill ID
     * 
     * @return Next available bill ID
     */
    private String generateNextBillId() {
        int maxId = 0;

        for (Bill bill : bills) {
            try {
                int id = Integer.parseInt(bill.getBillId().substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore invalid IDs
            }
        }

        return String.format("B%04d", maxId + 1);
    }

    /**
     * Generate next customer ID
     * 
     * @return Next available customer ID
     */
    public String generateNextCustomerId() {
        int maxId = 0;

        for (String customerId : customers.keySet()) {
            try {
                int id = Integer.parseInt(customerId.substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore invalid IDs
            }
        }

        return String.format("C%03d", maxId + 1);
    }

    /**
     * Check if customer exists
     * 
     * @param customerId Customer ID
     * @return True if customer exists
     */
    public boolean customerExists(String customerId) {
        return customers.containsKey(customerId);
    }
}