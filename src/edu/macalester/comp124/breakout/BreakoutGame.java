package edu.macalester.comp124.breakout;


import comp124graphics.CanvasWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main program for the breakout game.
 *
 */
public class BreakoutGame extends CanvasWindow implements ActionListener {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 1000;
    private static final double WALL_WIDTH = 100;
    private static final int TICK = 10;
    private static final int BALL_STEP = 5;

    private Ball ball;

    public BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);

        add(new Wall(-WALL_WIDTH, 0, WALL_WIDTH, CANVAS_HEIGHT));
        add(new Wall(0, -WALL_WIDTH, CANVAS_WIDTH, WALL_WIDTH));
        add(new Wall(CANVAS_WIDTH, 0, WALL_WIDTH, CANVAS_HEIGHT));
        add(new Wall(0, CANVAS_HEIGHT, CANVAS_WIDTH, WALL_WIDTH));

        ball = new Ball(CANVAS_WIDTH/2, 900, 50, false, BALL_STEP, this);
        add(ball);

        Timer timer = new Timer(TICK, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.moveBall();
        ball.checkCollision();
    }
    public static void main(String[] args){
        BreakoutGame prog = new BreakoutGame();
    }

}
