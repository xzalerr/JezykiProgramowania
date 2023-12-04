package PieChartGenerator;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new BorderLayout());
        JPanel steeringPanel = new JPanel(new BorderLayout());

        ListPanel listPanel = new ListPanel();
        PieChartPanel chartPanel = new PieChartPanel(listPanel);
        ButtonPanel buttonPanel = new ButtonPanel(listPanel, chartPanel);

        steeringPanel.add(listPanel, BorderLayout.NORTH);
        steeringPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(chartPanel, BorderLayout.WEST);
        add(steeringPanel, BorderLayout.EAST);
    }
}
