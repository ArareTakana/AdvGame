package com.web.fc2.samidarehetima.advgame.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.font.SystemFont;

public class EndScreen extends GameObject
{
    private ColorBackground background;

    public EndScreen(GameEngine parent)
    {
        super(parent);
    }

    @Override
    public void initResources()
    {
        final int WIN_W = super.getWidth();
        final int WIN_H = super.getHeight();

        this.background = new ColorBackground(Color.BLACK, WIN_W, WIN_H);
    }

    @Override
    public void update(long elapsedTime)
    {
        if (click())
        {
            this.parent.nextGameID = GameScreenIds.TITLE_SCREEN;
            finish();
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        this.background.render(g);

        SystemFont font = new SystemFont(new Font("Monospace", Font.BOLD, 30));
        font.setColor(Color.WHITE);
        font.drawString(g, "End...", super.getWidth() / 2,
                super.getHeight() / 2);
    }
}
