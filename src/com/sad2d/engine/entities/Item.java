package com.sad2d.engine.entities;

import java.awt.image.BufferedImage;

public abstract class Item extends Entity {

    public Item(double x, double y, int width, int height, BufferedImage spriteDefault) {
        super(x, y, width, height, spriteDefault);
    }

    protected void whenTouch(Player player) {
        super.setVisible(false);
    };

    @Override
    public int getDepth() {
        return 0;
    }
}
