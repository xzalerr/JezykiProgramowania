package SystemOfEquationSolver;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new GridLayout(0, 1));
        JPanel aPanel = new JPanel();
        JPanel bPanel = new JPanel();
        JPanel cPanel = new JPanel();
        aPanel.setLayout(new GridLayout(1, 3));
        bPanel.setLayout(new GridLayout(1, 4));
        cPanel.setLayout(new GridLayout(1, 4));
        aPanel.add(new JLabel());
        aPanel.add(createOneLetterPanel("a1"));
        aPanel.add(createOneLetterPanel("a2"));
        aPanel.add(createOneLetterPanel("a3"));

        bPanel.add(createOneLetterPanel("+/-"));
        bPanel.add(createOneLetterPanel("b1"));
        bPanel.add(createOneLetterPanel("b2"));
        bPanel.add(createOneLetterPanel("b3"));

        cPanel.add(createOneLetterPanel("c0"));
        cPanel.add(createOneLetterPanel("c1"));
        cPanel.add(createOneLetterPanel("c2"));
        cPanel.add(createOneLetterPanel("c3"));

        JButton btnSolve = new JButton("Rozwiąż");
        JLabel lblSolution = new JLabel("Tutaj pojawi się rozwiązanie.", SwingConstants.CENTER);


        add(aPanel);
        add(bPanel);
        add(new LinePanel());
        add(cPanel);
        add(btnSolve);
        add(lblSolution);
    }
    public static JPanel createOneLetterPanel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        JLabel lbl = new JLabel(text);
        JTextField txt = new JTextField();
        panel.add(lbl);
        panel.add(txt);
        return panel;
    }

}

