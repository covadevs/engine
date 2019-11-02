package com.sad2d.engine.core;

import com.sad2d.engine.graphics.Renderer;
import com.sad2d.engine.graphics.Updater;

import java.util.Comparator;

public abstract class GameComponent implements Updater, Renderer {
    protected double x;
    protected double y;
    private int ticks;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public abstract String getLabel();

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }
}
