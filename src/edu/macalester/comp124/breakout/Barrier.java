package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

import java.awt.*;

/**
 * A general class that defines a rectangular barrier upon which the ball can bounce on
 */
public class Barrier extends Rectangle implements Bounceable {

    /**
     * Sets up the barrier
     * @param x - x position
     * @param y - y position
     * @param width - width
     * @param height - height
     * @param color - barrier fill color
     */
    public Barrier(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        setStroked(false);
        setFillColor(color);
        setFilled(true);
    }

    /**
     * When the ball collides, bounce it
     * @param ball - ball which collided
     */
    @Override
    public void collided(Ball ball) {
        ball.bounce(Bounceable.collisionDirection(this, ball));
    }

    /**
     * Gets the x midpoint
     * @return - x midpoint
     */
    @Override
    public double getXMid() {
        return getX() + getWidth()/2;
    }

    /**
     * Gets the y midpoint
     * @return - y midpoint
     */
    @Override
    public double getYMid() {
        return getY() + getHeight()/2;
    }

    @Override
    public String toString() {
        return "Barrier{x - " +getX()+ " y - " +getY()+ " width - " +getWidth()+ " height - " +getHeight()+"}";
    }
}