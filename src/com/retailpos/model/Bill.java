package com.retailpos.model;

import java.util.ArrayList;
import java.util.List;

/*
Developer Name : Aryan Pandey
Responsibility : Billing Model
Concepts Used  : Collections, OOP, Aggregation
*/

public class Bill {

    private Integer billId;
    private Customer customer;
    private List<Product> productList;
    private Double totalAmount;

    public Bill(Integer billId, Customer customer) {
        this.billId = billId;
        this.customer = customer;
        this.productList = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addProduct(Product product) {
        productList.add(product);
        totalAmount += product.getPrice() * product.getQuantity();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double calculateGST() {
        return totalAmount * 0.18;
    }

    public Double getGrandTotal() {
        return totalAmount + calculateGST();
    }

    @Override
    public String toString() {
        return "Bill ID: " + billId +
                "\nCustomer: " + customer.getCustomerName() +
                "\nTotal: " + totalAmount +
                "\nGST: " + calculateGST() +
                "\nGrand Total: " + getGrandTotal();
    }
}
