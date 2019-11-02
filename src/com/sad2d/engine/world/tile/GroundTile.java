package com.sad2d.engine.world.tile;

import java.awt.image.BufferedImage;

public class GroundTile extends Tile {

    public GroundTile(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }

    @Override
    public String getLabel() {
        return TileConstants.GROUND_LABEL;
    }
}
