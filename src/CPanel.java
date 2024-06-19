import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CPanel extends JPanel implements Serializable {

    private CFrame cFrame;

    public CPanel(CFrame cFrame) throws IOException {
        this.cFrame = cFrame;
        JButton button = new JButton("Calculate difference");
        JButton buttonSave = new JButton("Save");
        JTextField nameField = new JTextField(15);
        JTextField startField = new JTextField(15);
        JTextField endField = new JTextField(15);
        JLabel outputLabel = new JLabel("Days left: ");
        JLabel saveLabel = new JLabel("Data saved: ");

        // Table with saved countdowns
        String[] columnNames = { "Name", "Days left" };
        Object[][] data = { { getSavedName(), getSavedDaysLeft() } };
        JTable savedDataTable = new JTable(data, columnNames);

        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        this.setLayout(new GridLayout(0, 1));
        this.add(nameField);
        this.add(startField);
        this.add(endField);
        this.add(button);
        this.add(outputLabel);
        this.add(buttonSave);
        this.add(saveLabel);
        this.add(savedDataTable.getTableHeader());
        this.add(savedDataTable);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (formatCheckValidate(startField.getText()) && formatCheckValidate(endField.getText())) {
                    outputLabel.setText("Days left: " + getDiff(startField.getText(), endField.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid format! Input must be in the format 'YYYY-MM-DD'",
                            null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveState(nameField.getText(), startField.getText(), endField.getText());
                try {
                    saveLabel.setText("Data saved: " + getSavedName() + getSavedDaysLeft());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });

    }

    public boolean formatCheckValidate(String input) {
        return input.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public String getDiff(String start, String end) {

        LocalDate date1 = LocalDate.parse(start);
        LocalDate date2 = LocalDate.parse(end);
        return Long.toString(date1.until(date2, ChronoUnit.DAYS));

    }

    public void saveState(String name, String start, String end) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(name.toString() + "\n");
            writer.write(start.toString() + "\n");
            writer.write(end.toString() + "\n");
            writer.write(getDiff(start, end).toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSavedName() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
        String name = reader.readLine();
        reader.close();
        return name;

    }

    public String getSavedDaysLeft() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
        String name = reader.readLine();
        String start = reader.readLine();
        String end = reader.readLine();
        String diff = reader.readLine();
        reader.close();
        return diff;

    }

}
