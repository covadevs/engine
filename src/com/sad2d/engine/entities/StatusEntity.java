package com.sad2d.engine.entities;

public class StatusEntity {
    private double chanceCritic;
    private int damage;
    private int maxDamage;
    private int life;
    private int maxLife;
    private int baseLifeRegen;
    private int lifeRegenMultiplier;

    public StatusEntity(StatusBuilder builder) {
        this.chanceCritic = builder.chanceCritic;
        this.damage = builder.damage;
        this.maxDamage = builder.maxDamage;
        this.life = builder.life;
        this.maxLife = builder.maxLife;
        this.baseLifeRegen = builder.baseLifeRegen;
        this.lifeRegenMultiplier = builder.lifeRegenMultiplier;
    }

    public static class StatusBuilder {
        private double chanceCritic;
        private int damage;
        private int maxDamage;
        private int life;
        private int maxLife;
        private int baseLifeRegen;
        private int lifeRegenMultiplier;

        StatusBuilder chanceCritic(double chanceCritic) {
            this.chanceCritic = chanceCritic;
            return this;
        }

        StatusBuilder damage(int damage) {
            this.damage = damage;
            return this;
        }

        StatusBuilder maxDamage(int maxDamage) {
            this.maxDamage = maxDamage;
            return this;
        }

        StatusBuilder life(int life) {
            this.life = life;
            return this;
        }

        StatusBuilder maxlife(int maxLife) {
            this.maxLife = maxLife;
            return this;
        }

        StatusBuilder baseLifeRegen(int baseLifeRegen) {
            this.baseLifeRegen = baseLifeRegen;
            return this;
        }

        StatusBuilder lifeRegenMultiplier (int lifeRegenMultiplier) {
            this.lifeRegenMultiplier = lifeRegenMultiplier;
            return this;
        }

        StatusEntity builder() {
            return new StatusEntity(this);
        }
    }

    public double getChanceCritic() {
        return chanceCritic;
    }

    public void setChanceCritic(double chanceCritic) {
        this.chanceCritic = chanceCritic;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getBaseLifeRegen() {
        return baseLifeRegen;
    }

    public void setBaseLifeRegen(int baseLifeRegen) {
        this.baseLifeRegen = baseLifeRegen;
    }

    public int getLifeRegenMultiplier() {
        return lifeRegenMultiplier;
    }

    public void setLifeRegenMultiplier(int lifeRegenMultiplier) {
        this.lifeRegenMultiplier = lifeRegenMultiplier;
    }
}
