/*
Developer Name : Atul Mishra
PRN Number : STT-25128071903
Responsibility: Exception Handling - Authentication Exception
Concepts Used : Custom Exception, Exception Handling, Constructor Overloading
*/

package com.retailpos.exception;

// Custom exception thrown when authentication fails
public class AuthenticationException extends Exception {

    // Default constructor
    public AuthenticationException() {
        super("Authentication failed");
    }

    // Constructor with custom message
    public AuthenticationException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}