package edu.macalester.comp124.breakout;

import java.awt.*;

/**
 * Created by mhmdm on 3/29/2017.
 */
public class Brick extends Barrier {
    BrickWall wall;

    public Brick(double x, double y, double width, double height, Color color, BrickWall wall) {
        super(x, y, width, height, color);
        this.wall = wall;
    }

    @Override
    public void collided(Ball ball) {
        ball.bounce(Bounceable.collisionDirection(this, ball));
        wall.remove(this);
    }
}
