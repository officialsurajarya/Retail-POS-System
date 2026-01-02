/*
Developer Name : Aryan Pandey
PRN Number : STT-25128071902
Responsibility: Model Class - Bill Entity
Concepts Used : Encapsulation, Inner Class, ArrayList, Constructor Overloading,
                Getters/Setters, toString(), Date/Time, Object-Oriented Design
*/

package com.retailpos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Bill model class representing a billing transaction 
public class Bill {

    // Private fields - Encapsulation
    private String billId;
    private String customerId;
    private String customerName;
    private ArrayList<BillItem> items;
    private double subtotal;
    private double gstAmount;
    private double totalAmount;
    private String billDate;
    private String cashierName;

    // GST percentage
    private static final double GST_RATE = 0.18; // 18% GST

    // Inner class representing an item in the bill
    public static class BillItem {
        private String productId;
        private String productName;
        private int quantity;
        private double unitPrice;
        private double itemTotal;

        // Constructor for BillItem
        public BillItem(String productId, String productName, int quantity, double unitPrice) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.itemTotal = quantity * unitPrice;
        }

        // Getters
        public String getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public double getItemTotal() {
            return itemTotal;
        }

        @Override
        public String toString() {
            return String.format("%-10s %-20s %5d %10.2f %12.2f",
                    productId, productName, quantity, unitPrice, itemTotal);
        }

        // Convert to file string format
        public String toFileString() {
            return productId + ":" + productName + ":" + quantity + ":" + unitPrice;
        }

        // Create BillItem from file string
        public static BillItem fromFileString(String str) {
            String[] parts = str.split(":");
            if (parts.length >= 4) {
                return new BillItem(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Double.parseDouble(parts[3]));
            }
            return null;
        }
    }

    // Default constructor
    public Bill() {
        this.items = new ArrayList<>();
        this.subtotal = 0.0;
        this.gstAmount = 0.0;
        this.totalAmount = 0.0;
        this.billDate = getCurrentDateTime();
    }

    // Parameterized constructor
    public Bill(String billId, String customerId, String customerName, String cashierName) {
        this.billId = billId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.cashierName = cashierName;
        this.items = new ArrayList<>();
        this.subtotal = 0.0;
        this.gstAmount = 0.0;
        this.totalAmount = 0.0;
        this.billDate = getCurrentDateTime();
    }

    // Getters and Setters
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

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

    public ArrayList<BillItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getGstAmount() {
        return gstAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    // Add item to bill
    public void addItem(String productId, String productName, int quantity, double unitPrice) {
        BillItem item = new BillItem(productId, productName, quantity, unitPrice);
        items.add(item);
        calculateTotals();
    }

    // Add BillItem object to bill
    public void addItem(BillItem item) {
        items.add(item);
        calculateTotals();
    }

    // Calculate subtotal, GST and total
    private void calculateTotals() {
        subtotal = 0.0;
        for (BillItem item : items) {
            subtotal += item.getItemTotal();
        }
        gstAmount = subtotal * GST_RATE;
        totalAmount = subtotal + gstAmount;
    }

    // Get current date and time
    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(formatter);
    }

    // Get number of items in bill
    public int getItemCount() {
        return items.size();
    }

    // Convert Bill to file format string
    // Format:
    // billId|customerId|customerName|cashierName|billDate|subtotal|gstAmount|totalAmount|items

    public String toFileString() {
        StringBuilder itemsStr = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            itemsStr.append(items.get(i).toFileString());
            if (i < items.size() - 1) {
                itemsStr.append(";");
            }
        }

        return billId + "|" + customerId + "|" + customerName + "|" + cashierName + "|" +
                billDate + "|" + subtotal + "|" + gstAmount + "|" + totalAmount + "|" + itemsStr.toString();
    }

    // Create Bill from file format string
    public static Bill fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length >= 8) {
            Bill bill = new Bill();
            bill.setBillId(parts[0]);
            bill.setCustomerId(parts[1]);
            bill.setCustomerName(parts[2]);
            bill.setCashierName(parts[3]);
            bill.setBillDate(parts[4]);
            bill.subtotal = Double.parseDouble(parts[5]);
            bill.gstAmount = Double.parseDouble(parts[6]);
            bill.totalAmount = Double.parseDouble(parts[7]);

            // Parse items if present
            if (parts.length > 8 && !parts[8].isEmpty()) {
                String[] itemsStr = parts[8].split(";");
                for (String itemStr : itemsStr) {
                    BillItem item = BillItem.fromFileString(itemStr);
                    if (item != null) {
                        bill.items.add(item);
                    }
                }
            }

            return bill;
        }
        return null;
    }

    // Display bill invoice
    public void displayInvoice() {
        System.out.println("\n");
        System.out.println("==================================================================");
        System.out.println("                        RETAIL POS SYSTEM                         ");
        System.out.println("                          TAX INVOICE                             ");
        System.out.println("==================================================================");
        System.out.println("Bill ID          : " + billId);
        System.out.println("Date & Time      : " + billDate);
        System.out.println("Customer ID      : " + customerId);
        System.out.println("Customer Name    : " + customerName);
        System.out.println("Cashier          : " + cashierName);
        System.out.println("==================================================================");
        System.out.println(String.format("%-10s %-20s %5s %10s %12s",
                "Product", "Name", "Qty", "Price", "Total"));
        System.out.println("------------------------------------------------------------------");

        for (BillItem item : items) {
            System.out.println(item.toString());
        }

        System.out.println("==================================================================");
        System.out.println(String.format("%48s : Rs. %10.2f", "Subtotal", subtotal));
        System.out.println(String.format("%48s : Rs. %10.2f", "GST (18%)", gstAmount));
        System.out.println("------------------------------------------------------------------");
        System.out.println(String.format("%48s : Rs. %10.2f", "TOTAL AMOUNT", totalAmount));
        System.out.println("==================================================================");
        System.out.println("                  Thank you for shopping with us!                 ");
        System.out.println("==================================================================\n");
    }
}