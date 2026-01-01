/*
Developer Name : Aryan Pandey
PRN Number : STT-25128071902
Responsibility: Model Class - Product Entity
Concepts Used : Encapsulation, Constructor Overloading, Getters/Setters, toString(),
                Object-Oriented Design, Data Modeling
*/

package com.retailpos.model;

/**
 * Product model class representing a product in the inventory
 * Demonstrates Encapsulation and OOP principles
 */
public class Product {

    // Private fields - Encapsulation
    private String productId;
    private String productName;
    private String category;
    private double price;
    private int stockQuantity;
    private String description;

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * Parameterized constructor
     * 
     * @param productId     Unique product identifier
     * @param productName   Name of the product
     * @param category      Product category
     * @param price         Product price
     * @param stockQuantity Available stock
     */
    public Product(String productId, String productName, String category, double price, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = "";
    }

    /**
     * Full constructor with description
     */
    public Product(String productId, String productName, String category, double price,
            int stockQuantity, String description) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }

    // Getters and Setters

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Reduce stock by specified quantity
     * 
     * @param quantity Quantity to reduce
     */
    public void reduceStock(int quantity) {
        this.stockQuantity -= quantity;
    }

    /**
     * Add stock by specified quantity
     * 
     * @param quantity Quantity to add
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * Check if product is in stock
     * 
     * @return True if stock is available
     */
    public boolean isInStock() {
        return stockQuantity > 0;
    }

    /**
     * Check if requested quantity is available
     * 
     * @param requestedQty Requested quantity
     * @return True if available
     */
    public boolean hasStock(int requestedQty) {
        return stockQuantity >= requestedQty;
    }

    /**
     * Convert Product object to file format string
     * Format: productId|productName|category|price|stockQuantity|description
     * 
     * @return Formatted string for file storage
     */
    public String toFileString() {
        return productId + "|" + productName + "|" + category + "|" +
                price + "|" + stockQuantity + "|" + description;
    }

    /**
     * Create Product object from file format string
     * 
     * @param line Line from file
     * @return Product object
     */
    public static Product fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length >= 5) {
            Product product = new Product();
            product.setProductId(parts[0]);
            product.setProductName(parts[1]);
            product.setCategory(parts[2]);
            product.setPrice(Double.parseDouble(parts[3]));
            product.setStockQuantity(Integer.parseInt(parts[4]));
            product.setDescription(parts.length > 5 ? parts[5] : "");
            return product;
        }
        return null;
    }

    /**
     * Display product details
     */
    public void displayProduct() {
        System.out.println("==================================================");
        System.out.println("Product ID       : " + productId);
        System.out.println("Product Name     : " + productName);
        System.out.println("Category         : " + category);
        System.out.println("Price            : Rs. " + String.format("%.2f", price));
        System.out.println("Stock Quantity   : " + stockQuantity);
        System.out.println("Description      : " + description);
        System.out.println("==================================================");
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-15s Rs.%-10.2f %-10d",
                productId, productName, category, price, stockQuantity);
    }
}