package edu.macalester.comp124.breakout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Listener for mouse movements and timer for moving the ball
 */
public class Listener implements MouseMotionListener, ActionListener {

    private Ball ball;
    private Paddle paddle;
    private double lastX;

    public Listener(Ball ball, Paddle paddle) {
        this.ball = ball;
        this.paddle = paddle;
        lastX = 450;
    }

    /**
     * Moves the ball
     * @param e - Timer action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ball.moveBall();
        paddle.setCurrentDX(paddle.getX()-lastX);
        lastX = paddle.getX();
        ball.checkCollision();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Set the paddle to the mouse x position
     * @param e - MouseEvent with mouse position
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        paddle.setCenterX(e.getX());
    }
}
