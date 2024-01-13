package MultiThreadedBalls;

import javax.swing.*;
import java.awt.*;

public class MovementPanel extends JPanel {
    private int centerX;
    private int centerY;
    private final int radius = 200;


    public MovementPanel() {
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,500,500);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
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

    public void drawCircle(Graphics g, int xBefore, int yBefore, int x, int y, Color color, int num) {
        g.setColor(Color.WHITE);
        g.fillOval(xBefore, yBefore, 25, 25);
        g.setColor(color);
        g.fillOval(x, y, 25, 25);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(num+1), x+5, y+14);
    }
}