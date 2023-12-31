package StringCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//TODO make a seperate class for operations and seperate ActionListeners
public class LetterPanel extends JPanel {
    private static final String[][] buttonTexts = {
            {"abc", "def", "ghi"},
            {"jkl", "mno", "pqr"},
            {"stuv", "wxyz"}
    };
    private boolean isUpper = false;
    private ArrayList<JButton> buttons;
    private static String typedIn = "";
    private int operation = 0;
    private Timer timer;
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
                        if(currentClick - lastClick < 1000) {
                            counter++;
                            counter = counter % letters.length;
                        } else {
                            counter = 0;
                        }
                        letters = getLetters(btn);
                        String letter = letters[counter];
                        textField.setText(typedIn + letter);
                        lastClick = System.currentTimeMillis();
                        timer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(System.currentTimeMillis() - lastClick >= 1000) {
                                    typedIn+=letter;
                                    refresh(textField);
                                }
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
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
        btnDeleteLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    typedIn = typedIn.substring(0, typedIn.length()-1);
                    refresh(textField);
                } catch(StringIndexOutOfBoundsException message) {
                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LetterPanel.this);
                    JOptionPane.showMessageDialog(parentFrame,"Nie można usunąć, bo nie ma wpisanych żadnych liter.");
                }

            }
        });
        gbc.gridx = 1;
        add(btnDeleteLast, gbc);

        JButton btnDelete = new JButton("C");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typedIn = "";
                refresh(textField);
            }
        });
        gbc.gridx = 2;
        add(btnDelete, gbc);

        gbc.gridx = 4;
        JButton btnAdd = new JButton("+");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typedIn += "+";
                operation = 1;
                refresh(textField);
            }
        });
        gbc.gridy = 0;
        add(btnAdd, gbc);

        JButton btnSubtract = new JButton("-");
        btnSubtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typedIn += "-";
                operation = 2;
                refresh(textField);
            }
        });
        gbc.gridy = 1;
        add(btnSubtract, gbc);

        JButton btnDivide = new JButton("/");
        btnDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typedIn += "/";
                operation = 3;
                refresh(textField);
            }
        });
        gbc.gridy = 2;
        add(btnDivide, gbc);

        JButton btnEquals = new JButton("=");
        btnEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(operation==1) {
                    String[] parts = typedIn.split("\\+");
                    typedIn = parts[0] + parts[1];
                    refresh(textField);
                    operation = 0;
                } else if(operation==2) {
                    String[] parts = typedIn.split("-");
                    typedIn = typedIn.replace("-", "");
                    typedIn = typedIn.replace(parts[1], "");
                    refresh(textField);
                    operation = 0;
                } else if(operation==3) {
                    String[] parts = typedIn.split("/");
                    typedIn = longestCommonSubstring(parts[0], parts[1]);
                    refresh(textField);
                    operation = 0;
                } else {
                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LetterPanel.this);
                    JOptionPane.showMessageDialog(parentFrame, "Nie podałeś działania, jakie chcesz wykonać.");
                }
            }
        });
        gbc.gridy = 3;
        add(btnEquals, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add(textField, gbc);
    }
    public void refresh(JTextField textField) {
        textField.setText(typedIn);
    }
    public String[] getLetters(JButton btn) {
        return btn.getText().split("");
    }
    public String longestCommonSubstring(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int max = 0;
        int index = 0;
        int[][] allLengths = new int[n+1][m+1];
        StringBuilder lcs = new StringBuilder();
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                if(i == 0 || j == 0) {
                    allLengths[i][j] = 0;
                } else if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    allLengths[i][j] = allLengths[i-1][j-1] + 1;
                    if(allLengths[i][j] > max) {
                        max = allLengths[i][j];
                        index = i;
                    }
                } else {
                    allLengths[i][j] = 0;
                }
            }
        }
        for(int i = index-max; i < index; i ++) {
            lcs.append(str1.charAt(i));
        }
        return lcs.toString();
    }
}
