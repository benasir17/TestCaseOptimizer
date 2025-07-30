package com.testoptimizer;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderUtil {
    public static List<TestCase> readTestCases(String filePath) {
        List<TestCase> testCases = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header row
            while ((line = reader.readNext()) != null) {
                TestCase tc = new TestCase(line[0], line[1], line[2], line[3]);
                testCases.add(tc);
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return testCases;
    }
}
