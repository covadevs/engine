package com.sad2d.engine.graphics.ui;

import com.sad2d.engine.core.UIComponent;

import java.awt.*;

public abstract class UI extends UIComponent {

    private int width;
    private int height;
    private double len;

    private int x; private int y;

    public UI(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getLen() {
        return len;
    }

    public void setLen(double len) {
        this.len = len;
    }

    protected void drawCenteredString(Graphics g, Rectangle rectangle, String str) {
        FontMetrics metrics = g.getFontMetrics();
        int x = ((int)rectangle.getX()) + ((int)(this.len) - metrics.stringWidth(str)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = ((int)rectangle.getY()) + (((int)rectangle.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        // Draw the String
        g.drawString(str, x, y);
    }
}
