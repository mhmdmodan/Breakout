package edu.macalester.comp124.breakout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by mhmdm on 3/30/2017.
 */
public class Listener implements MouseMotionListener, ActionListener {

    Ball ball;
    Paddle paddle;

    public Listener(Ball ball, Paddle paddle) {
        this.ball = ball;
        this.paddle = paddle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.moveBall();
        ball.checkCollision();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddle.setCenterX(e.getX());
    }
}
