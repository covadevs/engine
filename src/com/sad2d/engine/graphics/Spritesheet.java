package com.sad2d.engine.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Spritesheet {

    private static Spritesheet instance;

    private HashMap<String, BufferedImage> spritesheet;

    private Spritesheet() {
        this.spritesheet = new HashMap<>();
    }

    public BufferedImage getSprite(int x, int y, int width, int height, String path) {
        return this.spritesheet.get(path).getSubimage(x, y, width, height);
    }

    public void addSpritesheet(String path) {
        synchronized (Spritesheet.class) {
            BufferedImage bf;
            try {
                bf = ImageIO.read(getClass().getResource(path));
                this.spritesheet.put(path, bf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static Spritesheet getInstance() {
        if(instance == null) {
            synchronized (Spritesheet.class) {
                instance = new Spritesheet();
            }
        }
        return instance;
    }

}
