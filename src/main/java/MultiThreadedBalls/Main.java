package MultiThreadedBalls;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    static ArrayList<Circle> circles;
    static ArrayList<Double> usedAngles;
    public static void main(String[] args) {
        MovementPanel movementPanel = new MovementPanel();
        circles = createCircles(20, movementPanel);
        colorCircles();
        selectAngles();
        SliderPanel sliderPanel = new SliderPanel(circles);

        JFrame frame = new JFrame("Multi Threaded Balls");
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 600);

        frame.add(movementPanel, BorderLayout.CENTER);
        frame.add(sliderPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        for(Circle circle : circles) {
            circle.start();
        }
    }

    private static ArrayList<Circle> createCircles(int n, MovementPanel mp) {
        ArrayList<Circle> circlesCreated = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            circlesCreated.add(new Circle(mp, circlesCreated));
        }
        return circlesCreated;
    }

    public static void selectAngles() {
        Random rd = new Random();
        usedAngles = new ArrayList<>();

        for (int i = 0; i < circles.size(); i++) {
            double selectedAngle;
            do {
                selectedAngle = rd.nextDouble() * 360.0;
                selectedAngle = Math.toRadians(selectedAngle);

            } while (isAngleTooClose(selectedAngle));

            usedAngles.add(selectedAngle);
        }

        int index = 0;
        for (Circle circle : circles) {
            circle.setAngle(usedAngles.get(index));
            index++;
        }
    }

    private static boolean isAngleTooClose(double newAngle) {
        for (double angle : usedAngles) {
            double angleDifference = Math.abs(newAngle - angle);
            double fullRotation = 2 * Math.PI;

            if (angleDifference < 0.15 || (fullRotation - angleDifference) < 0.15) {
                return true;
            }
        }
        return false;
    }

    private static void colorCircles() {
        float interval = (float) 360 / circles.size();
        Color[] colors = new Color[circles.size()];
        int i = 0;
        for (float x = 0; x < 360; x += interval) {
            Color c = Color.getHSBColor(x / 360, 1, 1);
            colors[i] = c;
            i++;
        }
        i = 0;
        for(Circle c : circles) {
            c.setColor(colors[i]);
            i++;
        }
    }
}