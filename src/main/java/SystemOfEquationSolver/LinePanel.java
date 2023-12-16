package SystemOfEquationSolver;

import javax.swing.*;
import java.awt.*;

public class LinePanel extends JPanel {
    public LinePanel() {
        setPreferredSize(new Dimension(600, 3));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, 600, 3);
    }
}
