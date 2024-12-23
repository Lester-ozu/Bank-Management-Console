import javax.swing.JPanel;
import java.awt.*;

public class MyPanel extends JPanel {

    MyPanel() {

        final int panel_WIDTH = 600;
        final int panel_HEIGHT = 600;

        this.setPreferredSize(new Dimension(panel_WIDTH, panel_HEIGHT));
        this.setLayout(new BorderLayout());
    }
}
