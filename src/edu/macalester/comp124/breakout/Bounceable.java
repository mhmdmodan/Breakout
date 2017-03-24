package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

import java.awt.geom.Point2D;

/**
 * Created by mhmdm on 3/23/2017.
 */
public interface Bounceable {
    public abstract void collided(BreakoutGame game, Ball ball);

    public static int[] collisionDirection(Rectangle rectangle, Ball ball) {
        if (ball.getY() >= rectangle.getY() + rectangle.getHeight()) { //below
            ball.setPosition(ball.getX(), rectangle.getY() + rectangle.getHeight()); //prevent clipping
            return new int[] {1, -1};
        } else if (ball.getY() + ball.getHeight() <= rectangle.getY()) { //above
            ball.setPosition(ball.getX(), rectangle.getY() - ball.getHeight());
            return new int[] {1, -1};
        } else if (ball.getX() >= rectangle.getX() + rectangle.getWidth()) {
            ball.setPosition(rectangle.getX() + rectangle.getWidth() + 5, ball.getY());
            return new int[] {-1, 1};
        } else {
            ball.setPosition(rectangle.getX() - ball.getWidth() - 5, ball.getY());
            return new int[] {-1, 1};
        }
    }

}