import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class DateDifferenceAppGUI extends JFrame {
    private static List<DateDifference> dateDifferenceList = new ArrayList<>();
    private DefaultTableModel tableModel;

    public DateDifferenceAppGUI() {
        setTitle("Date Difference Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new BorderLayout());

        JTextField startDateField = new JTextField("Enter start date (yyyy-MM-dd)");
        startDateField.setPreferredSize(new Dimension(200, 30));
        startDateField.setForeground(java.awt.Color.GRAY);
        startDateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (startDateField.getText().equals("Enter start date (yyyy-MM-dd)")) {
                    startDateField.setText("");
                    startDateField.setForeground(java.awt.Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (startDateField.getText().isEmpty()) {
                    startDateField.setForeground(java.awt.Color.GRAY);
                    startDateField.setText("Enter start date (yyyy-MM-dd)");
                }
            }
        });

        JTextField endDateField = new JTextField("Enter end date (yyyy-MM-dd)");
        endDateField.setPreferredSize(new Dimension(200, 30));
        endDateField.setForeground(java.awt.Color.GRAY);
        endDateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (endDateField.getText().equals("Enter end date (yyyy-MM-dd)")) {
                    endDateField.setText("");
                    endDateField.setForeground(java.awt.Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (endDateField.getText().isEmpty()) {
                    endDateField.setForeground(java.awt.Color.GRAY);
                    endDateField.setText("Enter end date (yyyy-MM-dd)");
                }
            }
        });

        JTextField descriptionField = new JTextField("Enter description");
        descriptionField.setPreferredSize(new Dimension(200, 30));
        descriptionField.setForeground(java.awt.Color.GRAY);
        descriptionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (descriptionField.getText().equals("Enter description")) {
                    descriptionField.setText("");
                    descriptionField.setForeground(java.awt.Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (descriptionField.getText().isEmpty()) {
                    descriptionField.setForeground(java.awt.Color.GRAY);
                    descriptionField.setText("Enter description");
                }
            }
        });

        fieldsPanel.add(startDateField, BorderLayout.NORTH);
        fieldsPanel.add(endDateField, BorderLayout.CENTER);
        fieldsPanel.add(descriptionField, BorderLayout.SOUTH);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> {
            LocalDate startDate = LocalDate.parse(startDateField.getText());
            LocalDate endDate = LocalDate.parse(endDateField.getText());
            String description = descriptionField.getText();
            calculateAndAddDateDifference(startDate, endDate, description);
            updateTable();
            startDateField.setText("Enter start date (yyyy-MM-dd)");
            startDateField.setForeground(java.awt.Color.GRAY);
            endDateField.setText("Enter end date (yyyy-MM-dd)");
            endDateField.setForeground(java.awt.Color.GRAY);
            descriptionField.setText("Enter description");
            descriptionField.setForeground(java.awt.Color.GRAY);
        });

        inputPanel.add(fieldsPanel, BorderLayout.CENTER);
        inputPanel.add(calculateButton, BorderLayout.SOUTH);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[] { "Start Date", "End Date", "Days Difference", "Description" },
                0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void calculateAndAddDateDifference(LocalDate startDate, LocalDate endDate, String description) {
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate);
        DateDifference dateDifference = new DateDifference(startDate, endDate, daysDifference, description);
        dateDifferenceList.add(dateDifference);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (DateDifference dateDifference : dateDifferenceList) {
            tableModel.addRow(new Object[] {
                    dateDifference.getStartDate(),
                    dateDifference.getEndDate(),
                    dateDifference.getDaysDifference(),
                    dateDifference.getDescription()
            });
        }
    }

    private static class DateDifference {
        private LocalDate startDate;
        private LocalDate endDate;
        private long daysDifference;
        private String description;

        public DateDifference(LocalDate startDate, LocalDate endDate, long daysDifference, String description) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.daysDifference = daysDifference;
            this.description = description;
        }

        // Getters and setters
        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public long getDaysDifference() {
            return daysDifference;
        }

        public String getDescription() {
            return description;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DateDifferenceAppGUI().setVisible(true);
        });
    }
}