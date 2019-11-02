package com.sad2d.engine;

import java.util.Random;

public class Utils {
    private static Utils instance;

    private Random generator;

    private Utils() {
        this.generator = new Random();
    }

    public Random getGenerator() {
        return generator;
    }

    public boolean doIt(double chance) {
        return this.generator.nextDouble()*100.0d <= chance;
    }

    public static Utils getInstance() {
        if(instance == null) {
            synchronized (Utils.class) {
                instance = new Utils();
            }
        }

        return instance;
    }
}
