package edu.macalester.comp124.breakout;

import comp124graphics.Ellipse;
import comp124graphics.GraphicsObject;

import java.awt.*;

/**
 * A class defining
 */
public class Ball extends Ellipse {
    private double size;
    private BreakoutGame game;
    private double currentDX;
    private double currentDY;
    private double angle;

    public Ball(double x, double y, double size, boolean left, double step, BreakoutGame game) {
        super(x, y, size, size);
        this.size = size;
        this.game = game;
        this.setFilled(true);
        this.setFillColor(Color.BLACK);

        if (left) {
            currentDX = -step;
            currentDY = step;
        } else {
            currentDX = step;
            currentDY = step;
        }
    }

    public void checkCollision() {
        GraphicsObject[] bounceables = new GraphicsObject[4];

        bounceables[0] = game.getDeepElementAt(getX(), getY());
        bounceables[1] = game.getDeepElementAt(getX(), getY() + size);
        bounceables[2] = game.getDeepElementAt(getX() + size, getY());
        bounceables[3] = game.getDeepElementAt(getX() + size, getY() + size);

        for (int i = 0; i < bounceables.length; i++) {
            if (bounceables[i] instanceof Bounceable) {
                Bounceable bounced = (Bounceable) bounceables[i];
                bounced.collided(this);
            }
        }
    }

    public void bounce(Direction direction) {
        switch (direction) {
            case RIGHT:
                currentDX = currentDX < 0 ? -currentDX : currentDX;
                break;
            case LEFT:
                currentDX = currentDX < 0 ? currentDX : -currentDX;
                break;
            case BOTTOM:
                currentDY = currentDY < 0 ? -currentDY : currentDY;
                break;
            case TOP:
                currentDY = currentDY < 0 ? currentDY : -currentDY;
        }
    }

    public void moveBall() {
        move(currentDX, currentDY);
    }

    public double getRadius() {
        return size/2;
    }

    public double getXMid() {
        return getX() + getRadius();
    }

    public double getYMid() {
        return getY() + getRadius();
    }
}
