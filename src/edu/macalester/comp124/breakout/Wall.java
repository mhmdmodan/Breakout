package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

/**
 * Created by mhmdm on 3/23/2017.
 */
public class Wall extends Rectangle implements Bounceable {
    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
}
