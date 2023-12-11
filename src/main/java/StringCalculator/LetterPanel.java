package StringCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LetterPanel extends JPanel {
    private static final String[][] buttonTexts = {
            {"abc", "def", "ghi"},
            {"jkl", "mno", "pqr"},
            {"stuv", "wxyz"}
    };
    private boolean isUpper = false;
    private ArrayList<JButton> buttons;
    private static String typedIn = "";
    public LetterPanel() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 600));
        buttons = new ArrayList<>();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JTextField textField = new JTextField();
        for(int i = 0; i < buttonTexts.length; i++) {
            for(int j = 0; j < buttonTexts[i].length; j++) {
                JButton btn = new JButton(buttonTexts[i][j]);
                btn.addActionListener(new ActionListener() {
                    int counter = 0;
                    long lastClick = -1;
                    String[] letters = getLetters(btn);
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        long currentClick = System.currentTimeMillis();
                        if(currentClick - lastClick <= 2000) {
                            counter++;
                            counter = counter % 3;
                        } else {
                            counter = 0;
                        }
                        letters = getLetters(btn);
                        String letter = letters[counter];
                        //typedIn += letter;
                        textField.setText(letter);
                        lastClick = System.currentTimeMillis();
                    }
                });
                gbc.gridx = j;
                gbc.gridy = i;
                buttons.add(btn);
                add(btn, gbc);
            }
        }
        gbc.gridy = 3;

        JButton btnUpperLower = new JButton("D/M");
        btnUpperLower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isUpper = !isUpper;
                for(JButton b : buttons) {
                    b.setText(isUpper ? b.getText().toUpperCase() : b.getText().toLowerCase());
                }
            }
        });
        gbc.gridx = 0;
        add(btnUpperLower, gbc);

        JButton btnDeleteLast = new JButton("CE");
        gbc.gridx = 1;
        add(btnDeleteLast, gbc);

        JButton btnDelete = new JButton("C");
        gbc.gridx = 2;
        add(btnDelete, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add(textField, gbc);
    }
    public String[] getLetters(JButton btn) {
        return btn.getText().split("");
    }
}
