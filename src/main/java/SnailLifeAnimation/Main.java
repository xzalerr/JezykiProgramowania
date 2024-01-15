package SnailLifeAnimation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Snail> snails;
    private static Leaf leaf;
    private static AnimationPanel animationPanel;
    public static void main(String[] args) {
        snails = new ArrayList<>();
        leaf = new Leaf(10, 10);
        animationPanel = new AnimationPanel(leaf, snails);
        generateSnails(10, 5, 2000);

        JFrame frame = new JFrame("Snail Life Animation");
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(leaf.getWidth()*40, (leaf.getHeight()*40)+28));
        frame.add(animationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ResourceRefresher refresher = new ResourceRefresher(leaf, animationPanel);
        GraphicsRefresher gr = new GraphicsRefresher(animationPanel);
        gr.start();
        refresher.start();
        for(Snail s : snails) {
            s.start();
        }
    }
    public static void generateSnails(int n, int velocity, int time) {
        for(int i = 0; i < n; i++) {
            snails.add(new Snail(velocity, time, leaf));
        }
    }
}