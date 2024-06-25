import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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

        // Table with saved countdowns
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.addColumn("Name");
        modelTable.addColumn("Days left");
        JTable savedDataTable = new JTable(modelTable);

        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        this.setLayout(new GridLayout(0, 1));
        this.add(nameField);
        this.add(startField);
        this.add(endField);
        this.add(button);
        this.add(outputLabel);
        this.add(buttonSave);
        this.add(savedDataTable.getTableHeader());
        this.add(savedDataTable);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (formatCheckValidate(startField.getText()) && formatCheckValidate(endField.getText())) {
                    Entry entry = new Entry(nameField.getText(), LocalDate.parse(startField.getText()),
                            LocalDate.parse(endField.getText()));
                    outputLabel.setText("Days left: " + entry.getDiff());

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid format! Input must be in the format 'YYYY-MM-DD'",
                            null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) savedDataTable.getModel();
                try {
                    model.addRow(new Object[] { getSavedName(), getSavedDaysLeft() });

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });

    }

    public boolean formatCheckValidate(String input) {
        return input.matches("\\d{4}-\\d{2}-\\d{2}");
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
