package com.sad2d.engine.main;

import com.sad2d.engine.Constants;
import com.sad2d.engine.GameCanvas;
import com.sad2d.engine.entities.Entity;
import com.sad2d.engine.entities.EntityList;
import com.sad2d.engine.entities.Player;
import com.sad2d.engine.graphics.Spritesheet;
import com.sad2d.engine.graphics.ui.BulletUIComponent;
import com.sad2d.engine.graphics.ui.LifebarUIComponent;
import com.sad2d.engine.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Collections;

public class Game extends GameCanvas implements Runnable {

    public final static int WIDTH = 240;
    public final static int HEIGHT = 160;
    public final static int SCALE = 3;

    private Boolean running;
    private Thread thread;
    private BufferedImage image;
    private int frames;
    private World map;
    private LifebarUIComponent lifebarUIComponent;
    private BulletUIComponent bulletUIComponent;
    private Font font;

    public Game() {
        this.font = new Font("Arial", Font.PLAIN, 10);
        this.setPreferredSize(new Dimension(Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE));
        initFrame();
        this.image = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        this.frames = 0;
        Spritesheet.getInstance().addSpritesheet(Constants.PATH_SPRITESHEET);
        Player.createPlayer(0,0,16,16, Spritesheet.getInstance().getSprite(0,0,16,16,Constants.PATH_SPRITESHEET));
        EntityList.getInstance().addEntity(Player.getInstance());
        this.lifebarUIComponent = new LifebarUIComponent(200, 8, 10, 10);
        this.bulletUIComponent = new BulletUIComponent(100,20, 10, Game.HEIGHT- 10);
        this.addKeyListener(this);

        World.createWorld("/map.png");
        this.map = World.getInstance();
        Collections.sort(EntityList.getInstance().getEntities());
    }

    private void initFrame() {
        JFrame jFrame = new JFrame("Zelda");
        jFrame.add(this);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public synchronized void start() {
        this.running = Boolean.TRUE;
        this.thread = new Thread(this);
        this.thread.start();
    }

    private synchronized void stop() {
        this.running = Boolean.FALSE;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        for(Entity e : EntityList.getInstance().getEntities()) {
            if(e.isVisible()) {
                e.update();
            }
        }

        this.map.update();
        this.lifebarUIComponent.update();
        this.bulletUIComponent.update();

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = this.image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.WIDTH, Game.HEIGHT);

        //Renderizacao

        this.map.render(g);
        for(Entity e : EntityList.getInstance().getEntities()) {
            if(e.isVisible()) {
                e.render(g);
            }
        }
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(this.image,0,0, Game.WIDTH* Game.SCALE, Game.HEIGHT* Game.SCALE, null);
        g.setFont(this.font);
        this.lifebarUIComponent.render(g);
        this.bulletUIComponent.render(g);
        bs.show();
    }

    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double amountOfUpdates = 60.0d;
        double ns = 1000000000d / amountOfUpdates;
        double delta = 0.0d;

        this.frames = 0;

        long currentTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while(this.running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                update();
                render();
                this.frames++;
                delta--;
            }

            elapsedTime = System.currentTimeMillis();
            if((elapsedTime - currentTime) >= 1000L) {
                System.out.println("FPS: "+this.frames);
                this.frames = 0;
                currentTime+=1000;
            }
        }

        stop();
    }
}

