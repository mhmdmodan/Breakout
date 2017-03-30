package edu.macalester.comp124.breakout;

import comp124graphics.GraphicsGroup;

import java.awt.*;

/**
 * Created by mhmdm on 3/29/2017.
 */
public class BrickWall extends GraphicsGroup {

    private final double X = 0;
    private double y;
    private double width;
    private double height;
    private double padding;
    private int countX;
    private int countY;
    private double brickWidth;
    private double brickHeight;

    private int count;

    public BrickWall(double y, double width, double height, double padding, int countX, int countY, Color[] colors) {
        this.y = y;
        this.width = width;
        this.height = height;
        this.padding = padding;
        this.countX = countX;
        this.countY = countY;
        brickWidth = (width - padding*(countX+1))/countX;
        brickHeight = (height - padding*(countY+1))/countY;
        count = countX*countY;

        for(int i=0; i<countY; i++) {
            createRow(i*brickHeight + (i+1)*padding + y, colors[i]);
        }
    }

    private void createRow(double y, Color color) {
        for(int i=0; i<countX; i++) {
            add(new Brick(i*brickWidth + (i+1)*padding,y,brickWidth,brickHeight,color,this));
        }
    }

    public void decrementCount() {
        count--;
    }
}
