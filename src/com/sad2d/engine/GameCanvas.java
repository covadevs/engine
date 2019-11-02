package com.sad2d.engine;

import com.sad2d.engine.entities.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameCanvas extends Canvas implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT ||
                keyEvent.getKeyCode() == KeyEvent.VK_D) {
            Player.getInstance().setRight(true);
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT ||
                keyEvent.getKeyCode() == KeyEvent.VK_A) {
            Player.getInstance().setLeft(true);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_UP ||
                keyEvent.getKeyCode() == KeyEvent.VK_W) {
            Player.getInstance().setUp(true);
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN ||
                keyEvent.getKeyCode() == KeyEvent.VK_S) {
            Player.getInstance().setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT ||
                keyEvent.getKeyCode() == KeyEvent.VK_D) {
            Player.getInstance().setRight(false);
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT ||
                keyEvent.getKeyCode() == KeyEvent.VK_A) {
            Player.getInstance().setLeft(false);
        }

        if(keyEvent.getKeyCode() == KeyEvent.VK_UP ||
                keyEvent.getKeyCode() == KeyEvent.VK_W) {
            Player.getInstance().setUp(false);
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN ||
                keyEvent.getKeyCode() == KeyEvent.VK_S) {
            Player.getInstance().setDown(false);
        }
    }
}
