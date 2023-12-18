package SystemOfEquationSolver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Equation {
    private static Float right = 0.0F;
    private static Map<String, Float> symbols;

    public static void main(String[] args) {
        String[] test = {
                "1", "x", "2",
                "x", "y", "3",
                "4", "0", "x", "1"};
        addValues(test, -1);
        System.out.println(right);
        System.out.println(symbols);
        System.out.println(Arrays.toString(getResults()));
    }
    public static void addValues(String[] values, int operation) {
        symbols = new HashMap<>();
        float counter = 100;
        int index = 0;
        int flip = 0;
        if(operation==1) {
            flip = 6;
        } else if(operation==-1) {
            flip = 3;
        }
        for(String x : values) {
            try {
                if(index < flip) {
                    right -= Float.parseFloat(x) * counter;
                } else {
                    right += Float.parseFloat(x) * counter;
                }
            } catch(NumberFormatException e) {
                if(symbols.containsKey(x)) {
                    if(index < flip) {
                        symbols.put(x, symbols.get(x) + counter);
                    } else {
                        symbols.put(x, symbols.get(x) - counter);
                    }
                } else {
                    if(index < flip) {
                        symbols.put(x, counter);
                    } else {
                        symbols.put(x, -counter);
                    }
                }
            }
            if(index == 5) {
                counter = 1000;
            } else if(index == 2) {
                counter = 100;
            } else {
                counter = counter / 10;
            }
            index++;
        }
    }
    public static String[] getResults() {
        String result = "";
        String[] results = new String[symbols.size()];
        int index = 0;
        for(Map.Entry<String, Float> entry : symbols.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            result = key + " = ";
            for(Map.Entry<String, Float> entryNested : symbols.entrySet()) {
                String keyNested = entryNested.getKey();
                Float valueNested = entryNested.getValue();
                if(!Objects.equals(keyNested, key)) {
                    if(valueNested != 0) {
                        result += -(valueNested / value) + keyNested + " + ";
                    }
                }
            }
            result += + right/value;
            results[index] = result;
            result = "";
            index++;
        }
        return results;
    }
    public static void refresh() {
        right = 0.0F;
        symbols.clear();
    }
}
