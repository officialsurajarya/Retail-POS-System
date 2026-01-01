package com.retailpos.exception;

/*
Developer Name : Vaibhavi Anand
Responsibility : Product Not Found Exception
Concepts Used  : Custom Exception
*/

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
