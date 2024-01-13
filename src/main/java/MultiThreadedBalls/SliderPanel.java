package MultiThreadedBalls;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;

public class SliderPanel extends JPanel {
    private JComboBox<String> comboBox;
    private JSlider slider;
    private List<Circle> circles;

    public SliderPanel(List<Circle> circles) {
        this.circles = circles;

        setPreferredSize(new Dimension(500, 100));
        setLayout(new GridLayout(2, 1));

        String[] circleNames = new String[circles.size()];
        for (int i = 0; i < circles.size(); i++) {
            circleNames[i] = "Circle " + (i + 1);
        }

        comboBox = new JComboBox<>(circleNames);
        slider = new JSlider(1, 5);

        comboBox.addActionListener(e -> {
            int selectedIndex = comboBox.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < circles.size()) {
                Circle selectedCircle = circles.get(selectedIndex);
                int speed = selectedCircle.getSpeed();
                slider.setValue(speed);
                selectedCircle.setSpeed(speed);
            }
        });

        if (!circles.isEmpty()) {
            Circle firstCircle = circles.get(0);
            slider.setValue(firstCircle.getSpeed());
        }

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = comboBox.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < circles.size()) {
                    Circle selectedCircle = circles.get(selectedIndex);
                    int speed = slider.getValue();
                    selectedCircle.setSpeed(speed);
                }
            }
        });

        add(comboBox);
        add(slider);
    }

}
