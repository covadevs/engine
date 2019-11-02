package com.sad2d.engine.world.tile;

import java.awt.image.BufferedImage;

public class WallTile extends Tile {
    public WallTile(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }

    @Override
    public String getLabel() {
        return TileConstants.WALL_LABEL;
    }
}
