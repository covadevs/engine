package com.sad2d.engine.entities;

import com.sad2d.engine.Constants;
import com.sad2d.engine.exceptions.PlayerAlreadyExistsException;
import com.sad2d.engine.graphics.Spritesheet;
import com.sad2d.engine.main.Game;
import com.sad2d.engine.world.World;
import com.sad2d.engine.world.camera.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;

public class Player extends Entity {

    private static Player playerInstance;

    private boolean right, left, up, down;
    private boolean moved, stopped;
    private Direction lastDir = Direction.NEUTRAL;
    private Direction currentDir = Direction.NEUTRAL;

    private int frames, maxFrames, indexAnimation;
    private int maxDamagedFrames;
    private int ammo;

    private Weapon weapon;

    private BufferedImage playerDamaged;
    private boolean damaged;

    private Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        super.speed = 1.5d;
        this.right = false;
        this.left = false;
        this.up = false;
        this.down = false;
        this.moved = false;
        this.frames = 0;
        this.maxFrames = 5;
        this.indexAnimation = 0;
        super.spritesLeft = new BufferedImage[4];
        super.spritesRight = new BufferedImage[4];
        this.ammo = 0;
        this.playerDamaged = Spritesheet.getInstance().getSprite(0,32,16,16, Constants.PATH_SPRITESHEET);
        this.damaged = false;

        super.setMask(4, 1, 7, 15);

        super.status = new StatusEntity.StatusBuilder()
                .life(100)
                .maxlife(100)
                .baseLifeRegen(1)
                .lifeRegenMultiplier(1)
                .damage(0)
                .maxDamage(2)
                .builder();

        for(int i = 0; i < 4; i++) {
            super.spritesRight[i] = Spritesheet.getInstance().getSprite((i*16), 0, 16, 16, Constants.PATH_SPRITESHEET);
        }

