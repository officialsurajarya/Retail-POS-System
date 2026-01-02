/*
Developer Name : Atul Mishra
PRN Number : STT-25128071903
Responsibility: Exception Handling - Product Not Found Exception
Concepts Used : Custom Exception, Exception Handling, Constructor Overloading
*/

package com.retailpos.exception;

// Custom exception thrown when a product is not found in the inventory
public class ProductNotFoundException extends Exception {

    // Default constructor
    public ProductNotFoundException() {
        super("Product not found in inventory");
    }

    // Constructor with custom message
    public ProductNotFoundException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}