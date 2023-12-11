package StringCalculator;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("String Calculator");
        frame.setSize(600, 750);
        frame.setLayout(new BorderLayout());
        frame.add(new LetterPanel(), BorderLayout.NORTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
