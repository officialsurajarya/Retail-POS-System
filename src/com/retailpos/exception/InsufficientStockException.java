package com.retailpos.exception;

/*
Developer Name : Vaibhavi Anand
Responsibility : Stock Exception Handling
Concepts Used  : Custom Exception
*/

public class InsufficientStockException extends Exception {

    public InsufficientStockException(String message) {
        super(message);
    }
}
