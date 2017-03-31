package edu.macalester.comp124.breakout;

import comp124graphics.GraphicsGroup;

import java.awt.*;

/**
 * Created by mhmdm on 3/29/2017.
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

    private int count;

    private Brick[] brickHistory;

    public BrickWall(double y, double width, double height, double padding, int countX, Color[] colors) {
        this.y = y;
        this.width = width;
        this.height = height;
        this.padding = padding;
        this.countX = countX;
        countY = colors.length;
        brickWidth = (width - padding*(countX+1))/countX;
        brickHeight = (height - padding*(countY+1))/countY;
        count = 0;
        brickHistory = new Brick[BRICK_MEMORY];

        for(int i=0; i<countY; i++) {
            createRow(i*brickHeight + (i+1)*padding + y, colors[i]);
        }
    }

    private void createRow(double y, Color color) {
        for(int i=0; i<countX; i++) {
            count++;
            add(new Brick(i*brickWidth + (i+1)*padding,y,brickWidth,brickHeight,color,count,this));
        }
    }

    private void updateBrickHistory(Brick brick) {
        for (int i=1; i<brickHistory.length; i++) {
            brickHistory[i-1] = brickHistory[i];
        }
        brickHistory[brickHistory.length-1] = brick;
    }

    public void deleteBrick(Brick brick) {
        for (Brick lastBrick: brickHistory) {
            if (brick == lastBrick) {
                return;
            }
        }
        updateBrickHistory(brick);
        remove(brick);
        count--;
        System.out.println(count);
    }
}
