package com.retailpos.service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.retailpos.model.Product;
import com.retailpos.util.FileUtil;

/*
Developer Name: Vaibhavi Anand
Responsibility: Inventory Management
Concepts Used: Collections, File Handling, Exception Handling
*/

public class InventoryService {

    private Map<Integer, Product> inventory = new HashMap<>();

    // Constructor to load products from file at startup
    public InventoryService() {
        loadProductsFromFile();
    }

    // Load products from the file
    private void loadProductsFromFile() {
        List<String> data = FileUtil.readFromFile("products.txt");
        for (String line : data) {
            String[] productDetails = line.split(",");
            int id = Integer.parseInt(productDetails[0]);
            String name = productDetails[1];
            double price = Double.parseDouble(productDetails[2]);
            int quantity = Integer.parseInt(productDetails[3]);

            Product product = new Product(id, name, price, quantity);
            inventory.put(id, product);
        }
    }

    // Add a product to the inventory and the file
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        saveProductsToFile();  // Update the file after adding a new product
    }

    // Remove a product from the inventory and the file
    public void removeProduct(int productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
            saveProductsToFile();  // Update the file after removing a product
        } else {
            System.out.println("Product ID not found.");
        }
    }

    // Save the current inventory to the file
    private void saveProductsToFile() {
        StringBuilder sb = new StringBuilder();
        for (Product product : inventory.values()) {
            sb.append(product.getProductId()).append(",")
              .append(product.getProductName()).append(",")
              .append(product.getPrice()).append(",")
              .append(product.getQuantity()).append("\n");
        }
        // Write the data to the file (overwriting the file)
        FileUtil.writeToFile("products.txt", sb.toString(), false);
    }

    // View all products in the inventory
    public void viewProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product product : inventory.values()) {
            System.out.println(product.getProductId() + " | " +
                               product.getProductName() + " | " +
                               product.getPrice() + " | Qty: " + 
                               product.getQuantity());
        }
    }

    // Get a product by its ID
    public Product getProduct(int productId) {
        return inventory.get(productId);
    }
}
