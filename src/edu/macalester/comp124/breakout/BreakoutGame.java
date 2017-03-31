package edu.macalester.comp124.breakout;


import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;
import comp124graphics.GraphicsObject;
import comp124graphics.GraphicsText;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Main program for the breakout game.
 *
 */
public class BreakoutGame extends CanvasWindow {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 1000;
    public static final double WALL_WIDTH = 100;

    public static final double BRICK_WALL_Y = 100;
    public static final double BRICK_WALL_HEIGHT = 400;
    public static final double BRICK_WALL_PADDING = 10;
    public static final int BRICK_WALL_XCOUNT = 10;
    public static final Color[] WALL_COLORS= new Color[]{Color.RED,Color.RED,Color.ORANGE,Color.ORANGE,Color.YELLOW,
            Color.YELLOW,Color.GREEN,Color.GREEN,Color.CYAN,Color.CYAN};

    public static final double BALL_DIAMETER = 30;
    public static final double BALL_START_Y = 600;
    public static final double BALL_START_X = CANVAS_WIDTH/2 - BALL_DIAMETER/2;

    public static final double PADDLE_WIDTH = 100;
    public static final double PADDLE_HEIGHT = 10;
    public static final double PADDLE_START_Y = 880;
    public static final double PADDLE_START_X = CANVAS_WIDTH/2 - PADDLE_WIDTH;

    public static final int INITIAL_LIVES = 3;

    public static final int TICK = 30;
    public static final int BALL_STEP = 5;

    private int lives;
    private Timer timer;
    private Random random;
    private BrickWall wall;
    private Listener listener;
    private Ball ball;
    private Paddle paddle;
    public Point lastMouseLocation;

    public BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        lives = INITIAL_LIVES;

        random = new Random();
        buildBoundaries();
        addGameElements();

        GraphicsText text = new GraphicsText("YOU LOSE", 200,300);
        text.setFont(new Font("Calibri",Font.BOLD,80));
        add(text);

    }

    private void buildBoundaries() {
        add(new Barrier(-WALL_WIDTH, 0, WALL_WIDTH, CANVAS_HEIGHT, Color.BLACK));
        add(new Barrier(0, -WALL_WIDTH, CANVAS_WIDTH, WALL_WIDTH, Color.BLACK));
        add(new Barrier(CANVAS_WIDTH, 0, WALL_WIDTH, CANVAS_HEIGHT, Color.BLACK));
        add(new Barrier(0, CANVAS_HEIGHT, CANVAS_WIDTH, WALL_WIDTH,Color.BLACK));

        add(new BottomBarrier(0,PADDLE_START_Y+PADDLE_HEIGHT+BALL_DIAMETER,CANVAS_WIDTH,
                CANVAS_HEIGHT-PADDLE_START_Y-PADDLE_HEIGHT-BALL_DIAMETER,Color.RED,this));
    }

    private void addGameElements() {
        wall = new BrickWall(BRICK_WALL_Y,CANVAS_WIDTH,BRICK_WALL_HEIGHT,
                BRICK_WALL_PADDING,BRICK_WALL_XCOUNT,WALL_COLORS);
        add(wall);

        ball = new Ball(BALL_START_X, BALL_START_Y, BALL_DIAMETER, random.nextBoolean(), BALL_STEP, this);
        add(ball);

        paddle = new Paddle(PADDLE_START_X, PADDLE_START_Y,PADDLE_WIDTH,PADDLE_HEIGHT,Color.BLACK, CANVAS_WIDTH);
        add(paddle);

        listener = new Listener(ball,paddle);
        addMouseMotionListener(listener);

        timer = new Timer(TICK, listener);
        timer.start();
    }

    private void removeGameElements() {
        remove(ball);
        remove(paddle);
        remove(wall);
    }

    public void lose() {
        timer.stop();
        this.pause(1000);

        lives--;
        System.out.println(lives);
        if (lives <= 0) {
            superLose();
        }
        removeGameElements();
        addGameElements();
        timer.restart();
        timer.start();
    }

    private void superLose() {
        System.out.println("YOU SUPER LOSE");
    }

    public GraphicsObject getDeepElementAt(double x, double y) {
        GraphicsObject element = getElementAt(x,y);
        if (element instanceof GraphicsGroup) {
            element = ((GraphicsGroup) element).getElementAt(x,y);
        }
        return element;
    }

    public static void main(String[] args){
        BreakoutGame prog = new BreakoutGame();
    }

}
