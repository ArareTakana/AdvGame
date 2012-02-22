package com.gmail.samidarehetima.advgame.scenario;

import org.xml.sax.Attributes;

public interface Node
{
    //タグの開始時（<>を読み込んだ時）に呼び出される関数．
    public void start(Attributes atts);

    //タグのテキスト（<>と</>で囲まれている部分の文章）のセッター。
    //これはTemplateMethodパターンのほうがいいか？
    public void setText(String text);

    //タグの終了時（</>を読み込んだ時）に呼び出される関数。
    public void end();

    //NULLオブジェクトパターン
    public static final Node NULL = new Node() {
        
                                      @Override
                                      public void start(Attributes atts)
                                      {}

                                      @Override
                                      public void setText(String text)
                                      {}

                                      @Override
                                      public void end()
                                      {}
                                  };
}
