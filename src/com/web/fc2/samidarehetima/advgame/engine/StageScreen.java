package com.web.fc2.samidarehetima.advgame.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ColorBackground;
import com.web.fc2.samidarehetima.advgame.gtgealpha.TextboxSprite;

public class StageScreen extends GameObject
{
    //[memo]BackgroundにはImageBackgroundもColorBackgroundもインスタンスできる
    private Background background = null;
    private TextboxSprite textbox = null;
    
    public StageScreen(GameEngine parent)
    {
        super(parent);
    }

    @Override
    public void initResources()
    {
        background = new ColorBackground(Color.BLACK, super.getWidth(), super.getHeight());
        textbox = new TextboxSprite(new Dimension(this.getWidth(), this.getHeight()));
       
        //ここでControllerのインスタンスを作る．
        //renderとupdateでtextbox#setText()などを呼び出し，画面の更新を行う
    }

    @Override
    public void render(Graphics2D g)
    {
        background.render(g);
        textbox.render(g);
    }

    @Override
    public void update(long elapsedTime)
    {
        background.update(elapsedTime);
        textbox.update(elapsedTime);
        if(click())
        {
            textbox.setText("あへぇぇぇぇぇぇぇぇぇぇぇぇ");
        }
    }

}
