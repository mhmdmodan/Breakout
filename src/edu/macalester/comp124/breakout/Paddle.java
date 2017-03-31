package edu.macalester.comp124.breakout;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Defines the paddle
 */
public class Paddle extends Barrier {
    private double halfWidth;
    private double paddleLimit;

    /**
     * Sets up the paddle
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     * @param paddleLimit - maximum x value the paddle can reach
     */
    public Paddle(double x, double y, double width, double height, Color color, double paddleLimit) {
        super(x, y, width, height, color);
        this.paddleLimit = paddleLimit;
        halfWidth = getWidth()/2;
    }

    /**
     * Sets the center of the paddle at the given x value,
     * ensures the window limit is not exceeded
     * @param x - x value to set at
     */
    public void setCenterX(double x) {
        if (x >= paddleLimit - halfWidth) {
            setPosition(paddleLimit - getWidth(), getY());
        } else if (x <= halfWidth) {
            setPosition(0, getY());
        } else {
            setPosition(x - getWidth()/2, getY());
        }
    }

    @Override
    public String toString() {
        return "Paddle{" +
                "halfWidth=" + halfWidth +
                ", paddleLimit=" + paddleLimit +
                '}';
    }
}
