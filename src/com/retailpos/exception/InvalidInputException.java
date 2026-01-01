package com.retailpos.exception;

/*
Developer Name : Atul Mishra
Responsibility : Validation Exception
Concepts Used  : Custom Exception, Inheritance
*/

public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
