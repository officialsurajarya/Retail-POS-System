package com.retailpos.exception;

/*
Developer Name : Suraj Arya
Responsibility : Custom Exception for Login
Concepts Used  : Custom Exception, Inheritance
*/

public class AuthenticationException extends Exception {

    public AuthenticationException(String message) {
        super(message);
    }
}
