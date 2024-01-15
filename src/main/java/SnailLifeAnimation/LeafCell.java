package SnailLifeAnimation;

import java.awt.*;
import java.util.Random;

public class LeafCell {
    private Color colorShade;
    private int foodAmount;
    private final Color[] colorShades = {
            Color.decode("#ffffff"), Color.decode("#dbfddb"), Color.decode("#afffae"),
            Color.decode("#7cfa7a"), Color.decode("#55ea53"), Color.decode("#3bd439"),
            Color.decode("#27b924"), Color.decode("#1ba719"), Color.decode("#129111"),
            Color.decode("#117910"), Color.decode("#0c630b")
    };

    private boolean isOccupied = false;

    public LeafCell() {
        generateRandomFoodAmount();
        setColorBasedOnFoodAmount();
    }

    private void generateRandomFoodAmount() {
        Random rn = new Random();
        this.foodAmount = rn.nextInt(11);
    }

    private void setColorBasedOnFoodAmount() {
        this.colorShade = colorShades[foodAmount];
    }

    public void incrementLeafCell() {
        if(this.foodAmount < 10) {
            this.foodAmount++;
            setColorBasedOnFoodAmount();
        }
    }

    public void setColorShade(Color color) {
        this.colorShade = color;
    }

    public Color getColorShade() {
        return colorShade;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;

    }
}