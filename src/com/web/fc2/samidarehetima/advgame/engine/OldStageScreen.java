package com.web.fc2.samidarehetima.advgame.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.engine.BaseAudioRenderer;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.font.SystemFont;
import com.web.fc2.samidarehetima.advgame.gtgealpha.JavaLayerMp3Renderer;

//@see GTGEで遊ぼう http://springotherside.web.fc2.com/gtge/sec03_01.html
public class OldStageScreen extends GameObject
{
    private String          text = "";

    private ImageBackground background;
    private Sprite          standSprite;
    private Sprite          textboxSprite;

    public OldStageScreen(GameEngine parent)
    {
        super(parent);
    }

    // 初期化処理
    @Override
    public void initResources()
    {
        final int WIN_W = super.getWidth();
        final int WIN_H = super.getHeight();

        //背景画像の表示
        this.background = new ImageBackground(
                getImage("picture/background01.jpg"), WIN_W, WIN_H);

        //立ち絵のスプライト
        standSprite = new Sprite(getImage("picture/stand02.png"), 0, 0);
        standSprite.move((WIN_W - standSprite.getWidth()) / 2.0, 0);

        //テキスト領域のスプライト
        textboxSprite = new Sprite(getImage("picture/textbox.png"), 0, 0);
        textboxSprite.move((WIN_W - textboxSprite.getWidth()) / 2, WIN_H
                - textboxSprite.getHeight() - 32);

        this.text = "あなたって本当に最低のクズだわ";

        //BGM
//        bsMusic.setBaseRenderer(new JavaLayerMp3Renderer() );
//        playMusic("sound/bgm01.mp3");
    }

    //     更新処理
    @Override
    public void update(long elapsedTime)
    {
        this.background.update(elapsedTime);
        this.standSprite.update(elapsedTime);
        this.textboxSprite.update(elapsedTime);

        if (click())
        {
            //立ち絵をランダムに変更する
            Random rnd = new Random();
            int number = rnd.nextInt(4) + 1;
            String filename = "picture/stand0" + number + ".png";

            this.standSprite.setImage(getImage(filename));
            
        }

        //        if (click())
        //        {
        //            this.parent.nextGameID = GameScreenIds.END_SCREEN;
        //            finish();
        //        }
    }

    // 描画処理
    @Override
    public void render(Graphics2D g)
    {
        this.background.render(g);
        this.standSprite.render(g);
        this.textboxSprite.render(g);

        //文字表示
        SystemFont font = new SystemFont(new Font("Monospace", Font.BOLD, 16));
        font.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        font.drawString(g, this.text, 50, 320);
    }
}
