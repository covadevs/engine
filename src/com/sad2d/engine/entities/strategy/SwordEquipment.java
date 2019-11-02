package com.sad2d.engine.entities.strategy;

import com.sad2d.engine.entities.Entity;
import com.sad2d.engine.entities.strategy.EquipmentStrategy;

public class SwordEquipment implements EquipmentStrategy {
    @Override
    public void equip(Entity entity) {
        entity.getStatus().setDamage(1);
        entity.getStatus().setMaxDamage(2);
    }
}
