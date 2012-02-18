package com.gmail.samidarehetima;

import java.awt.Dimension;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class MyGameEngine extends GameEngine implements GameScreenIds
{
    @Override
    public GameObject getGame(int GameID)
    {
        return new StageScreen(this);

        //        switch (GameID)
        //        {
        //        case GameScreenIds.TITLE_SCREEN:
        //            return new TitleScreen(this);
        //        case GameScreenIds.STAGE_SCREEN:
        //            return new StageScreen(this);
        //        case GameScreenIds.END_SCREEN:
        //            return new EndScreen(this);
        //        }
        //
        //        return null;
    }

    public static void main(String[] args)
    {
        GameLoader game = new GameLoader();
        game.setup(new MyGameEngine(), new Dimension(640, 480), false);
        game.start();
    }
    
    //出荷品であることを示すフラグ。
    //これをtrueにすると，
    //　１．画面端の「GTGE」の表示が消える
    //　２．ゲーム開始時にGTGEの広告が出る
    //  ３．マウスカーソルが非表示になる
    //などが発生する。
    {
        this.distribute = false;
    }
}
