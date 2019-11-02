package com.sad2d.engine.entities;

public enum EntityTypes {
    PLAYER("PLAYER"),
    ENEMY("ENEMY"),
    WEAPON("WEAPON"),
    BULLET("BULLET"),
    LIFEPACK("LIFEPACK");

    private String entityLabel;

    EntityTypes(String label) {
        this.entityLabel = label;
    }

    public String getEntityLabel() {
        return entityLabel;
    }
}
