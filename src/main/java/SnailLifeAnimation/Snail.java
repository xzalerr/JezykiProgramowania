package SnailLifeAnimation;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Snail extends Thread{
    private int y, x, velocity, time;
    private Leaf leaf;
    public Snail(int velocity, int time, Leaf leaf) {
        this.velocity = velocity;
        this.time = time;
        this.leaf = leaf;
        generatePosition();
    }
    @Override
    public void run() {

            while (!Thread.interrupted()) {
                move();
                //System.out.println("test: " + Thread.currentThread().getId() + "x: " + getX() + ", y: " + getY());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

    }
    public void generatePosition() {
        int newX, newY;
        Random rn = new Random();
        do {
            newY = rn.nextInt(leaf.getHeight());
            newX = rn.nextInt(leaf.getWidth());
        } while (leaf.isOccupiedAt(newY, newX));
        this.y = newY;
        this.x = newX;
        leaf.setOccupiedAt(newY, newX, true);
    }

    private synchronized void move() {
            int[] possibleMoves = getValidMoves();
            if (possibleMoves.length > 0) {
                int randomMoveIndex = new Random().nextInt(possibleMoves.length);
                int selectedMove = possibleMoves[randomMoveIndex];

                switch (selectedMove) {
                    case 0: // Move up
                        moveUp();
                        break;
                    case 1: // Move down
                        moveDown();
                        break;
                    case 2: // Move left
                        moveLeft();
                        break;
                    case 3: // Move right
                        moveRight();
                        break;
                    case 4: // Move diagonally up-left
                        moveUpLeft();
                        break;
                    case 5: // Move diagonally up-right
                        moveUpRight();
                        break;
                    case 6: // Move diagonally down-left
                        moveDownLeft();
                        break;
                    case 7: // Move diagonally down-right
                        moveDownRight();
                        break;
                }

                // Konsumowanie liścia
                consumeLeaf();
            } else {
                try {
                    Thread.sleep(time); // Uśpienie na określony czas, gdy brak możliwości ruchu
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
    }

    private void consumeLeaf() {
        int foodAmount = leaf.getFoodAmountAt(y, x);
        int newFoodAmount = Math.max(0, foodAmount - velocity);
        leaf.setFoodAmountAt(y, x, newFoodAmount);
    }

    private int[] getValidMoves() {
        int[] validMoves = new int[8];
        int count = 0;

        if (y > 0 && leaf.getFoodAmountAt(y - 1, x) > 0 && !leaf.isOccupiedAt(y - 1, x)) {
            validMoves[count++] = 0; // Move up
        }
        if (y < leaf.getHeight() - 1 && leaf.getFoodAmountAt(y + 1, x) > 0 && !leaf.isOccupiedAt(y + 1, x)) {
            validMoves[count++] = 1; // Move down
        }
        if (x > 0 && leaf.getFoodAmountAt(y, x - 1) > 0 && !leaf.isOccupiedAt(y, x - 1)) {
            validMoves[count++] = 2; // Move left
        }
        if (x < leaf.getWidth() - 1 && leaf.getFoodAmountAt(y, x + 1) > 0 && !leaf.isOccupiedAt(y, x + 1)) {
            validMoves[count++] = 3; // Move right
        }
        if (y > 0 && x > 0 && leaf.getFoodAmountAt(y - 1, x - 1) > 0 && !leaf.isOccupiedAt(y - 1, x - 1)) {
            validMoves[count++] = 4; // Move diagonally up-left
        }
        if (y > 0 && x < leaf.getWidth() - 1 && leaf.getFoodAmountAt(y - 1, x + 1) > 0 && !leaf.isOccupiedAt(y - 1, x + 1)) {
            validMoves[count++] = 5; // Move diagonally up-right
        }
        if (y < leaf.getHeight() - 1 && x > 0 && leaf.getFoodAmountAt(y + 1, x - 1) > 0 && !leaf.isOccupiedAt(y + 1, x - 1)) {
            validMoves[count++] = 6; // Move diagonally down-left
        }
        if (y < leaf.getHeight() - 1 && x < leaf.getWidth() - 1 && leaf.getFoodAmountAt(y + 1, x + 1) > 0 && !leaf.isOccupiedAt(y + 1, x + 1)) {
            validMoves[count++] = 7; // Move diagonally down-right
        }

        // Trim the array to the actual number of valid moves
        return Arrays.copyOf(validMoves, count);
    }


    private void moveUp() {
        move(-1, 0);
    }

    private void moveDown() {
        move(1, 0);
    }

    private void moveLeft() {
        move(0, -1);
    }

    private void moveRight() {
        move(0, 1);
    }

    private void moveUpLeft() {
        move(-1, -1);
    }

    private void moveUpRight() {
        move(-1, 1);
    }

    private void moveDownLeft() {
        move(1, -1);
    }

    private void moveDownRight() {
        move(1, 1);
    }

    private void move(int deltaY, int deltaX) {
        leaf.setOccupiedAt(y, x, false);
        y += deltaY;
        x += deltaX;
        leaf.setOccupiedAt(y, x, true);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}