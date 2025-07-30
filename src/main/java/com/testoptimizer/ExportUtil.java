package com.testoptimizer;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ExportUtil {

    public static void writeSimilarTestCases(List<String[]> similarPairs, String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("ID1,Steps1,ID2,Steps2,LevenshteinDistance");

            for (String[] pair : similarPairs) {
                pw.println(String.join(",", pair));
            }

            System.out.println("✅ Exported to: " + filename);
        } catch (Exception e) {
            System.out.println("❌ Failed to export: " + e.getMessage());
        }
    }
}
