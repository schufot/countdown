import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CFrame extends JFrame {

    private CPanel cPanel;

    public CFrame() {
        super();
        this.setTitle("Countdown");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cPanel = new CPanel(this);
        this.add(cPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

        //saveState(cPanel);
    }

    public void saveState(JPanel cPanel){
        try {
            FileOutputStream fs = new FileOutputStream("countdown.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(cPanel);
            os.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
