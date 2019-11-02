package com.sad2d.engine.world;

import com.sad2d.engine.ColorConstants;
import com.sad2d.engine.core.GameComponent;
import com.sad2d.engine.entities.*;
import com.sad2d.engine.main.Game;
import com.sad2d.engine.world.camera.Camera;
import com.sad2d.engine.world.tile.GroundTile;
import com.sad2d.engine.world.tile.Tile;
import com.sad2d.engine.world.tile.TileConstants;
import com.sad2d.engine.world.tile.WallTile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World extends GameComponent {

    private static World world;

    private BufferedImage map;

    private static Tile[] tiles;
    private int width, height;
    private Font font = new Font("Arial", Font.PLAIN, 8);
    public static int WIDTH, HEIGHT;

    private World(String path) {
        try {
            this.map = ImageIO.read(getClass().getResource(path));
            this.width = this.map.getWidth();
            this.height = this.map.getHeight();
            WIDTH = this.map.getWidth();
            HEIGHT = this.map.getHeight();
            makeWorld();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeWorld() {
        int[] pixels = new int[this.width * this.height];
        tiles = new Tile[this.width * this.height];
        this.map.getRGB(0, 0, this.width, this.height, pixels, 0, this.width);
        for(int xx = 0; xx < this.width; xx++) {
            for(int yy = 0; yy < this.height; yy++) {
                int currentIndex = xx + (yy * this.width);
                int currentPixel = pixels[currentIndex];
                int x = xx * 16;
                int y = yy * 16;
                tiles[currentIndex] = new GroundTile(x, y, Tile.TILE_GROUND);
                switch (currentPixel) {
                    case ColorConstants.ENEMY:
                        Enemy e = new Enemy(x, y, 16, 16);
                        EntityList.getInstance().addEntity(e);
                        EntityList.getInstance().addEnemy(e);
                        break;
                    case ColorConstants.PLAYER:
                        Player.getInstance().setX(x);
                        Player.getInstance().setY(y);
                        break;
                    case ColorConstants.WALL:
                        tiles[currentIndex] = new WallTile(x, y, Tile.TILE_WALL);
                        break;
                    case ColorConstants.WEAPON:
                        EntityList.getInstance().addEntity(new Gun(x, y, 16, 16));
                        break;
                    case ColorConstants.BULLET:
                        Bullet bullet = new Bullet(x, y, 16, 16);
                        bullet.setMask(8,8,2,6);
                        EntityList.getInstance().addEntity(bullet);
                        break;
                    case ColorConstants.LIFE:
                        Lifepack lifepack = new Lifepack(x, y, 16, 16);
                        lifepack.setMask(5,8, 6, 6);
                        EntityList.getInstance().addEntity(lifepack);
                        break;
                    default:
                        tiles[currentIndex] = new GroundTile(x, y, Tile.TILE_GROUND);

                }
            }
        }
    }

    public static boolean isFree(int xNext, int yNext) {

        //Left
        int x1 = (xNext) / TileConstants.TILE_SIZE;
        int y1 = (yNext) / TileConstants.TILE_SIZE;

        //Right
        int x2 = (xNext + TileConstants.TILE_SIZE) / TileConstants.TILE_SIZE;
        int y2 = yNext / TileConstants.TILE_SIZE;

        //Up
        int x3 = xNext / TileConstants.TILE_SIZE;
        int y3 = (yNext+TileConstants.TILE_SIZE) / TileConstants.TILE_SIZE;

        //Down
        int x4 = (xNext + TileConstants.TILE_SIZE) / TileConstants.TILE_SIZE;
        int y4 = (yNext + TileConstants.TILE_SIZE) / TileConstants.TILE_SIZE;

        boolean tileLeftIsWall, tileRightIsWall, tileUpIsWall, tileDownIsWall, isWall;

        tileLeftIsWall = tiles[x1 + (y1 * WIDTH)].getLabel().equals(TileConstants.WALL_LABEL);
        tileRightIsWall = tiles[x2 + (y2 * WIDTH)].getLabel().equals(TileConstants.WALL_LABEL);
        tileUpIsWall = tiles[x3 + (y3 * WIDTH)].getLabel().equals(TileConstants.WALL_LABEL);
        tileDownIsWall = tiles[x4 + (y4 * WIDTH)].getLabel().equals(TileConstants.WALL_LABEL);

        isWall = tileLeftIsWall || tileRightIsWall || tileUpIsWall || tileDownIsWall;

        return !isWall;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setFont(this.font);
        int xstart = Camera.x >> 4;
        int ystart = Camera.y >> 4;

        int xfinal = xstart + (Game.WIDTH >> 4);
        int yfinal = ystart + (Game.HEIGHT >> 4);
        int c = 0;
        for(int xx = xstart; xx <= xfinal; xx++) {
            for(int yy = ystart; yy <= yfinal; yy++) {
                if(xx < 0 || yy < 0 || xx >= this.width || yy >= this.height)
                    continue;
                int pos = xx + (yy * this.width);
                Tile tile = tiles[pos];
                tile.render(g);
            }
        }
    }

    public static void createWorld(String path) {
        if(world == null) {
            synchronized (World.class) {
                world = new World(path);
            }
        }
    }


    public static World getInstance() {
        return world;
    }

    public void resetWorld() {
        makeWorld();
    }

    @Override
    public String getLabel() {
        return "WORLD";
    }
}