        for(int i = 3; i >= 0; i--) {
            super.spritesLeft[i] = Spritesheet.getInstance().getSprite((i*16), 16, 16, 16, Constants.PATH_SPRITESHEET);
        }
    }

    @Override
    public void update() {
        setTicks(getTicks() + 1);
        checkStatus();
        super.update();
        this.moved = false;
        moveRight();

        moveLeft();

        moveUp();

        moveDown();

        if(this.moved) {
            animate();
        } else {
            this.currentDir = Direction.NEUTRAL;
            setLastDir();
        }

        checkCollisionWithItem();

        if(this.isDamaged()) {
            this.maxDamagedFrames++;
            if(this.maxDamagedFrames == 10) {
                this.maxDamagedFrames = 0;
                this.setDamaged(false);
            }
        }

        Camera.x = Camera.clamp((int)this.x - (Game.WIDTH/2), 0, World.WIDTH * 16 - Game.WIDTH);
        Camera.y = Camera.clamp((int)this.y - (Game.HEIGHT/2), 0, World.HEIGHT * 16 - Game.HEIGHT);
    }

    private void checkStatus() {
        if(getTicks() == 60) {
            int life = super.getStatus().getLife();
            int maxLife = super.getStatus().getMaxLife();
            if(life < maxLife) {
                super.getStatus().setLife(life+1);
            }

            setTicks(0);
        }
    }

    private void checkCollisionWithItem() {
        int size = EntityList.getInstance().getEntities().size();
        for(int i = 0; i < size; i++) {
            Entity e = EntityList.getInstance().getEntities().get(i);
            if(e.isVisible()) {
                if (e.getLabel().equals(EntityTypes.LIFEPACK.getEntityLabel())) {
                    if (isColliding(this, e)) {
                        Item lifepack = (Lifepack) e;
                        lifepack.whenTouch(this);
                    }
                }

                if(e.getLabel().equals(EntityTypes.BULLET.getEntityLabel())) {
                    if(isColliding(this, e)) {
                        Item bullet = (Bullet) e;
                        bullet.whenTouch(this);
                    }
                }

                if(e.getLabel().equals(EntityTypes.WEAPON.getEntityLabel())) {
                    if(isColliding(this, e)) {
                        Weapon weapon = (Weapon) e;
                        weapon.whenTouch(this);
                    }
                }
            }
        }
    }

    private void setLastDir() {
        if(this.lastDir == Direction.LEFT) {
            this.indexAnimation = 3;
        } else {
            this.indexAnimation = 0;
        }
    }

    private void animate() {
        this.frames++;
        if(this.frames == this.maxFrames) {
            this.frames = 0;

            this.indexAnimation++;
            if(this.indexAnimation >= this.spritesRight.length) {
                this.indexAnimation = 0;
            }
        }
    }

    private void moveDown() {
        if (this.down && World.isFree((int)this.x, (int)(this.y+this.speed))) {
            this.lastDir = Direction.DOWN;
            this.moved = true;
            this.y += this.speed;
        }
    }

    private void moveUp() {
        if(this.up && World.isFree((int)this.x, (int)(this.y-this.speed))) {
            this.lastDir = Direction.UP;
            this.moved = true;
            this.y -= this.speed;
        }
    }

    private void moveLeft() {
        if (this.left && World.isFree((int)(this.x - this.speed), (int)this.y)) {
            this.lastDir = Direction.LEFT;
            this.moved = true;
            this.x -= this.speed;
        }
    }

    private void moveRight() {
        if(this.right && World.isFree((int)(this.x + this.speed), (int)this.y)) {
            this.lastDir = Direction.RIGHT;
            this.moved = true;
            this.x += this.speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        if(!this.moved) {
            if(this.lastDir == Direction.RIGHT) {
                this.spriteDefault = super.spritesRight[this.indexAnimation];
            } else if(this.lastDir == Direction.LEFT) {
                this.spriteDefault = super.spritesLeft[this.indexAnimation];
            }
        }

        if(this.right) {
            this.spriteDefault = super.spritesRight[this.indexAnimation];
        } else if(this.left) {
            this.spriteDefault = super.spritesLeft[this.indexAnimation];
        }

        if(this.damaged) {
            this.spriteDefault = this.playerDamaged;
        }

        int x = (int) this.x - Camera.x;
        int y = (int) this.y - Camera.y;
        g.drawImage(super.spriteDefault, x, y, null);
        g.drawRect((int)this.entityRectangle.getX()-Camera.x, (int)this.entityRectangle.getY()-Camera.y, (int)this.entityRectangle.getWidth(), (int)this.entityRectangle.getHeight());

        if(this.weapon != null) {
            if(this.right) {
                g.drawImage(Gun.GUN_RIGHT, (int) getX() - Camera.x + 8, (int) getY() - Camera.y, null);
            } else if(this.left) {
                g.drawImage(Gun.GUN_LEFT, (int) getX() - Camera.x - 8, (int) getY() - Camera.y, null);
            }
        }
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public static void createPlayer(int x, int y, int width, int height, BufferedImage spriteDefault) throws PlayerAlreadyExistsException {
        if(playerInstance != null) {
            throw new PlayerAlreadyExistsException("Player ja foi criado.");
        }

        synchronized (Player.class) {
            playerInstance = new Player(x, y, width, height, spriteDefault);
        }
    }



    public static Player getInstance() {
        return playerInstance;
    }

    public void takeDamage(int damage) {
        System.out.println("Tomou: "+damage);
        this.setDamaged(true);
        int life = super.status.getLife();
        super.status.setLife(life - damage);

        if(super.status.getLife() <= 0) {
            EntityList.getInstance().resetAll();
            Spritesheet.getInstance().addSpritesheet(Constants.PATH_SPRITESHEET);
            this.reset();
            Player.createPlayer(0,0,16,16, Spritesheet.getInstance().getSprite(0,0,16,16,Constants.PATH_SPRITESHEET));
            EntityList.getInstance().addEntity(Player.getInstance());
            World.getInstance().resetWorld();
            Collections.sort(EntityList.getInstance().getEntities());
            return;
        }
    }

    public void reset() {
        playerInstance = null;
    }
    @Override
    public String getLabel() {
        return EntityTypes.PLAYER.getEntityLabel();
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public int getDepth() {
        return 2;
    }
}
