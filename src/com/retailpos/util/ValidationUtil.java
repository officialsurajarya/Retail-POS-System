package com.retailpos.util;

import com.retailpos.exception.InvalidInputException;

/*
Developer Name : Atul Mishra
Responsibility : Input Validation
Concepts Used  : Exception Handling, Wrapper Classes
*/

public class ValidationUtil {

    public static int validateInt(String input) throws InvalidInputException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format!");
        }
    }

    public static double validateDouble(String input) throws InvalidInputException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid decimal format!");
        }
    }
}
