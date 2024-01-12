package MultiThreadedBalls;

import javax.swing.*;
import java.awt.*;

public class MovementPanel extends JPanel {
    private int centerX;
    private int centerY;
    private final int radius = 200;

    public MovementPanel() {
        setPreferredSize(new Dimension(600, 650));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0,0,600,650);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;

        g.setColor(Color.BLACK);
        g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void drawCircle(Graphics g, int x, int y, int radius, Color color) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,600,650);
        g.setColor(color);
        g.fillOval(x, y, 20, 20);
    }
}
