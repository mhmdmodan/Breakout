package edu.macalester.comp124.breakout;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by mhmdm on 3/30/2017.
 */
public class Paddle extends Barrier {

    double halfWidth;
    double paddleLimit;

    public Paddle(double x, double y, double width, double height, Color color, double paddleLimit) {
        super(x, y, width, height, color);
        this.paddleLimit = paddleLimit;
        halfWidth = getWidth()/2;
    }

    public void setCenterX(double x) {
        if (x >= paddleLimit - halfWidth) {
            setPosition(paddleLimit - getWidth(), getY());
        } else if (x <= halfWidth) {
            setPosition(0, getY());
        } else {
            setPosition(x - getWidth()/2, getY());
        }
    }
}
