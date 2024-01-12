package MultiThreadedBalls;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MovementPanel movementPanel = new MovementPanel();
        SliderPanel sliderPanel = new SliderPanel();
        Circle circle = new Circle(40, 100, Color.RED, movementPanel, 1);
        Circle circle2 = new Circle(0, 0, Color.BLUE, movementPanel, 3);

        JFrame frame = new JFrame("Multi Threaded Balls");
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 750);

        frame.add(movementPanel, BorderLayout.CENTER);
        frame.add(sliderPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        circle.start();
        //circle2.start();
    }
}
