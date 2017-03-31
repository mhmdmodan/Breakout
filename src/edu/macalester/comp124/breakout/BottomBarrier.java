package edu.macalester.comp124.breakout;

import java.awt.*;

/**
 * Created by mhmdm on 3/30/2017.
 */
public class BottomBarrier extends Barrier{

    BreakoutGame game;

    public BottomBarrier(double x, double y, double width, double height, Color color, BreakoutGame game) {
        super(x, y, width, height, color);
        this.game = game;
    }

    @Override
    public void collided(Ball ball) {
        if (!ball.isAlreadyBounced()) {
            game.lose();
        }
    }
}
