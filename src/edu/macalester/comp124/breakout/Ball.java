package edu.macalester.comp124.breakout;

import comp124graphics.Ellipse;
import comp124graphics.GraphicsObject;

/**
 * Created by mhmdm on 3/23/2017.
 */
public class Ball extends Ellipse {
    private double size;
    private BreakoutGame game;
    private double currentDX;
    private double currentDY;

    public Ball(double x, double y, double size, boolean left, int step, BreakoutGame game) {
        super(x, y, size, size);
        this.size = size;
        this.game = game;

        if (left) {
            currentDX = -step;
            currentDY = step;
            move(currentDX, currentDY);
        } else {
            currentDX = step;
            currentDY = step;
            move(currentDX, currentDY);
        }
    }

    public void checkCollision() {
        GraphicsObject[] bounceables = new GraphicsObject[4];

        bounceables[0] = game.getElementAt(getX(), getY());
        bounceables[1] = game.getElementAt(getX(), getY() + size);
        bounceables[2] = game.getElementAt(getX() + size, getY());
        bounceables[3] = game.getElementAt(getX() + size, getY() + size);

        for (int i = 0; i < bounceables.length; i++) {
            if (bounceables[i] instanceof Bounceable) {
                move(-currentDX, -currentDY);
                Bounceable bounced = (Bounceable) bounceables[i];
                bounced.collided();
            }
        }

    }
}
