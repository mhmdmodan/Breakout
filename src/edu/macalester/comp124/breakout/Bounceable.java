package edu.macalester.comp124.breakout;

import comp124graphics.GraphicsGroup;
import comp124graphics.Rectangle;

import java.awt.geom.Point2D;

import static edu.macalester.comp124.breakout.Direction.*;

/**
 * An interface outlining graphics object that can be bounced on
 */
public interface Bounceable {

    /**
     * What to do when collided with
     * @param ball - ball that collided
     */
    public abstract void collided(Ball ball);

    public abstract double getXMid();

    public abstract double getY();

    public abstract double getX();

    public abstract double getYMid();

    public abstract double getWidth();

    public abstract double getHeight();

    /**
     * Determines which side the ball hit a Bounceable from
     * @param bounceable - bounceable which was hit
     * @param ball - ball which collided
     * @return - Direction enum
     */
    public static Direction collisionDirection(Bounceable bounceable, Ball ball) {
        double xDist = Math.abs(ball.getXMid() - bounceable.getXMid());
        if (bounceable.getWidth() / 2 <= xDist &&
                xDist <= (bounceable.getWidth() / 2 + ball.getRadius())) {
            if (bounceable.getXMid() < ball.getXMid()) {
                //these set position terms unclip the ball, so it can never get stuck inside something
                //and jiggle around an edge
                ball.setPosition(bounceable.getX() + bounceable.getWidth(), ball.getY());
                return RIGHT;
            } else {
                ball.setPosition(bounceable.getX() - ball.getWidth(), ball.getY());
                return LEFT;
            }
        } else {
            if (bounceable.getYMid() < ball.getYMid()) {
                ball.setPosition(ball.getX(), bounceable.getY() + bounceable.getHeight());
                return BOTTOM;
            } else {
                ball.setPosition(ball.getX(), bounceable.getY() - ball.getHeight());
                return TOP;
            }
        }
    }
}