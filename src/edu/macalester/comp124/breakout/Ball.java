package edu.macalester.comp124.breakout;

import comp124graphics.Ellipse;
import comp124graphics.GraphicsObject;

import java.awt.*;

/**
 * A class defining a ball
 */
public class Ball extends Ellipse {
    private double size;
    private BreakoutGame game;
    private double currentDX;
    private double currentDY;

    private boolean alreadyBounced;

    //8 points just outside the ball every 45 degrees
    private double[] pointsX;
    private double[] pointsY;

    /**
     * Sets up the ball and shoots it in the specified direction
     * @param x
     * @param y
     * @param size
     * @param left - true: move ball left first, false: move right
     * @param step - step size for moving the ball
     * @param game - game which the ball belogns to
     */
    public Ball(double x, double y, double size, boolean left, double step, BreakoutGame game) {
        super(x, y, size, size);
        this.size = size;
        this.game = game;
        this.setFilled(true);
        this.setFillColor(Color.BLACK);

        double quarter = (Math.sqrt(2)/2) * getRadius();
        pointsX = new double[] {1+2,quarter+2,0,-quarter-2,-1-2,-quarter-2,0,quarter+2};
        pointsY = new double[] {0,quarter+2,1+2,quarter+2,0,-quarter-2,-1-2,-quarter-2};

        if (left) {
            currentDX = -step;
            currentDY = step;
        } else {
            currentDX = step;
            currentDY = step;
        }
    }

    /**
     * Checks all 8 points to see if there was a collision
     */
    public void checkCollision() {
        //An array of 8 possibly intersected objects for the 8 points
        GraphicsObject[] bounceables = new GraphicsObject[8];

        for (int i=0; i<bounceables.length; i++) {
            bounceables[i] = game.getDeepElementAt(getXMid()+pointsX[i], getYMid()+pointsY[i]);
        }

        //If the interected object was a bounceable, invoke its collided method
        for (int i = 0; i < bounceables.length; i++) {
            if (bounceables[i] instanceof Bounceable) {
                Bounceable bounced = (Bounceable) bounceables[i];
                bounced.collided(this);
                alreadyBounced = true;
            }
        }
        alreadyBounced = false;
    }

    /**
     * Change direction given which side the ball bounced on
     * @param direction - direction hit from
     */
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

    /**
     * Move the ball
     */
    public void moveBall() {
        move(currentDX, currentDY);
    }

    /**
     * Returns the ball's radius
     * @return - radius
     */
    public double getRadius() {
        return size/2;
    }

    public double getXMid() {
        return getX() + getRadius();
    }

    public double getYMid() {
        return getY() + getRadius();
    }

    /**
     * Checks if the ball has already bounced on something
     * @return - alreadyBounced
     */
    public boolean isAlreadyBounced() {
        return alreadyBounced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Ball ball = (Ball) o;

        if (Double.compare(ball.size, size) != 0) return false;
        if (Double.compare(ball.currentDX, currentDX) != 0) return false;
        if (Double.compare(ball.currentDY, currentDY) != 0) return false;
        if (alreadyBounced != ball.alreadyBounced) return false;
        return game.equals(ball.game);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "size=" + size +
                ", currentDX=" + currentDX +
                ", currentDY=" + currentDY +
                ", alreadyBounced=" + alreadyBounced +
                '}';
    }
}
