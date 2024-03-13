import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        JLabel outputLabel = new JLabel("Diff");
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(startField);
        panel.add(endField);
        panel.add(button);
        panel.add(outputLabel);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (formatCheckValidate(startField.getText()) && formatCheckValidate(endField.getText())) {
                    outputLabel.setText(getDiff(startField.getText(), endField.getText()));
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        try {
            Date date1 = (Date) dateFormat.parse(start);
            Date date2 = (Date) dateFormat.parse(end);
            long difference_In_Time = date2.getTime() - date1.getTime();
            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
            return Long.toString(difference_In_Days);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

}
