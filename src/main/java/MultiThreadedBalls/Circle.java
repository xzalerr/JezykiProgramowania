package MultiThreadedBalls;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Circle extends Thread {
    private int x, xBefore;
    private int y, yBefore;
    private Color color;
    private double angle;
    private int speed;
    private MovementPanel movementPanel;
    private ArrayList<Circle> circles;

    public Circle(MovementPanel movementPanel, ArrayList<Circle> circles) {
        this.color = Color.BLACK;
        this.movementPanel = movementPanel;
        Random rd = new Random();
        this.speed = rd.nextInt(5) +1;
        this.angle = 0;
        this.circles = circles;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            move();
            draw();
            try {
                Thread.sleep(300/speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void move() {
        synchronized (circles) {
            int radius = movementPanel.getRadius();
            this.xBefore = this.x;
            this.yBefore = this.y;
            this.x = (int) (movementPanel.getCenterX() + radius * Math.cos(angle));
            this.y = (int) (movementPanel.getCenterY() + radius * Math.sin(angle));
            angle += 0.01*speed;

            for (Circle otherCircle : circles) {
                if (otherCircle != this) {
                    double distance = Math.sqrt(Math.pow(x - otherCircle.x, 2) + Math.pow(y - otherCircle.y, 2));
                    double minDistance = 30;

                    if (distance < minDistance) {
                        if (speed > otherCircle.speed) {
                            speed = otherCircle.speed;
                            angle = otherCircle.angle - 0.15;
                        }
                    }
                }
            }
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void draw() {
        Graphics g = movementPanel.getGraphics();
        movementPanel.drawCircle(g, xBefore, yBefore, x, y, color, circles.indexOf(this));
    }
}