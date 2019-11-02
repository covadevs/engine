package com.sad2d.engine.entities;

public class Lifepack extends Item {

    public Lifepack(double x, double y, int width, int height) {
        super(x, y, width, height, Entity.LIFEPACK_EN);
    }

    @Override
    public void update() {

    }

    @Override
    public String getLabel() {
        return EntityTypes.LIFEPACK.getEntityLabel();
    }

    @Override
    public void whenTouch(Player player) {
        super.whenTouch(player);
        int life = player.getStatus().getLife();
        life+=10;

        if(life >= player.getStatus().getMaxLife()) {
            life = player.getStatus().getMaxLife();
        }

        player.getStatus().setLife(life);
    }
}
