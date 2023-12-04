package BarChartGenerator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Values extends JPanel{
    private ArrayList<Integer> values;
    public Values() {
        values = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            values.add(0);
        }
    }
    public void addNumber(String x, int i) {
        if(x.isEmpty()) {
            values.set(i, 0);
        } else {
            try {
                x = x.trim();
                Integer a = Integer.parseInt(x);
                values.set(i, a);
            } catch (NumberFormatException ignore) {
                values.set(i, 0);
            }
        }
    }

    public void deleteNumber(String x, int index) throws NieprawidlowaWartoscException {
        if(x.isEmpty() && values.get(index) == 0) {
            values.set(index, 0);
        } else {
            try {
                x = x.trim();
                Integer a = Integer.parseInt(x);
                int occurencies = Collections.frequency(values, a);
                if (values.indexOf(a) == -1 || (occurencies == 1 && values.indexOf(a) != index)) {
                    JOptionPane.showMessageDialog(this, "Nie ma takiej wartości w tym miejscu.");
                    throw new NieprawidlowaWartoscException();
                } else {
                    values.set(index, 0);
                }
            } catch (NumberFormatException ignore) {

            }
        }
    }

    public ArrayList<Integer> getList() {
        return values;
    }
}
