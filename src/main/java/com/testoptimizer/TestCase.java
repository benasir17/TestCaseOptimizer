package com.testoptimizer;

public class TestCase {
    private String id;
    private String title;
    private String steps;
    private String expected;

    public TestCase(String id, String title, String steps, String expected) {
        this.id = id;
        this.title = title;
        this.steps = steps;
        this.expected = expected;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getSteps() { return steps; }
    public String getExpected() { return expected; }
}

