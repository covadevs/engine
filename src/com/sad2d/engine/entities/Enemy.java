package com.sad2d.engine.entities;

import com.sad2d.engine.Constants;
import com.sad2d.engine.Utils;
import com.sad2d.engine.entities.strategy.SwordAttackStrategy;
import com.sad2d.engine.entities.strategy.SwordEquipment;
import com.sad2d.engine.graphics.Spritesheet;
import com.sad2d.engine.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity {

    private int frames, maxFrames, indexAnimation;

    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height, Entity.ENEMY_EN);
        super.setMask(8,8,10,10);
        super.speed = 0.5d;
        this.frames = 0;
        this.maxFrames = 20;
        this.indexAnimation = 0;
        super.sprites = new BufferedImage[2];
        super.sprites[0] = super.spriteDefault;
        super.sprites[1] = Spritesheet.getInstance().getSprite(144,0,16,16, Constants.PATH_SPRITESHEET);
        super.status = new StatusEntity.StatusBuilder()
                .life(20)
                .maxlife(20)
                .chanceCritic(50.0d)
                .damage(0)
                .maxDamage(1)
                .builder();

        this.attackStrategy = new SwordAttackStrategy();
        this.equipmentStrategy = new SwordEquipment();
        this.equipmentStrategy.equip(this);
    }

    @Override
    public void update() {
        super.update();
        if(!isCollidingWithPlayer()) {
            moveEnemy();
        } else {
            tryToAttackPlayer();
        }

        this.frames++;
        if(this.frames == this.maxFrames) {
            this.frames = 0;

            this.indexAnimation++;
            if(this.indexAnimation >= this.sprites.length) {
                this.indexAnimation = 0;
            }
        }
    }

    private void tryToAttackPlayer() {
        if(Utils.getInstance().doIt(super.status.getChanceCritic())) {
            if(Player.getInstance().getStatus().getLife() <= 0) {
                System.exit(1);
            }
            this.attackStrategy.attack(Player.getInstance(), this);
        }
    }

    private void moveEnemy() {
        if ((int) this.getX() < (int) Player.getInstance().getX() &&
                World.isFree((int) (super.x + super.speed), (int) super.y) &&
                !isColliding((int) (super.x + super.speed), (int) super.y)) {
            super.x += super.speed;
        } else if ((int) this.getX() > (int) Player.getInstance().getX() &&
                World.isFree((int) (super.x - super.speed), (int) super.y) &&
                !isColliding((int) (super.x - super.speed), (int) super.y)) {
            super.x -= super.speed;
        }

        if ((int) this.getY() < (int) Player.getInstance().getY() &&
                World.isFree((int) super.x, (int) (super.y + super.speed)) &&
                !isColliding((int) super.x, (int) (super.y + super.speed))) {
            super.y += super.speed;
        } else if ((int) this.getY() > (int) Player.getInstance().getY() &&
                World.isFree((int) super.x, (int) (super.y - super.speed)) &&
                !isColliding((int) super.x, (int) (super.y - super.speed))) {
            super.y -= super.speed;
        }
    }

    private boolean isCollidingWithPlayer() {
        Rectangle player = new Rectangle((int)Player.getInstance().getX(), (int)Player.getInstance().getY(), 16,16);
        Rectangle thisEnemy = new Rectangle((int)this.getX(), (int)this.getY(), 16,16);

        return thisEnemy.intersects(player);
    }

    private boolean isColliding(int xNext, int yNext) {
        Rectangle currentEnemy = new Rectangle(xNext + xMask, yNext+yMask, wMask, hMask);

        for(int i = 0; i < EntityList.getInstance().getEnemies().size(); i++) {
            Enemy e = EntityList.getInstance().getEnemies().get(i);
            if(e.equals(this)) {
                continue;
            }

            Rectangle enemy = new Rectangle((int)e.getX() + xMask, (int)e.getY()+yMask, wMask, hMask);
            if(currentEnemy.intersects(enemy)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void render(Graphics g) {
        super.spriteDefault = super.sprites[this.indexAnimation];
        super.render(g);

    }

    public void takeDamage(int damage) {
        int life = super.status.getLife();
        super.status.setLife(life - damage);

        if(life <= 0) {
            super.setVisible(false);
        }
    }

    @Override
    public String getLabel() {
        return EntityTypes.ENEMY.getEntityLabel();
    }

    @Override
    public int getDepth() {
        return 1;
    }
}
