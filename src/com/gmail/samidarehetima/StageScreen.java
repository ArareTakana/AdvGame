package com.gmail.samidarehetima;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.font.SystemFont;

//@see GTGEで遊ぼう http://springotherside.web.fc2.com/gtge/sec03_01.html
public class StageScreen extends GameObject
{
    private PlayField playField;
    private String    text = "";

    public StageScreen(GameEngine parent)
    {
        super(parent);
    }

    // 初期化処理
    @Override
    public void initResources()
    {
        final int WIN_W = super.getWidth();
        final int WIN_H = super.getHeight();

        this.playField = new PlayField();
        {
            //背景画像の表示
            ImageBackground background = new ImageBackground(
                    getImage("picture/background01.jpg"), WIN_W, WIN_H);
            this.playField.setBackground(background);

            //立ち絵のスプライト
            Sprite standSprite = new Sprite(getImage("picture/stand02.png"), 0,
                    0);
            standSprite.move((WIN_W - standSprite.getWidth()) / 2.0, 0);
            this.playField.add(standSprite);

            //テキスト領域のスプライト
            Sprite textboxSprite = new Sprite(getImage("picture/textbox.png"),
                    0, 0);
            textboxSprite.move((WIN_W - textboxSprite.getWidth()) / 2, WIN_H
                    - textboxSprite.getHeight() - 32);
            this.playField.add(textboxSprite);
        }

        this.text = "あなたって本当に最低のクズだわ";
    }

    //     更新処理
    @Override
    public void update(long elapsedTime)
    {
        this.playField.update(elapsedTime);

        if (click())
        {
            this.parent.nextGameID = GameScreenIds.END_SCREEN;
            finish();
        }
    }

    // 描画処理
    @Override
    public void render(Graphics2D g)
    {
        this.playField.render(g);

        //文字表示
        SystemFont font = new SystemFont(new Font("Monospace", Font.BOLD, 16));
        font.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        font.drawString(g, this.text, 50, 320);
    }
}
