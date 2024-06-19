import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.IOException;

public class CFrame extends JFrame {

    private CPanel cPanel;

    public CFrame() throws IOException {
        super();
        this.setTitle("Countdown");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cPanel = new CPanel(this);
        this.add(cPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }

}
