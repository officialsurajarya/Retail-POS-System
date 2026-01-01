/*
Developer Name : Atul Mishra
PRN Number : STT-25128071903
Responsibility: File Handling Utility - Read/Write operations for .txt files
Concepts Used : File Handling, BufferedReader, BufferedWriter, FileReader, FileWriter,
                ArrayList, Exception Handling, Static Methods, Try-Catch-Finally
*/

package com.retailpos.util;

import java.io.*;
import java.util.ArrayList;

/**
 * Utility class for file operations
 * Handles reading and writing data to text files
 */
public class FileUtil {

    // File paths
    public static final String PRODUCTS_FILE = "data/products.txt";
    public static final String CUSTOMERS_FILE = "data/customers.txt";
    public static final String BILLS_FILE = "data/bills.txt";
    public static final String USERS_FILE = "data/users.txt";

    /**
     * Write a single line to a file
     * 
     * @param filePath Path to the file
     * @param data     Data to write
     * @param append   True to append, false to overwrite
     * @throws IOException If file operation fails
     */
    public static void writeToFile(String filePath, String data, boolean append) throws IOException {
        BufferedWriter writer = null;
        try {
            // Create directory if it doesn't exist
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            writer = new BufferedWriter(new FileWriter(filePath, append));
            writer.write(data);
            writer.newLine();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error closing writer: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Read all lines from a file
     * 
     * @param filePath Path to the file
     * @return ArrayList containing all lines
     * @throws IOException If file operation fails
     */
    public static ArrayList<String> readFromFile(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = null;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return lines; // Return empty list if file doesn't exist
            }

            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing reader: " + e.getMessage());
                }
            }
        }

        return lines;
    }

    /**
     * Write multiple lines to a file
     * 
     * @param filePath Path to the file
     * @param lines    ArrayList of lines to write
     * @param append   True to append, false to overwrite
     * @throws IOException If file operation fails
     */
    public static void writeAllLines(String filePath, ArrayList<String> lines, boolean append) throws IOException {
        BufferedWriter writer = null;
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            writer = new BufferedWriter(new FileWriter(filePath, append));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error closing writer: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Delete a line from file
     * 
     * @param filePath     Path to the file
     * @param lineToDelete Line to delete
     * @return True if deleted successfully
     * @throws IOException If file operation fails
     */
    public static boolean deleteLine(String filePath, String lineToDelete) throws IOException {
        ArrayList<String> lines = readFromFile(filePath);
        boolean removed = lines.remove(lineToDelete);

        if (removed) {
            writeAllLines(filePath, lines, false);
        }

        return removed;
    }

    /**
     * Update a line in file
     * 
     * @param filePath Path to the file
     * @param oldLine  Old line to replace
     * @param newLine  New line to insert
     * @return True if updated successfully
     * @throws IOException If file operation fails
     */
    public static boolean updateLine(String filePath, String oldLine, String newLine) throws IOException {
        ArrayList<String> lines = readFromFile(filePath);
        int index = lines.indexOf(oldLine);

        if (index != -1) {
            lines.set(index, newLine);
            writeAllLines(filePath, lines, false);
            return true;
        }

        return false;
    }

    /**
     * Check if file exists
     * 
     * @param filePath Path to the file
     * @return True if file exists
     */
    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * Create file if it doesn't exist
     * 
     * @param filePath Path to the file
     * @throws IOException If file operation fails
     */
    public static void createFileIfNotExists(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Initialize all required files
     */
    public static void initializeFiles() {
        try {
            createFileIfNotExists(PRODUCTS_FILE);
            createFileIfNotExists(CUSTOMERS_FILE);
            createFileIfNotExists(BILLS_FILE);
            createFileIfNotExists(USERS_FILE);

            // Create default admin user if users.txt is empty
            ArrayList<String> users = readFromFile(USERS_FILE);
            if (users.isEmpty()) {
                writeToFile(USERS_FILE, "admin|admin123|ADMIN", false);
                writeToFile(USERS_FILE, "cashier|cash123|CASHIER", true);
            }
        } catch (IOException e) {
            System.err.println("Error initializing files: " + e.getMessage());
        }
    }
}