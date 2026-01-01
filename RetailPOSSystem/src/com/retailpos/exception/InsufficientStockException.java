/*
Developer Name : Atul Mishra
PRN Number : STT-25128071903
Responsibility: Exception Handling - Insufficient Stock Exception
Concepts Used : Custom Exception, Exception Handling, Constructor Overloading
*/

package com.retailpos.exception;

/**
 * Custom exception thrown when there is insufficient stock for a product
 */
public class InsufficientStockException extends Exception {

    private int availableStock;
    private int requestedStock;

    /**
     * Default constructor
     */
    public InsufficientStockException() {
        super("Insufficient stock available");
    }

    /**
     * Constructor with custom message
     * 
     * @param message Custom error message
     */
    public InsufficientStockException(String message) {
        super(message);
    }

    /**
     * Constructor with stock details
     * 
     * @param message        Custom error message
     * @param availableStock Available stock quantity
     * @param requestedStock Requested stock quantity
     */
    public InsufficientStockException(String message, int availableStock, int requestedStock) {
        super(message);
        this.availableStock = availableStock;
        this.requestedStock = requestedStock;
    }

    /**
     * Get available stock
     * 
     * @return Available stock quantity
     */
    public int getAvailableStock() {
        return availableStock;
    }

    /**
     * Get requested stock
     * 
     * @return Requested stock quantity
     */
    public int getRequestedStock() {
        return requestedStock;
    }
}