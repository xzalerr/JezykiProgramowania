package BarChartGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BarChartPanel extends JPanel{
    private Values values;
    public BarChartPanel(Values values) {
        this.values = values;
        setPreferredSize(new Dimension(600, 500));
    }
    public void paintComponent(Graphics g) {
        Rectangle area = getBounds();
        Integer max = values.getList().stream().filter(Objects::nonNull).max(Integer::compare).orElse(null);
        Integer active = (int) values.getList().stream().filter(a -> a > 0).count();
        if(active > 0) {
            int colWidth = area.width / active;
            int gap = 0;
            if(colWidth > 150) {
                colWidth = 150;
                gap = (area.width - (colWidth * active)) / 2;
            }
            int counter = 0;
            if (max != null) {
                for (int i = 0; i < 10; i++) {
                    Integer liczba = values.getList().get(i) != null ? values.getList().get(i) : 0;
                    g.setColor(getColorFromIndex(i));
                    double height = ((double) liczba/ (double) max)*area.height;
                    if(liczba > 0) {
                        g.fillRect(counter * colWidth + gap, (int) (area.height - height) + 5, colWidth - 10, (int) height);
                        counter++;
                    }
                }
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

