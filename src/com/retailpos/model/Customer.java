package com.retailpos.model;

/*
Developer Name : Aryan Pandey
Responsibility : Customer Model
Concepts Used  : Encapsulation, String Handling
*/

public class Customer {

    private Integer customerId;
    private String customerName;
    private String mobile;

    public Customer(Integer customerId, String customerName, String mobile) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.mobile = mobile;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return customerId + "," + customerName + "," + mobile;
    }
}
