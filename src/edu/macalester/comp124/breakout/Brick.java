package edu.macalester.comp124.breakout;

import java.awt.*;

/**
 * Created by mhmdm on 3/29/2017.
 */
public class Brick extends Barrier {
    BrickWall wall;
    int id;

    public Brick(double x, double y, double width, double height, Color color, int id, BrickWall wall) {
        super(x, y, width, height, color);
        this.wall = wall;
        this.id = id;
    }

    @Override
    public void collided(Ball ball) {
        ball.bounce(Bounceable.collisionDirection(this, ball));
        if (!ball.isAlreadyBounced()) {
            wall.deleteBrick(this);
        }
    }

    @Override
    public String toString() {
        return ""+id;
    }
}
