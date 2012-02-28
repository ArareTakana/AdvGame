package com.web.fc2.samidarehetima.advgame.scenario;

import java.util.Map;

public interface Tag
{
    public void setAttributes(Map<String, String> attributes);

    public void setText(String text);

    //タグの開始時（<>を読み込んだ時）に呼び出される関数．
    public void start();

    //タグのテキスト（<>と</>で囲まれている部分の文章）を読み込んだ時に呼び出される関数。
    public void act();

    //タグの終了時（</>を読み込んだ時）に呼び出される関数。
    public void end();

    //NULLオブジェクトパターン
    public static final Tag NULL = new Tag() {

                                     @Override
                                     public void setAttributes(
                                             Map<String, String> attributes)
                                     {}

                                     @Override
                                     public void setText(String text)
                                     {}

                                     @Override
                                     public void start()
                                     {
                                         System.out.print("<NULL>");
                                     }

                                     @Override
                                     public void act()
                                     {
                                         System.out.print("NULL");
                                     }

                                     @Override
                                     public void end()
                                     {
                                         System.out.print("</NULL>");
                                     }

                                 };
}
