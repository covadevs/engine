package com.sad2d.engine.entities;

import java.util.ArrayList;
import java.util.List;

public class EntityList {

    private static EntityList instance;

    private List<Entity> entities;
    private List<Enemy> enemies;

    private EntityList() {
        this.entities = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean addEntity(Entity e) {
        return this.entities.add(e);
    }

    public boolean addEnemy(Enemy e) { return  this.enemies.add(e);}
    public void removeEntity(int index) {
        this.entities.remove(index);
    }
    public static EntityList getInstance() {
        if(instance == null) {
            synchronized (EntityList.class) {
                instance = new EntityList();
            }
        }
        return instance;
    }

    public void resetAll() {
        this.entities = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }
}
