/*
Developer Name : Atul Mishra
PRN Number : STT-25128071903
Responsibility: Exception Handling - Insufficient Stock Exception
Concepts Used : Custom Exception, Exception Handling, Constructor Overloading
*/

package com.retailpos.exception;

// Custom exception thrown when there is insufficient stock for a product
public class InsufficientStockException extends Exception {

    private int availableStock;
    private int requestedStock;

    // Default constructor
    public InsufficientStockException() {
        super("Insufficient stock available");
    }

    // Constructor with custom message
    public InsufficientStockException(String message) {
        super(message);
    }

    // Constructor with stock details
    public InsufficientStockException(String message, int availableStock, int requestedStock) {
        super(message);
        this.availableStock = availableStock;
        this.requestedStock = requestedStock;
    }

    // Get available stock
    public int getAvailableStock() {
        return availableStock;
    }

    // Get requested stock
    public int getRequestedStock() {
        return requestedStock;
    }
}