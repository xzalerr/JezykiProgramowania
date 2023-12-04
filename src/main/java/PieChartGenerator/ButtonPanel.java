package PieChartGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private ListPanel listPanel;
    private PieChartPanel chartPanel;
    public ButtonPanel(ListPanel listPanel, PieChartPanel chartPanel) {
        this.listPanel = listPanel;
        this.chartPanel = chartPanel;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JTextField textField = new JTextField();
        JButton btnAdd = new JButton("Dodaj");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listPanel.addNumber(textField.getText());
                textField.setText("");
                chartPanel.updateChart();
            }
        });
        JButton btnDelete = new JButton("Usuń");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listPanel.getNumbers().getSelectedIndex();
                listPanel.deleteNumber(index);
                chartPanel.updateChart();
            }
        });
        JButton btnEdit = new JButton("Edytuj");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listPanel.getNumbers().getSelectedIndex();
                listPanel.editNumber(textField.getText(), index);
                chartPanel.updateChart();
            }
        });

        textField.setPreferredSize(new Dimension(180, 60));
        btnAdd.setPreferredSize(new Dimension(80, 60));
        btnDelete.setPreferredSize(new Dimension(80, 60));
        btnEdit.setPreferredSize(new Dimension(80, 60));

        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(textField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(btnAdd, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        add(btnDelete, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(btnEdit, c);
    }
}
