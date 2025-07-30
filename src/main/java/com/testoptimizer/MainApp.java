package com.testoptimizer;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        String path = "src/main/resources1/testcases.csv";
        List<TestCase> testCases = CSVReaderUtil.readTestCases(path);

        List<String[]> similarCases = DuplicateDetector.findSimilarTestCases(testCases);

        ExportUtil.writeSimilarTestCases(similarCases, "similar_testcases.csv");
    }
}
