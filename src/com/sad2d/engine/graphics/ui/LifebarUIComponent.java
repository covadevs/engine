package com.sad2d.engine.graphics.ui;

import com.sad2d.engine.entities.Player;

import java.awt.*;

public class LifebarUIComponent extends UI {

    private Rectangle border;

    public LifebarUIComponent(int width, int height, int x, int y) {
        super(width, height, x, y);
        super.setLen(width);
        this.border = new Rectangle(getX(), getY(), (int)super.getLen()+2, getHeight()+2);
    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int)this.border.getX(), (int)this.border.getY(), (int)this.border.getWidth(), (int)this.border.getHeight());
        g.setColor(Color.RED);
        Rectangle lifeRect = new Rectangle(getX() + 1, getY() + 1, (int) (((double)Player.getInstance().getStatus().getLife() / Player.getInstance().getStatus().getMaxLife()) * super.getLen()), getHeight());
        g.fillRect((int) lifeRect.getX(), (int) lifeRect.getY(), (int) lifeRect.getWidth(), (int) lifeRect.getHeight());
        g.setColor(Color.WHITE);
        String life = (int)Player.getInstance().getStatus().getLife()+"/"+(int)Player.getInstance().getStatus().getMaxLife();
        drawCenteredString(g, lifeRect, life);
    }

    @Override
    public void update() {

    }

    public String fillString(String str, int len, char fillChar) {
        String s = String.valueOf(fillChar);
        String newString = "";
        if(str.length() < len) {
            newString = s.repeat(len - str.length());
        }

        newString+=str;

        return newString;
    }
}
