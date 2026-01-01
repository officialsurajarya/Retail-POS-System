package com.retailpos.model;

/*
Developer Name : Aryan Pandey
Responsibility : Product Model
Concepts Used  : OOP, Encapsulation, Wrapper Class
*/

public class Product {

    private Integer productId;
    private String productName;
    private Double price;
    private Integer quantity;

    public Product(Integer productId, String productName, Double price, Integer quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return productId + "," + productName + "," + price + "," + quantity;
    }
}
