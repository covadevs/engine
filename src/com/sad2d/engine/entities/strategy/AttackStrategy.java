package com.sad2d.engine.entities.strategy;

import com.sad2d.engine.entities.Enemy;
import com.sad2d.engine.entities.Player;

public interface AttackStrategy {
    public void attack(Player player, Enemy enemy);
}
