package BarChartGenerator;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new BorderLayout());

        Values values = new Values();
        BarChartPanel barChartPanel = new BarChartPanel(values);
        SteeringPanel steeringPanel = new SteeringPanel(values, barChartPanel);

        add(barChartPanel, BorderLayout.EAST);
        add(steeringPanel, BorderLayout.WEST);
    }
}
