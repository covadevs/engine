package com.sad2d.engine.entities;

import com.sad2d.engine.Constants;
import com.sad2d.engine.entities.strategy.GunAttackStrategy;
import com.sad2d.engine.entities.strategy.GunEquipStrategy;
import com.sad2d.engine.graphics.Spritesheet;

import java.awt.image.BufferedImage;

public class Gun extends Weapon {

    public static BufferedImage GUN_LEFT = Spritesheet.getInstance().getSprite(80, 16, 16, 16, Constants.PATH_SPRITESHEET);
    public static BufferedImage GUN_RIGHT = Spritesheet.getInstance().getSprite(96, 16, 16, 16, Constants.PATH_SPRITESHEET);

    public Gun(double x, double y, int width, int height) {
        super(x, y, width, height, Entity.GUN_EN, new GunAttackStrategy(), new GunEquipStrategy());
    }

    @Override
    public void update() {

    }

    @Override
    public String getLabel() {
        return EntityTypes.WEAPON.getEntityLabel();
    }

    @Override
    protected void whenTouch(Player player) {
        super.whenTouch(player);
        player.setWeapon(this);
        getEquipmentStrategy().equip(player);
    }
}
