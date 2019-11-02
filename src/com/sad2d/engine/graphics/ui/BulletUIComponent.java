package com.sad2d.engine.graphics.ui;

import com.sad2d.engine.entities.Player;
import com.sad2d.engine.main.Game;

import java.awt.*;

public class BulletUIComponent extends UI {

    public BulletUIComponent(int width, int height, int x, int y) {
        super(width, height, x, y);
        setLen(width);
    }

    //37 = stringwidth("Ammo: 999")
    @Override
    public void render(Graphics g) {
        FontMetrics metrics = g.getFontMetrics();
        String ammo = "Ammo: "+Player.getInstance().getAmmo();
        int sw = metrics.stringWidth(ammo);
        Rectangle rectangle = new Rectangle(Game.WIDTH*Game.SCALE - getWidth()-10, Game.HEIGHT*Game.SCALE-getHeight()-10,  (int)super.getLen(), getHeight());
        Rectangle innerRectangle = new Rectangle(Game.WIDTH*Game.SCALE - getWidth()-9, Game.HEIGHT*Game.SCALE-getHeight()-9,  (int)super.getLen()-1, getHeight()-1);
        g.drawRect((int)rectangle.getX(), (int)rectangle.getY(),  (int)rectangle.getWidth(), (int)rectangle.getHeight());
        g.setColor(Color.YELLOW);
        g.fillRect( (int)innerRectangle.getX(),
                    (int)innerRectangle.getY(),
                    (int)innerRectangle.getWidth(),
                    (int)innerRectangle.getHeight());
        g.setColor(Color.BLACK);
        drawCenteredString(g, rectangle, ammo);
    }

    @Override
    public void update() {

    }
}
