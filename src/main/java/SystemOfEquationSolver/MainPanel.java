package SystemOfEquationSolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private static int operation = 0;
    private static ArrayList<JTextField> textFields;
    public MainPanel() {
        setLayout(new GridLayout(0, 1));
        setPreferredSize(new Dimension(600, 300));
        textFields = new ArrayList<>();

        JPanel aPanel = new JPanel();
        JPanel operationPanel = new JPanel();
        JPanel bPanel = new JPanel();
        JPanel cPanel = new JPanel();
        aPanel.setLayout(new GridLayout(1, 3));
        bPanel.setLayout(new GridLayout(1, 4));
        cPanel.setLayout(new GridLayout(1, 4));

        aPanel.add(new JLabel());
        aPanel.add(createOneLetterPanel("a1"));
        aPanel.add(createOneLetterPanel("a2"));
        aPanel.add(createOneLetterPanel("a3"));

        operationPanel.setLayout(new GridLayout(2, 1));
        JLabel lblOperation = new JLabel("+/-");
        JTextField txtOperation = new JTextField();
        checkText(txtOperation);
        operationPanel.add(lblOperation);
        operationPanel.add(txtOperation);
        bPanel.add(operationPanel);
        bPanel.add(createOneLetterPanel("b1"));
        bPanel.add(createOneLetterPanel("b2"));
        bPanel.add(createOneLetterPanel("b3"));

        cPanel.add(createOneLetterPanel("c0"));
        cPanel.add(createOneLetterPanel("c1"));
        cPanel.add(createOneLetterPanel("c2"));
        cPanel.add(createOneLetterPanel("c3"));

        JButton btnSolve = new JButton("Rozwiąż");
        JLabel lblSolution = new JLabel("Tutaj pojawi się rozwiązanie.", SwingConstants.CENTER);
        btnSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkText(txtOperation);
                String[] values = new String[10];
                for(int i = 0; i < 10; i++) {
                    String txt = textFields.get(i).getText();
                    if(!txt.isBlank()) {
                        values[i] = txt;
                    } else {
                        values[i] = "0";
                    }
                }
                Equation.addValues(values, operation);
                String[] results = Equation.getResults();
                lblSolution.setText("<html>" + String.join("<br>", results) + "</html>");
                Equation.refresh();
            }
        });



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
        textFields.add(txt);
        panel.add(lbl);
        panel.add(txt);
        return panel;
    }

    public void checkText(JTextField txt) {
        if (txt.getText().equals("+")){
            operation = 1;
        } else if (txt.getText().equals("-")) {
            operation = -1;
        } else if(txt.getText().equals("")) {
            operation = 0;
        } else {
            operation = 0;
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(MainPanel.this);
            JOptionPane.showMessageDialog(parentFrame, "Znakiem operacji może być + lub -!");
        }
    }
}

