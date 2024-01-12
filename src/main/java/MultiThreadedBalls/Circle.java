package MultiThreadedBalls;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Thread {
    private int x;
    private int y;
    private int[] cords;
    private Color color;
    private double angle;
    private int speed;
    private MovementPanel movementPanel;

    public Circle(int x, int y, Color color, MovementPanel movementPanel, int speed) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.movementPanel = movementPanel;
        this.speed = speed;
        this.angle = 0;
    }

    @Override
    public void run() {
        cords = new int[2];
        while (!Thread.interrupted()) {
            move();
            draw();
            cords[0] = this.x;
            cords[1] = this.y;
            try {
                Thread.sleep(100/speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void move() {
        int radius = movementPanel.getRadius();
        this.x = (int) (movementPanel.getCenterX() + radius * Math.cos(angle));
        this.y = (int) (movementPanel.getCenterY() + radius * Math.sin(angle));
        angle += 0.01 * speed;
    }

    public int[] getCords() {
        return cords;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void draw() {
        Graphics g = movementPanel.getGraphics();
        movementPanel.drawCircle(g, x, y, movementPanel.getRadius(), color);
    }
}
