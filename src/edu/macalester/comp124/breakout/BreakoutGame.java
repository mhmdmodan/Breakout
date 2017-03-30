package edu.macalester.comp124.breakout;


import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;
import comp124graphics.GraphicsObject;

import javax.swing.*;
import java.awt.*;
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

        add(new Barrier(-WALL_WIDTH, 0, WALL_WIDTH, CANVAS_HEIGHT, Color.BLACK));
        add(new Barrier(0, -WALL_WIDTH, CANVAS_WIDTH, WALL_WIDTH, Color.BLACK));
        add(new Barrier(CANVAS_WIDTH, 0, WALL_WIDTH, CANVAS_HEIGHT, Color.BLACK));
        add(new Barrier(0, CANVAS_HEIGHT, CANVAS_WIDTH, WALL_WIDTH,Color.BLACK));
        add(new BrickWall(80, CANVAS_WIDTH, 400, 10, 10,10,new Color[]{Color.RED,Color.RED,Color.ORANGE,Color.ORANGE,Color.YELLOW,Color.YELLOW,Color.GREEN,Color.GREEN,Color.CYAN,Color.CYAN}));

        ball = new Ball(CANVAS_WIDTH/2, 900, 30, false, BALL_STEP, this);
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

    public GraphicsObject getDeepElementAt(double x, double y) {
        GraphicsObject element = getElementAt(x,y);
        if (element instanceof GraphicsGroup) {
            element = ((GraphicsGroup) element).getElementAt(x,y);
        }
        return element;
    }

}
