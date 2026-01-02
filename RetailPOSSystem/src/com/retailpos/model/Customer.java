/*
Developer Name : Aryan Pandey
PRN Number : STT-25128071902
Responsibility: Model Class - Customer Entity
Concepts Used : Encapsulation, Constructor Overloading, Getters/Setters, toString(),
                ArrayList, Object-Oriented Design
*/

package com.retailpos.model;

import java.util.ArrayList;

// Customer model class representing a customer in the system
public class Customer {

    // Private fields - Encapsulation
    private String customerId;
    private String customerName;
    private String phoneNumber;
    private String email;
    private ArrayList<String> purchaseHistory;
    private double totalPurchaseAmount;

    // Default constructor
    public Customer() {
        this.purchaseHistory = new ArrayList<>();
        this.totalPurchaseAmount = 0.0;
    }

    // Parameterized constructor
    public Customer(String customerId, String customerName, String phoneNumber, String email) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.purchaseHistory = new ArrayList<>();
        this.totalPurchaseAmount = 0.0;
    }

    // Full constructor with purchase history
    public Customer(String customerId, String customerName, String phoneNumber,
            String email, double totalPurchaseAmount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.purchaseHistory = new ArrayList<>();
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(ArrayList<String> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public double getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(double totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    // Add a purchase to history
    public void addPurchase(String billId, double amount) {
        this.purchaseHistory.add(billId);
        this.totalPurchaseAmount += amount;
    }

    // Get number of purchases
    public int getPurchaseCount() {
        return purchaseHistory.size();
    }

    // Convert Customer object to file format string
    // Format: customerId|customerName|phoneNumber|email|totalPurchaseAmount
    public String toFileString() {
        return customerId + "|" + customerName + "|" + phoneNumber + "|" +
                email + "|" + totalPurchaseAmount;
    }

    // Create Customer object from file format string
    public static Customer fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length >= 4) {
            Customer customer = new Customer();
            customer.setCustomerId(parts[0]);
            customer.setCustomerName(parts[1]);
            customer.setPhoneNumber(parts[2]);
            customer.setEmail(parts[3]);
            customer.setTotalPurchaseAmount(parts.length > 4 ? Double.parseDouble(parts[4]) : 0.0);
            return customer;
        }
        return null;
    }

    // Display customer details
    public void displayCustomer() {
        System.out.println("==================================================");
        System.out.println("Customer ID      : " + customerId);
        System.out.println("Customer Name    : " + customerName);
        System.out.println("Phone Number     : " + phoneNumber);
        System.out.println("Email            : " + email);
        System.out.println("Total Purchases  : Rs. " + String.format("%.2f", totalPurchaseAmount));
        System.out.println("Purchase Count   : " + getPurchaseCount());
        System.out.println("==================================================");
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-15s %-25s Rs.%-10.2f",
                customerId, customerName, phoneNumber, email, totalPurchaseAmount);
    }
}