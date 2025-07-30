package com.testoptimizer;

import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.ArrayList;
import java.util.List;

public class DuplicateDetector {
    public static List<String[]> findSimilarTestCases(List<TestCase> testCases) {
        List<String[]> similar = new ArrayList<>();
        LevenshteinDistance ld = new LevenshteinDistance();

        for (int i = 0; i < testCases.size(); i++) {
            for (int j = i + 1; j < testCases.size(); j++) {
                int distance = ld.apply(testCases.get(i).getSteps(), testCases.get(j).getSteps());
                if (distance < 15) {
                    System.out.println("⚠️ Similar Test Steps Found:");
                    System.out.println("→ " + testCases.get(i).getId() + ": " + testCases.get(i).getSteps());
                    System.out.println("→ " + testCases.get(j).getId() + ": " + testCases.get(j).getSteps());
                    System.out.println("Levenshtein Distance: " + distance);
                    System.out.println("--------------------------------------");

                    similar.add(new String[]{
                            testCases.get(i).getId(),
                            testCases.get(i).getSteps(),
                            testCases.get(j).getId(),
                            testCases.get(j).getSteps(),
                            String.valueOf(distance)
                    });
                }
            }
        }

        return similar;
    }
}
