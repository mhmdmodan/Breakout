package edu.macalester.comp124.breakout;

import java.awt.*;

/**
 * The bottom rectangular barrier that loses the game if hit.
 */
public class BottomBarrier extends Barrier{
    BreakoutGame game;

    /**
     * Sets up the barrier
     *
     * @param x - x position
     * @param y - y position
     * @param width - width
     * @param height - height
     * @param color - barrier fill color
     * @param game - BreakoutGame to which the barrier belongs
     */
    public BottomBarrier(double x, double y, double width, double height, Color color, BreakoutGame game) {
        super(x, y, width, height, color);
        this.game = game;
    }

    /**
     * If the ball collides, lose the game
     * @param ball - ball which collided
     */
    @Override
    public void collided(Ball ball) {
        //First check if the ball has already hit something.
        //Ensures that if two points on the ball intersect
        //the bottom barrier, then game.lose() isn't called twice.
        if (!ball.isAlreadyBounced()) {
            game.lose();
        }
    }

    @Override
    public String toString() {
        return "BottomBarrier{x - " +getX()+ " y - " +getY()+ " width - " +getWidth()+ " height - " +getHeight()+"}";
    }
}
