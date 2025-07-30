package com.testoptimizer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.List;

public class TestCaseGUI extends JFrame {

    private JTextArea outputArea;

    public TestCaseGUI() {
        setTitle("Test Case Optimizer");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen

        JButton uploadButton = new JButton("📂 Upload CSV");
        JButton processButton = new JButton("✅ Find Similar Cases");

        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel panel = new JPanel();
        panel.add(uploadButton);
        panel.add(processButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        final String[] csvPath = {null}; // store file path

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                csvPath[0] = fileChooser.getSelectedFile().getAbsolutePath();
                outputArea.append("📄 File selected: " + csvPath[0] + "\n");
            }
        });

        processButton.addActionListener(e -> {
            if (csvPath[0] == null) {
                JOptionPane.showMessageDialog(this, "Please upload a CSV file first!");
                return;
            }

            List<TestCase> testCases = CSVReaderUtil.readTestCases(csvPath[0]);
            List<String[]> similar = DuplicateDetector.findSimilarTestCases(testCases);
            ExportUtil.writeSimilarTestCases(similar, "similar_testcases.csv");

            if (similar.isEmpty()) {
                outputArea.append("✅ No similar test steps found.\n");
            } else {
                outputArea.append("✅ Similar test cases found & saved to 'similar_testcases.csv'\n");
                for (String[] row : similar) {
                    outputArea.append("→ " + row[0] + " & " + row[2] + " → Distance: " + row[4] + "\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestCaseGUI().setVisible(true));
    }
}
