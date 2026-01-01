package com.retailpos.service;

import com.retailpos.exception.InsufficientStockException;
import com.retailpos.exception.ProductNotFoundException;
import com.retailpos.model.Bill;
import com.retailpos.model.Product;

/*
Developer Name : Vaibhavi Anand
Responsibility : Billing Logic
Concepts Used  : OOP, Exception Handling, Business Logic
*/

public class BillingService {

    private InventoryService inventoryService;

    public BillingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void addProductToBill(Bill bill, Integer productId, Integer qty)
            throws ProductNotFoundException, InsufficientStockException {

        Product product = inventoryService.getProduct(productId);

        inventoryService.reduceStock(productId, qty);

        Product billProduct = new Product(
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                qty);

        bill.addProduct(billProduct);
    }
}
