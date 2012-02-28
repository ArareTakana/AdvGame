package com.web.fc2.samidarehetima.advgame.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ColorBackground;
import com.web.fc2.samidarehetima.advgame.gtgealpha.TextboxSprite;
import com.web.fc2.samidarehetima.advgame.scenario.Scenario;
import com.web.fc2.samidarehetima.advgame.scenario.ShowingText;

public class StageScreen extends GameObject
{
    //[memo]BackgroundにはImageBackgroundもColorBackgroundもインスタンスできる
    private Background    background = null;
    private TextboxSprite textbox    = null;
    private Scenario      scenarioNow;

    public StageScreen(GameEngine parent)
    {
        super(parent);
    }

    @Override
    public void initResources()
    {
        background = new ColorBackground(Color.BLACK, super.getWidth(),
                super.getHeight());
        textbox = new TextboxSprite(getImage("picture/textbox.png"), new Dimension(this.getWidth(),
                this.getHeight()));

        //実際にはここでControllerのインスタンスを作り，UIとシナリオ部の仲介役にする．
        //Controllerからシナリオ部の情報を受けとり，renderとupdateで
        //textbox#setText()などを呼び出し，画面の更新を行う
        scenarioNow = new Scenario("xml/scenario.xml");
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
        if (click())
        {
            if (scenarioNow.hasNextTag())
            {
                scenarioNow.doNextTag();
            }
            textbox.setText(ShowingText.INSTASNCE.getText());
        }
    }

}
