package SystemOfEquationSolver;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("System of Equation Solver");
        frame.setSize(750, 750);
        frame.setLayout(new BorderLayout());
        frame.add(new MainPanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
