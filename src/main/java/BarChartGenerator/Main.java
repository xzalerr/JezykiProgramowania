package BarChartGenerator;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bar Chart Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MainPanel mainPanel = new MainPanel();
            frame.add(mainPanel);

            frame.setSize(650, 500);
            frame.setVisible(true);

        });
    }
}
