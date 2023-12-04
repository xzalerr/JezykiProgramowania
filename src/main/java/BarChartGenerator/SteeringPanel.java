package BarChartGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SteeringPanel extends JPanel {
    private ArrayList<JTextField> textFields;
    private ArrayList<JCheckBox> checkBoxes;
    private Values values;
    private BarChartPanel barChartPanel;
    public SteeringPanel(Values values, BarChartPanel barChartPanel) {
        this.values = values;
        this.barChartPanel = barChartPanel;
        textFields = new ArrayList<>();
        checkBoxes = new ArrayList<>();
        setLayout(new GridLayout(10, 2));
        setPreferredSize(new Dimension(150, 500));
        for(int i = 0; i < 10; i++) {
            final int index = i;
            JCheckBox checkBox = new JCheckBox("Dodaj.");
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(checkBox.isSelected()) {
                        Timer timer = new Timer(100, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for (int i = 0; i < 10; i++) {
                                    if (checkBoxes.get(i).isSelected()) {
                                        values.addNumber(textFields.get(i).getText(), i);
                                        barChartPanel.updateChart();
                                    }
                                }
                            }
                        });
                        timer.start();
                    } else if(!checkBox.isSelected()) {
                        try{
                            values.deleteNumber(textFields.get(index).getText(), index);
                            barChartPanel.updateChart();
                        } catch (NieprawidlowaWartoscException ex) {
                            checkBox.setSelected(true);
                            textFields.get(index).setText(values.getList().get(index).toString());
                        }
                    }
                }
            });
            checkBoxes.add(checkBox);
            add(checkBox);
            JTextField textField = new JTextField();
            textFields.add(textField);
            add(textField);
        }
    }
}
