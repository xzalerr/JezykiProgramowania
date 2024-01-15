package SnailLifeAnimation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnimationPanel extends JPanel {
    private Leaf leaf;
    private ArrayList<Snail> snails;
    public AnimationPanel(Leaf leaf, ArrayList<Snail> snails) {
        this.leaf = leaf;
        this.snails = snails;
        setPreferredSize(new Dimension(leaf.getWidth()*40, leaf.getHeight()*40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x;
        int y = 0;
        for (int i = 0; i < leaf.getHeight(); i++) {
            x = 0;
            for (int j = 0; j < leaf.getWidth(); j++) {
                g.setColor(leaf.getColorShadeAt(i, j));
                g.fillRect(x, y, 40, 40);
                x += 40;
            }
            y += 40;
        }
        for (Snail snail : snails) {
            g.setColor(Color.RED);
            int xPos = 10 + 40 * snail.getX();
            int yPos = 10 + 40 * snail.getY();
            g.fillOval(xPos, yPos, 20, 20);
        }

    }
    public void refresh() {
        repaint();
    }
}