package PieChartGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListPanel extends JPanel {
    private ArrayList<Integer> list;
    private JList<Integer> numbers;
    private DefaultListModel<Integer> listModel;

    public ListPanel() {
        list = new ArrayList<>();
        listModel = new DefaultListModel<>();
        numbers = new JList<>(listModel);

        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(numbers);
        add(scrollPane, BorderLayout.CENTER);
    }
    public void addNumber(String x) {
        try {
            Integer a = Integer.parseInt(x);
            list.add(a);
            refreshNumbers();
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Podana wartość musi być liczbą typu Integer.");
        }
    }
    public void deleteNumber(int i) {
            try {
                list.remove(i);
                refreshNumbers();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Musisz zaznaczyćliczbę, którą chcesz usunąć.");
            }
    }
    public void editNumber(String x, int i) {
        try {
            Integer a = Integer.parseInt(x);
            list.set(i, a);
            refreshNumbers();
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Podana wartość musi być liczbą typu Integer.");
        }
    }
    public void refreshNumbers() {
        listModel.removeAllElements();
        for(Integer i : list) {
            listModel.addElement(i);
        }
    }
    public ArrayList<Integer> getList() {
        return list;
    }
    public JList<Integer> getNumbers() {
        return numbers;
    }
}
