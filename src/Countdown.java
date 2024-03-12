import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Countdown {
    public static void main(String[] args) {
        new Countdown();

    }

    public Countdown() {
        JFrame frame = new JFrame("Countdown");
        JButton button = new JButton("Calculate Difference");
        JLabel label = new JLabel("Days until");
        JPanel panel = new JPanel();
        JTextField startField = new JTextField();
        JTextField endField = new JTextField();
        JTextField outputField = new JTextField();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(startField);
        panel.add(endField);
        panel.add(button);
        panel.add(outputField);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (formatCheckValidate(startField.getText()) && formatCheckValidate(endField.getText())) {
                    JOptionPane.showMessageDialog(null, "Valid format", null, JOptionPane.INFORMATION_MESSAGE);
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

    public static void getDateDiffWithUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date: ");
        String date = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        Date date2 = null;
        try {
            date2 = (Date) dateFormat.parse(date);
            System.out.print("You have " + ((Temporal) date2).until(LocalDate.now(), ChronoUnit.DAYS) + " days left.");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static long getDateDiff(LocalDate date) {
        return date.until(LocalDate.now(), ChronoUnit.DAYS);
    }

}
