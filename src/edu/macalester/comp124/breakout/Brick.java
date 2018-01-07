package edu.macalester.comp124.breakout;

import java.awt.*;

/**
 * Defines bricks
 */
public class Brick extends Barrier {
    BrickWall wall;
    int id;

    /**
     * Sets up the brick
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     * @param id
     * @param wall - BrickWall which the brick belongs to
     */
    public Brick(double x, double y, double width, double height, Color color, int id, BrickWall wall) {
        super(x, y, width, height, color);
        this.wall = wall;
        this.id = id;
    }

    /**
     * Bounce the ball and delete self
     * @param ball - ball which collided
     */
    @Override
    public void collided(Ball ball) {
        ball.bounce(Bounceable.collisionDirection(this, ball));
        //Ensures that two bricks aren't deleted at once, and deletion isn't called
        //multiple times on teh same brick
        if (!ball.isAlreadyBounced()) {
            wall.deleteBrick(this);
        }
    }

    @Override
    public String toString() {
        return "Brick{" +
                "wall=" + wall +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Brick brick = (Brick) o;

        if (id != brick.id) return false;
        return wall.equals(brick.wall);
    }
}
