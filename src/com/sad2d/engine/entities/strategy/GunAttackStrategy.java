package com.sad2d.engine.entities.strategy;

import com.sad2d.engine.entities.Enemy;
import com.sad2d.engine.entities.Player;

public class GunAttackStrategy implements AttackStrategy {
    @Override
    public void attack(Player player, Enemy enemy) {
        enemy.takeDamage(player.getStatus().getDamage());
    }
}
