package edu.macalester.comp124.breakout;

import comp124graphics.Ellipse;
import comp124graphics.GraphicsObject;

import java.awt.*;

/**
 * Created by mhmdm on 3/23/2017.
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

        bounceables[0] = game.getElementAt(getX(), getY());
        bounceables[1] = game.getElementAt(getX(), getY() + size);
        bounceables[2] = game.getElementAt(getX() + size, getY());
        bounceables[3] = game.getElementAt(getX() + size, getY() + size);

        for (int i = 0; i < bounceables.length; i++) {
            if (bounceables[i] instanceof Bounceable) {
                Bounceable bounced = (Bounceable) bounceables[i];
                bounced.collided(game, this);
                return;
            }
        }
    }

    public void bounce(int[] direction) {
        currentDX = currentDX*direction[0];
        currentDY = currentDY*direction[1];
    }

    public void moveBall() {
        move(currentDX, currentDY);
    }
}
