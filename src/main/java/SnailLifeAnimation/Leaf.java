package SnailLifeAnimation;

import java.awt.*;

public class Leaf {
    private int width;
    private int height;
    private LeafCell[][] cells;
    public Leaf(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new LeafCell[height][width];
        generateLeafCells();
    }

    public void generateLeafCells() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                cells[i][j] = new LeafCell();
            }
        }
    }
    public void incrementLeafCells() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                cells[i][j].incrementLeafCell();
            }
        }
    }


    public Color getColorShadeAt(int y, int x) {
        return cells[y][x].getColorShade();
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFoodAmountAt(int y, int x) {
        return cells[y][x].getFoodAmount();
    }

    public void setFoodAmountAt(int y, int x, int velocity) {
        cells[y][x].setFoodAmount(velocity);
    }

    public boolean isOccupiedAt(int y, int x) {
        return cells[y][x].isOccupied();
    }

    public void setOccupiedAt(int y, int x, boolean b) {
        cells[y][x].setOccupied(b);
    }

}