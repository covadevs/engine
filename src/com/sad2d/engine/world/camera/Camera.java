package com.sad2d.engine.world.camera;

public class Camera {

    public static int x = 0;
    public static int y = 0;

    public static int clamp(int atual,int min,int max) {
        if(atual > max) {
            atual = max;
        }

        if(atual < min) {
            atual = min;
        }

        return atual;
    }
}
