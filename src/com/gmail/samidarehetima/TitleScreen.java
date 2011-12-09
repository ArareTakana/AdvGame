package com.gmail.samidarehetima;

import java.awt.Graphics2D;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

public class TitleScreen extends GameObject implements GameScreenIds
{
    private PlayField playField;

    public TitleScreen(GameEngine parent)
    {
        super(parent);
    }

    @Override
    public void initResources()
    {
        final int WIN_W = super.getWidth();
        final int WIN_H = super.getHeight();

        this.playField = new PlayField();
        {
            ImageBackground background = new ImageBackground(
                    getImage("picture/title.jpg"), WIN_W, WIN_H);
            this.playField.setBackground(background);

            //ゲームの開始ボタン
            final int margin = 64;
            OrgButton startButton = new OrgButton(super.bsInput, "Start",
                    WIN_W * 3 / 4, margin);
            startButton.setActionListener(new ButtonListener() {
                @Override
                public void actionPerformed()
                {
                    TitleScreen.this.parent.nextGameID = GameScreenIds.STAGE_SCREEN;
                    finish();
                }
            });
            this.playField.add(startButton);

            //ゲームの終了ボタン
            OrgButton endButton = new OrgButton(super.bsInput, "Exit",
                    WIN_W * 3 / 4, startButton.getY() + startButton.getHeight()
                            + margin);
            endButton.setActionListener(new ButtonListener() {
                @Override
                public void actionPerformed()
                {
                    finish();
                }
            });
            this.playField.add(endButton);
        }

    }

    @Override
    public void update(long elapsedTime)
    {
        this.playField.update(elapsedTime);
    }

    @Override
    public void render(Graphics2D g)
    {
        this.playField.render(g);
    }
}
