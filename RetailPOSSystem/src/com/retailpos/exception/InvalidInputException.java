/*
Developer Name : Atul Mishra
PRN Number : STT-25128071903
Responsibility: Exception Handling - Invalid Input Exception
Concepts Used : Custom Exception, Exception Handling, Constructor Overloading
*/

package com.retailpos.exception;

// Custom exception thrown when user provides invalid input
public class InvalidInputException extends Exception {

    // Default constructor
    public InvalidInputException() {
        super("Invalid input provided");
    }

    // Constructor with custom message
    public InvalidInputException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}