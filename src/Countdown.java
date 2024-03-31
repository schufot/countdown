
/**
 * 
 * author: schufot
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.util.Locale;

public class Countdown {
    public static void main(String[] args) {
        new Countdown();

    }

    public Countdown() {
        JFrame frame = new JFrame("Countdown");
        JButton button = new JButton("Calculate difference");
        JPanel panel = new JPanel();
        JTextField startField = new JTextField(15);
        JTextField endField = new JTextField(15);
        JLabel outputLabel = new JLabel("Days left: ");
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(startField);
        panel.add(endField);
        panel.add(button);
        panel.add(outputLabel);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

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

    }

    public boolean formatCheckValidate(String input) {
        return input.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public String getDiff(String start, String end) {

        LocalDate date1 = LocalDate.parse(start);
        LocalDate date2 = LocalDate.parse(end);
        return Long.toString(date1.until(date2, ChronoUnit.DAYS));

    }

}
