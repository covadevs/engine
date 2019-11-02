package com.sad2d.engine.entities.strategy;

import com.sad2d.engine.entities.Entity;

public class GunEquipStrategy implements EquipmentStrategy {
    @Override
    public void equip(Entity entity) {
        entity.getStatus().setDamage(4);
    }
}
