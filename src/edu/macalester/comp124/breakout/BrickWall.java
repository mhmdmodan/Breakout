package edu.macalester.comp124.breakout;

import comp124graphics.GraphicsGroup;

import java.awt.*;
import java.util.Arrays;

/**
 * Defines a Brick Wall of bricks
 */
public class BrickWall extends GraphicsGroup {

    private final double X = 0;
    private final int BRICK_MEMORY = 4;

    private double y;
    private double width;
    private double height;
    private double padding;
    private int countX;
    private int countY;
    private double brickWidth;
    private double brickHeight;
    private BreakoutGame game;

    private int count;

    private Brick[] brickHistory;

    /**
     * Sets up the brick wall, drawing all the bricks and assigning IDs
     * @param y
     * @param width
     * @param height
     * @param padding - space between bricks
     * @param countX - horizontal count of bricks
     * @param colors - an array, each element defines a new row of bricks with that color
     * @param game - game which the wall belongs to
     */
    public BrickWall(double y, double width, double height, double padding,
                     int countX, Color[] colors, BreakoutGame game) {
        this.y = y;
        this.width = width;
        this.height = height;
        this.padding = padding;
        this.countX = countX;
        this.game = game;

        countY = colors.length;
        brickWidth = (width - padding*(countX+1))/countX;
        brickHeight = (height - padding*(countY+1))/countY;
        count = 0;
        brickHistory = new Brick[BRICK_MEMORY];

        for(int i=0; i<countY; i++) {
            createRow(i*brickHeight + (i+1)*padding + y, colors[i]);
        }
    }

    /**
     * Creates a row of bricks
     * @param y - y pos of where to create the row
     * @param color - color to give it
     */
    private void createRow(double y, Color color) {
        for(int i=0; i<countX; i++) {
            count++;
            add(new Brick(i*brickWidth + (i+1)*padding,y,brickWidth,brickHeight,color,count,this));
        }
    }

    /**
     * Add a brick to the brick history and remove the oldest brick
     * @param brick - brick to be added
     */
    private void updateBrickHistory(Brick brick) {
        for (int i=1; i<brickHistory.length; i++) {
            brickHistory[i-1] = brickHistory[i];
        }
        brickHistory[brickHistory.length-1] = brick;
    }

    /**
     * Remove a brick. Ensures that a brick isn't removed twice
     * @param brick - brick to be removed
     */
    public void deleteBrick(Brick brick) {
        //Ensures this brick isn't already removed
        for (Brick lastBrick: brickHistory) {
            if (brick == lastBrick) {
                return;
            }
        }
        updateBrickHistory(brick);
        remove(brick);
        count--;
        if (count <= 0) {
            game.win();
        }
    }

    @Override
    public String toString() {
        return "BrickWall{" +
                "count=" + count +
                ", brickHistory=" + Arrays.toString(brickHistory) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BrickWall brickWall = (BrickWall) o;

        if (Double.compare(brickWall.padding, padding) != 0) return false;
        if (countX != brickWall.countX) return false;
        if (countY != brickWall.countY) return false;
        if (Double.compare(brickWall.brickWidth, brickWidth) != 0) return false;
        if (Double.compare(brickWall.brickHeight, brickHeight) != 0) return false;
        if (count != brickWall.count) return false;

        return Arrays.equals(brickHistory, brickWall.brickHistory);
    }
}
