package com.sad2d.engine.entities;

public class Bullet extends Item {

    public Bullet(double x, double y, int width, int height) {
        super(x, y, width, height, Entity.BULLET_EN);
    }

    @Override
    public void update() {

    }

    @Override
    public String getLabel() {
        return EntityTypes.BULLET.getEntityLabel();
    }

    @Override
    public void whenTouch(Player player) {
        super.whenTouch(player);
        int ammo = player.getAmmo();
        player.setAmmo(ammo + 1);
    }
}
