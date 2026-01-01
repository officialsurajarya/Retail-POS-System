/*
Developer Name : Atul Mishra
PRN Number : STT-25128071903
Responsibility: Validation Utility - Input validation methods
Concepts Used : Static Methods, String Parsing, Wrapper Classes (Integer, Double),
                Exception Handling, Regular Expressions
*/

package com.retailpos.util;

import com.retailpos.exception.InvalidInputException;

/**
 * Utility class for input validation
 */
public class ValidationUtil {

    /**
     * Validate if string is not empty
     * 
     * @param input     Input string
     * @param fieldName Name of the field
     * @throws InvalidInputException If validation fails
     */
    public static void validateNotEmpty(String input, String fieldName) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty");
        }
    }

    /**
     * Validate if string is a valid integer
     * 
     * @param input     Input string
     * @param fieldName Name of the field
     * @return Parsed integer value
     * @throws InvalidInputException If validation fails
     */
    public static int validateInteger(String input, String fieldName) throws InvalidInputException {
        try {
            validateNotEmpty(input, fieldName);
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid integer", e);
        }
    }

    /**
     * Validate if string is a valid positive integer
     * 
     * @param input     Input string
     * @param fieldName Name of the field
     * @return Parsed integer value
     * @throws InvalidInputException If validation fails
     */
    public static int validatePositiveInteger(String input, String fieldName) throws InvalidInputException {
        int value = validateInteger(input, fieldName);
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be positive");
        }
        return value;
    }

    /**
     * Validate if string is a valid double
     * 
     * @param input     Input string
     * @param fieldName Name of the field
     * @return Parsed double value
     * @throws InvalidInputException If validation fails
     */
    public static double validateDouble(String input, String fieldName) throws InvalidInputException {
        try {
            validateNotEmpty(input, fieldName);
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid number", e);
        }
    }

    /**
     * Validate if string is a valid positive double
     * 
     * @param input     Input string
     * @param fieldName Name of the field
     * @return Parsed double value
     * @throws InvalidInputException If validation fails
     */
    public static double validatePositiveDouble(String input, String fieldName) throws InvalidInputException {
        double value = validateDouble(input, fieldName);
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be positive");
        }
        return value;
    }

    /**
     * Validate phone number (10 digits)
     * 
     * @param phone Phone number
     * @throws InvalidInputException If validation fails
     */
    public static void validatePhoneNumber(String phone) throws InvalidInputException {
        validateNotEmpty(phone, "Phone number");
        if (!phone.matches("\\d{10}")) {
            throw new InvalidInputException("Phone number must be 10 digits");
        }
    }

    /**
     * Validate email format
     * 
     * @param email Email address
     * @throws InvalidInputException If validation fails
     */
    public static void validateEmail(String email) throws InvalidInputException {
        validateNotEmpty(email, "Email");
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidInputException("Invalid email format");
        }
    }

    /**
     * Validate product ID format
     * 
     * @param productId Product ID
     * @throws InvalidInputException If validation fails
     */
    public static void validateProductId(String productId) throws InvalidInputException {
        validateNotEmpty(productId, "Product ID");
        if (!productId.matches("^P\\d{3}$")) {
            throw new InvalidInputException("Product ID must be in format P001, P002, etc.");
        }
    }

    /**
     * Validate customer ID format
     * 
     * @param customerId Customer ID
     * @throws InvalidInputException If validation fails
     */
    public static void validateCustomerId(String customerId) throws InvalidInputException {
        validateNotEmpty(customerId, "Customer ID");
        if (!customerId.matches("^C\\d{3}$")) {
            throw new InvalidInputException("Customer ID must be in format C001, C002, etc.");
        }
    }

    /**
     * Validate choice from menu
     * 
     * @param choice User choice
     * @param min    Minimum valid choice
     * @param max    Maximum valid choice
     * @return Validated choice
     * @throws InvalidInputException If validation fails
     */
    public static int validateChoice(String choice, int min, int max) throws InvalidInputException {
        int value = validateInteger(choice, "Choice");
        if (value < min || value > max) {
            throw new InvalidInputException("Choice must be between " + min + " and " + max);
        }
        return value;
    }
}