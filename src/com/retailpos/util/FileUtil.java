package com.retailpos.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Developer Name : Atul Mishra
Responsibility : File Handling Utility
Concepts Used  : FileReader, FileWriter, BufferedReader, BufferedWriter
*/

public class FileUtil {

    // Write data to file
    public static void writeToFile(String fileName, String data, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }
    }

    // Read data from file
    public static List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }
        return lines;
    }
}
