package PieChartGenerator;

import javax.swing.*;
import java.awt.*;

public class PieChartPanel extends JPanel {
    private ListPanel listPanel;
    public PieChartPanel(ListPanel listPanel) {
        this.listPanel = listPanel;
        setPreferredSize(new Dimension(400, 400));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        double sum = 0.0;

        for (Integer num : listPanel.getList()) {
            sum += num;
        }

        int startingValue = 0;
        int finishAngle;
        int wholeValue = 0;
        for(Integer num : listPanel.getList()) {
            finishAngle = (int) ((num * 360) / sum);
            g.setColor(getColorFromInt(num));
            g.fillArc(10, 10, width-20, height-20, startingValue, finishAngle);
            wholeValue += num;
            startingValue = (int) ((wholeValue * 360) / sum);
        }
    }


    private Color getColorFromInt(int num) {
        float hue = (num % 256) / 255.0f;
        float saturation = 1.0f;
        float brightness = 1.0f;

        return Color.getHSBColor(hue, saturation, brightness);
    }

    public void updateChart() {
        repaint();
    }
}
