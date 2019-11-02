package com.sad2d.engine.entities;

import com.sad2d.engine.entities.strategy.AttackStrategy;
import com.sad2d.engine.entities.strategy.EquipmentStrategy;

import java.awt.image.BufferedImage;

public abstract class Weapon extends Item {

    private AttackStrategy attackStrategy;
    private EquipmentStrategy equipmentStrategy;


    public Weapon(double x, double y, int width, int height, BufferedImage spriteDefault, AttackStrategy attackStrategy, EquipmentStrategy equipmentStrategy) {
        super(x, y, width, height, spriteDefault);
        this.attackStrategy = attackStrategy;
        this.equipmentStrategy = equipmentStrategy;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public EquipmentStrategy getEquipmentStrategy() {
        return equipmentStrategy;
    }

    public void setEquipmentStrategy(EquipmentStrategy equipmentStrategy) {
        this.equipmentStrategy = equipmentStrategy;
    }
}
