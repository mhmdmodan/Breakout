package edu.macalester.comp124.breakout;


import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;
import comp124graphics.GraphicsObject;
import comp124graphics.GraphicsText;

import javax.swing.Timer;
import java.awt.*;
import java.util.*;

/**
 * Main program for the breakout game.
 *
 */
public class BreakoutGame extends CanvasWindow {

    //Parameters for the canvas and outer wall boundaries
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 1000;
    public static final double WALL_WIDTH = 100;

    //Parameters for the brick wall
    public static final double BRICK_WALL_Y = 100;
    public static final double BRICK_WALL_HEIGHT = 400;
    public static final double BRICK_WALL_PADDING = 10;
    public static final int BRICK_WALL_XCOUNT = 10;
    public static final Color[] WALL_COLORS= new Color[]{Color.RED,Color.RED,Color.ORANGE,Color.ORANGE,Color.YELLOW,
            Color.YELLOW,Color.GREEN,Color.GREEN,Color.CYAN,Color.CYAN};

    //Parameters for the ball
    public static final double BALL_DIAMETER = 30;
    public static final double BALL_START_Y = 600;
    public static final double BALL_START_X = CANVAS_WIDTH/2 - BALL_DIAMETER/2;
    public static final int INITIAL_DELAY = 3000;

    //Paddle parameters
    public static final double PADDLE_WIDTH = 100;
    public static final double PADDLE_HEIGHT = 10;
    public static final double PADDLE_START_Y = 880;
    public static final double PADDLE_START_X = CANVAS_WIDTH/2 - PADDLE_WIDTH/2;

    //General parameters for text in the game
    public static final String FONT = "Helvetica";
    public static final int CENTER_MESSAGE_SIZE = 80;
    public static final long MESSAGE_TIME = 3000;

    //Starting game parameters
    public static final int INITIAL_LIVES = 3;
    public static final int DELAY_AFTER_LOSS = 1000;

    //Game speed parameters
    public static final int TICK = 8;
    public static final int BALL_STEP = 5;

    private int lives;
    private java.util.Timer pauser;
    private BottomBarrier bottom;
    private GraphicsText livesText;
    private Timer timer;
    private Random random;
    private BrickWall wall;
    private Listener listener;
    private Ball ball;
    private Paddle paddle;
    public Point lastMouseLocation;

    /**
     * Sets up the game
     */
    public BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        lives = INITIAL_LIVES;

        pauser = new java.util.Timer();
        random = new Random();
        buildBoundaries();
        addGameElements();
    }

    /**
     * Builds the outer walls for the ball to bounce on, and the bottom barrier
     */
    private void buildBoundaries() {
        add(new Barrier(-WALL_WIDTH, 0, WALL_WIDTH, CANVAS_HEIGHT, Color.BLACK));
        add(new Barrier(0, -WALL_WIDTH, CANVAS_WIDTH, WALL_WIDTH, Color.BLACK));
        add(new Barrier(CANVAS_WIDTH, 0, WALL_WIDTH, CANVAS_HEIGHT, Color.BLACK));
        add(new Barrier(0, CANVAS_HEIGHT, CANVAS_WIDTH, WALL_WIDTH,Color.BLACK));

        bottom = new BottomBarrier(0,PADDLE_START_Y+PADDLE_HEIGHT+BALL_DIAMETER,CANVAS_WIDTH,
                CANVAS_HEIGHT-PADDLE_START_Y-PADDLE_HEIGHT-BALL_DIAMETER,Color.WHITE,this);
        add(bottom);
    }

    /**
     * Adds the ball, livesText, and new listener. Made for losing a life.
     */
    private void softAddElements() {
        ball = new Ball(BALL_START_X, BALL_START_Y, BALL_DIAMETER, random.nextBoolean(), BALL_STEP, this);
        add(ball);

        livesText = new GraphicsText("LIVES: "+lives, 0, 0);
        livesText.setFont(new Font(FONT,Font.BOLD,40));
        livesText.setPosition(CANVAS_WIDTH - livesText.getWidth()-20, livesText.getHeight());
        add(livesText);

        listener = new Listener(ball,paddle);
        addMouseMotionListener(listener);

        //Timer for moving the ball
        timer = new Timer(TICK, listener);
        timer.setInitialDelay(INITIAL_DELAY);
        timer.start();
    }

    /**
     * Adds the ball, livesText, new wall, new paddle, and new listener
     * Made for hard resets like winning, losing, or initializing
     */
    private void addGameElements() {
        wall = new BrickWall(BRICK_WALL_Y,CANVAS_WIDTH,BRICK_WALL_HEIGHT,
                BRICK_WALL_PADDING,BRICK_WALL_XCOUNT,WALL_COLORS,this);
        add(wall);

        paddle = new Paddle(PADDLE_START_X, PADDLE_START_Y,PADDLE_WIDTH,PADDLE_HEIGHT,Color.BLACK, CANVAS_WIDTH);
        add(paddle);

        softAddElements();
    }

    /**
     * Removes the ball, livesText, and new listener. Made for losing a life.
     */
    private void softRemoveElements() {
        remove(ball);
        remove(livesText);
    }

    /**
     * Removes the ball, livesText, new wall, new paddle, and new listener
     * Made for hard resets like winning, losing, or initializing
     */
    private void removeGameElements() {
        remove(paddle);
        remove(wall);
        softRemoveElements();
    }

    /**
     * Reset made for losing a life
     */
    private void softResetGame() {
        softRemoveElements();
        softAddElements();
    }

    /**
     * Reset made for winning or losing
     */
    private void resetGame() {
        lives = INITIAL_LIVES;
        removeGameElements();
        addGameElements();
    }

    /**
     * Stops the timer, decrement lives, check if game lost, soft resets the game
     */
    public void lose() {
        timer.stop();

        lives--;
        if (lives <= 0) {
            superLose();
            return;
        }

        pauser.schedule(new TimerTask() {
            @Override
            public void run() {
                softResetGame();
            }
        },DELAY_AFTER_LOSS);
    }

    /**
     * Hard reset the entire game and display losing message
     */
    private void superLose() {
        messageAndReset("YOU LOSE");
    }

    /**
     * Hard reset the entire game and display a winning message
     */
    public void win() {
        timer.stop();
        messageAndReset("YOU WIN");
    }

    /**
     * Display a message at the center and hard reset the game
     * @param string - message to be displayed
     */
    private void messageAndReset(String string) {
        GraphicsText message = new GraphicsText(string,0,0);
        message.setFont(new Font(FONT,Font.BOLD,CENTER_MESSAGE_SIZE));
        message.setPosition(CANVAS_WIDTH/2 - message.getBounds().getWidth()/2,
                CANVAS_HEIGHT/2 + livesText.getBounds().getHeight()/2);
        add(message);

        pauser.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(message);
                resetGame();
            }
        },MESSAGE_TIME);
    }

    /**
     * Returns the GraphicsObject at the specified point.
     * If a group is returned, find the object within the group corresponding to the point
     *
     * @param x - x of point
     * @param y - y of point
     * @return - GraphicsObject at the point, not a group.
     */
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
