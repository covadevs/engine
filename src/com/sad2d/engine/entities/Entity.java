package com.sad2d.engine.entities;

import com.sad2d.engine.Constants;
import com.sad2d.engine.core.GameComponent;
import com.sad2d.engine.entities.strategy.AttackStrategy;
import com.sad2d.engine.entities.strategy.EquipmentStrategy;
import com.sad2d.engine.graphics.Spritesheet;
import com.sad2d.engine.world.camera.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract  class Entity extends GameComponent implements Comparable<Entity> {

    public static BufferedImage GUN_EN = Spritesheet.getInstance().getSprite(64, 16, 16, 16, Constants.PATH_SPRITESHEET);
    public static BufferedImage BULLET_EN = Spritesheet.getInstance().getSprite(64, 0, 16, 16, Constants.PATH_SPRITESHEET);
    public static BufferedImage LIFEPACK_EN = Spritesheet.getInstance().getSprite(80, 0, 16, 16, Constants.PATH_SPRITESHEET);
    public static BufferedImage ENEMY_EN = Spritesheet.getInstance().getSprite(128, 0, 16, 16, Constants.PATH_SPRITESHEET);

    protected int width;
    protected int height;
    protected double speed;
    private boolean visible;

    protected int xMask, yMask, wMask, hMask;
    protected Rectangle entityRectangle;

    protected BufferedImage spriteDefault;
    protected BufferedImage[] sprites;
    protected BufferedImage[] spritesRight;
    protected BufferedImage[] spritesLeft;

    protected StatusEntity status;
    protected AttackStrategy attackStrategy;
    protected EquipmentStrategy equipmentStrategy;

    public Entity(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.xMask = 0;
        this.yMask = 0;
        this.wMask = width;
        this.hMask = height;
        this.entityRectangle = new Rectangle((int)(this.x+this.xMask), (int)(this.y+this.yMask), this.wMask, this.hMask);
        this.visible = true;
        setTicks(0);
    }

    public void setMask(int xMask, int yMask, int wMask, int hMask) {
        this.xMask = xMask;
        this.yMask = yMask;
        this.wMask = wMask;
        this.hMask = hMask;
        this.entityRectangle.setBounds((int)(this.x+this.xMask), (int)(this.y+this.yMask), this.wMask, this.hMask);
    }

    public Entity(double x, double y, int width, int height, BufferedImage spriteDefault) {
        this(x, y, width, height);
        this.spriteDefault = spriteDefault;
        this.visible = true;
    }

    public Entity() {

    }

    @Override
    public void update() {
        this.entityRectangle.setBounds((int)(this.x+this.xMask), (int)(this.y+this.yMask), this.wMask, this.hMask);
    }

    public double getX() {
        return x;
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

    public BufferedImage getSpriteDefault() {
        return spriteDefault;
    }

    public void setSpriteDefault(BufferedImage spriteDefault) {
        this.spriteDefault = spriteDefault;
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Rectangle getEntityRectangle() {
        return entityRectangle;
    }

    public void setEntityRectangle(Rectangle entityRectangle) {
        this.entityRectangle = entityRectangle;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public abstract int getDepth();

    @Override
    public int compareTo(Entity entity) {
        return Integer.compare(getDepth(), entity.getDepth());
    }

    @Override
    public void render(Graphics g) {
        int x = (int) this.getX() - Camera.x;
        int y = (int) this.getY() - Camera.y;
        g.drawImage(this.spriteDefault, x, y, null);
        g.setColor(Color.BLUE);
        g.drawRect((int)this.entityRectangle.getX()-Camera.x, (int)this.entityRectangle.getY()-Camera.y, (int)this.entityRectangle.getWidth(), (int)this.entityRectangle.getHeight());
    }

    protected boolean isColliding(Entity e1, Entity e2) {
        return e1.getEntityRectangle().intersects(e2.getEntityRectangle());
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
