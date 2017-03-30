package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

import java.awt.*;

/**
 * Created by mhmdm on 3/23/2017.
 */
public class Barrier extends Rectangle implements Bounceable {
    public Barrier(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        setStroked(false);
        setFillColor(color);
        setFilled(true);
    }

    @Override
    public void collided(Ball ball) {
        ball.bounce(Bounceable.collisionDirection(this, ball));
    }

    @Override
    public double getXMid() {
        return getX() + getWidth()/2;
    }

    @Override
    public double getYMid() {
        return getY() + getHeight()/2;
    }
}