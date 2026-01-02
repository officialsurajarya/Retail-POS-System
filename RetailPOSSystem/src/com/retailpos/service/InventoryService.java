/*
Developer Name : Vaibhavi Anand
PRN Number : STT-25128071922
Responsibility: Business Logic - Inventory Management Service
Concepts Used : ArrayList, HashMap, File Handling, Exception Handling, Collections,
                Business Logic Implementation, CRUD Operations
*/

package com.retailpos.service;

import com.retailpos.model.Product;
import com.retailpos.exception.ProductNotFoundException;
import com.retailpos.exception.InsufficientStockException;
import com.retailpos.exception.InvalidInputException;
import com.retailpos.util.FileUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Service class for inventory management
 * Handles all product-related business logic
 */
public class InventoryService {

    private HashMap<String, Product> inventory;

    /**
     * Constructor - loads inventory from file
     */
    public InventoryService() {
        this.inventory = new HashMap<>();
        loadInventory();
    }

    /**
     * Load inventory from file
     */
    private void loadInventory() {
        try {
            ArrayList<String> lines = FileUtil.readFromFile(FileUtil.PRODUCTS_FILE);
            for (String line : lines) {
                Product product = Product.fromFileString(line);
                if (product != null) {
                    inventory.put(product.getProductId(), product);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }

    /**
     * Save inventory to file
     */
    private void saveInventory() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Product product : inventory.values()) {
                lines.add(product.toFileString());
            }
            FileUtil.writeAllLines(FileUtil.PRODUCTS_FILE, lines, false);
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Add new product to inventory
     * 
     * @param product Product to add
     * @throws InvalidInputException If product ID already exists
     */
    public void addProduct(Product product) throws InvalidInputException {
        if (inventory.containsKey(product.getProductId())) {
            throw new InvalidInputException("Product with ID " + product.getProductId() + " already exists");
        }
        inventory.put(product.getProductId(), product);
        saveInventory();
        System.out.println("✓ Product added successfully!");
    }

    /**
     * Get product by ID
     * 
     * @param productId Product ID
     * @return Product object
     * @throws ProductNotFoundException If product not found
     */
    public Product getProduct(String productId) throws ProductNotFoundException {
        Product product = inventory.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
        return product;
    }

    /**
     * Update existing product
     * 
     * @param productId      Product ID to update
     * @param updatedProduct Updated product details
     * @throws ProductNotFoundException If product not found
     */
    public void updateProduct(String productId, Product updatedProduct) throws ProductNotFoundException {
        if (!inventory.containsKey(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
        inventory.put(productId, updatedProduct);
        saveInventory();
        System.out.println("✓ Product updated successfully!");
    }

    /**
     * Delete product from inventory
     * 
     * @param productId Product ID to delete
     * @throws ProductNotFoundException If product not found
     */
    public void deleteProduct(String productId) throws ProductNotFoundException {
        if (!inventory.containsKey(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
        inventory.remove(productId);
        saveInventory();
        System.out.println("✓ Product deleted successfully!");
    }

    /**
     * View all products
     */
    public void viewAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }

        System.out.println("\n==================================================================");
        System.out.println("                      INVENTORY LIST                              ");
        System.out.println("==================================================================");
        System.out.println(String.format("%-10s %-20s %-15s %-12s %-10s",
                "ID", "Product Name", "Category", "Price", "Stock"));
        System.out.println("------------------------------------------------------------------");

        for (Product product : inventory.values()) {
            System.out.println(product.toString());
        }

        System.out.println("==================================================================");
        System.out.println("Total Products: " + inventory.size());
        System.out.println("==================================================================\n");
    }

    /**
     * Search products by name
     * 
     * @param searchTerm Search term
     * @return ArrayList of matching products
     */
    public ArrayList<Product> searchProducts(String searchTerm) {
        ArrayList<Product> results = new ArrayList<>();
        String lowerSearchTerm = searchTerm.toLowerCase();

        for (Product product : inventory.values()) {
            if (product.getProductName().toLowerCase().contains(lowerSearchTerm) ||
                    product.getCategory().toLowerCase().contains(lowerSearchTerm) ||
                    product.getProductId().toLowerCase().contains(lowerSearchTerm)) {
                results.add(product);
            }
        }

        return results;
    }

    /**
     * Check stock availability
     * 
     * @param productId Product ID
     * @param quantity  Required quantity
     * @return True if stock is available
     * @throws ProductNotFoundException   If product not found
     * @throws InsufficientStockException If stock is insufficient
     */
    public boolean checkStock(String productId, int quantity)
            throws ProductNotFoundException, InsufficientStockException {
        Product product = getProduct(productId);

        if (!product.hasStock(quantity)) {
            throw new InsufficientStockException(
                    "Insufficient stock for " + product.getProductName(),
                    product.getStockQuantity(),
                    quantity);
        }

        return true;
    }

    /**
     * Reduce stock after billing
     * 
     * @param productId Product ID
     * @param quantity  Quantity to reduce
     * @throws ProductNotFoundException   If product not found
     * @throws InsufficientStockException If stock is insufficient
     */
    public void reduceStock(String productId, int quantity)
            throws ProductNotFoundException, InsufficientStockException {
        Product product = getProduct(productId);

        if (!product.hasStock(quantity)) {
            throw new InsufficientStockException(
                    "Cannot reduce stock. Only " + product.getStockQuantity() + " units available",
                    product.getStockQuantity(),
                    quantity);
        }

        product.reduceStock(quantity);
        saveInventory();
    }

    /**
     * Add stock to product
     * 
     * @param productId Product ID
     * @param quantity  Quantity to add
     * @throws ProductNotFoundException If product not found
     */
    public void addStock(String productId, int quantity) throws ProductNotFoundException {
        Product product = getProduct(productId);
        product.addStock(quantity);
        saveInventory();
        System.out.println("✓ Stock updated successfully!");
    }

    /**
     * Get low stock products (stock < 10)
     * 
     * @return ArrayList of low stock products
     */
    public ArrayList<Product> getLowStockProducts() {
        ArrayList<Product> lowStockProducts = new ArrayList<>();

        for (Product product : inventory.values()) {
            if (product.getStockQuantity() < 10) {
                lowStockProducts.add(product);
            }
        }

        return lowStockProducts;
    }

    /**
     * Display low stock alert
     */
    public void displayLowStockAlert() {
        ArrayList<Product> lowStockProducts = getLowStockProducts();

        if (lowStockProducts.isEmpty()) {
            System.out.println("✓ All products have sufficient stock.");
            return;
        }

        System.out.println("\n⚠ LOW STOCK ALERT ⚠");
        System.out.println("==================================================================");
        System.out.println(String.format("%-10s %-20s %-15s %-10s",
                "ID", "Product Name", "Category", "Stock"));
        System.out.println("------------------------------------------------------------------");

        for (Product product : lowStockProducts) {
            System.out.println(String.format("%-10s %-20s %-15s %-10d",
                    product.getProductId(),
                    product.getProductName(),
                    product.getCategory(),
                    product.getStockQuantity()));
        }

        System.out.println("==================================================================\n");
    }

    /**
     * Get total inventory value
     * 
     * @return Total value of inventory
     */
    public double getTotalInventoryValue() {
        double totalValue = 0.0;

        for (Product product : inventory.values()) {
            totalValue += (product.getPrice() * product.getStockQuantity());
        }

        return totalValue;
    }

    /**
     * Get product count
     * 
     * @return Number of products in inventory
     */
    public int getProductCount() {
        return inventory.size();
    }

    /**
     * Check if product exists
     * 
     * @param productId Product ID
     * @return True if product exists
     */
    public boolean productExists(String productId) {
        return inventory.containsKey(productId);
    }

    /**
     * Generate next product ID
     * 
     * @return Next available product ID
     */
    public String generateNextProductId() {
        int maxId = 0;

        for (String productId : inventory.keySet()) {
            try {
                int id = Integer.parseInt(productId.substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore invalid IDs
            }
        }

        return String.format("P%03d", maxId + 1);
    }
}