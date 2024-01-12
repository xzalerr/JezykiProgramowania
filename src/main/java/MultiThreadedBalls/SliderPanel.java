package MultiThreadedBalls;

import javax.swing.*;
import java.awt.*;

public class SliderPanel extends JPanel {
    JComboBox comboBox;
    public SliderPanel() {
        setPreferredSize(new Dimension(600, 100));
        setLayout(new GridLayout(2, 1));
        JSlider slider = new JSlider();
        slider.setPreferredSize(new Dimension(600, 50));
        comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(600, 50));
        add(comboBox);
        add(slider);
    }
}
