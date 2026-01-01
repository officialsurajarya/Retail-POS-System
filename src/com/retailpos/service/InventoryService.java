package com.retailpos.service;

import java.util.HashMap;
import java.util.Map;

import com.retailpos.exception.InsufficientStockException;
import com.retailpos.exception.ProductNotFoundException;
import com.retailpos.model.Product;

/*
Developer Name : Vaibhavi Anand
Responsibility : Inventory Management
Concepts Used  : Collections, Exception Handling, OOP
*/

public class InventoryService {

    private Map<Integer, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void viewProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product p : inventory.values()) {
            System.out.println(
                    p.getProductId() + " | " +
                            p.getProductName() + " | " +
                            p.getPrice() + " | Qty: " +
                            p.getQuantity());
        }
    }

    public Product getProduct(Integer productId)
            throws ProductNotFoundException {

        if (!inventory.containsKey(productId)) {
            throw new ProductNotFoundException("Product ID not found!");
        }
        return inventory.get(productId);
    }

    public void reduceStock(Integer productId, Integer qty)
            throws InsufficientStockException, ProductNotFoundException {

        Product product = getProduct(productId);

        if (product.getQuantity() < qty) {
            throw new InsufficientStockException("Insufficient stock available!");
        }
        product.setQuantity(product.getQuantity() - qty);
    }
}
