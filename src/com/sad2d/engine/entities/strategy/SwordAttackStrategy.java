package com.sad2d.engine.entities.strategy;

import com.sad2d.engine.Utils;
import com.sad2d.engine.entities.Enemy;
import com.sad2d.engine.entities.Entity;
import com.sad2d.engine.entities.EntityTypes;
import com.sad2d.engine.entities.Player;

public class SwordAttackStrategy implements AttackStrategy {

    @Override
    public void attack(Player player, Enemy enemy) {
        int min = enemy.getStatus().getDamage();
        int max = enemy.getStatus().getMaxDamage();
        int damage = Utils.getInstance().getGenerator().nextInt(max - min + 1) + min;
        player.takeDamage(damage);
    }

}
