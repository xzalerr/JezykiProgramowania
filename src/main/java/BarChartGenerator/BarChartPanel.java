package BarChartGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BarChartPanel extends JPanel{
    private Values values;
    public BarChartPanel(Values values) {
        this.values = values;
        setPreferredSize(new Dimension(500, 500));
    }
    public void paintComponent(Graphics g) {
        Rectangle area = getBounds();
        Integer max = values.getList().stream().filter(Objects::nonNull).max(Integer::compare).orElse(null);
        if (max != null) {
            for (int i = 0; i < values.getList().size(); i++) {
                Integer liczba = values.getList().get(i) != null ? values.getList().get(i) : 0;
                g.setColor(getColorFromIndex(i));
                double height = ((double) liczba/ (double) max)*area.height;
                g.fillRect(i*40+(i-1)*10+10, (int) (area.height-height), (int) 40, (int) height);
            }
        }
    }
    private Color getColorFromIndex(int num) {
        Color[] colorMap = {
                Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN,
                Color.BLUE, Color.MAGENTA, Color.PINK, Color.GRAY, Color.BLACK
        };

        return colorMap[num];
    }
    public void updateChart() {
        repaint();
    }
}

