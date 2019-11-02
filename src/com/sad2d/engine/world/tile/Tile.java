package com.sad2d.engine.world.tile;

import com.sad2d.engine.Constants;
import com.sad2d.engine.core.GameComponent;
import com.sad2d.engine.graphics.Spritesheet;
import com.sad2d.engine.world.camera.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile extends GameComponent {

    public static BufferedImage TILE_GROUND = Spritesheet
            .getInstance()
            .getSprite(96, 0, 16, 16, Constants.PATH_SPRITESHEET);

    public static BufferedImage TILE_WALL = Spritesheet
            .getInstance()
            .getSprite(112, 0, 16, 16, Constants.PATH_SPRITESHEET);

    public static BufferedImage TILE_WEAPON = Spritesheet
            .getInstance()
            .getSprite(64, 16, 16, 16, Constants.PATH_SPRITESHEET);

    protected BufferedImage sprite;

    public Tile(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    @Override
    public void render(Graphics g) {
        int x = (int) this.x - Camera.x;
        int y = (int) this.y - Camera.y;
        g.drawImage(this.sprite, x, y, null);
    }

    @Override
    public void update() {

    }
}
