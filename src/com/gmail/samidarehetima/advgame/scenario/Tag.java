package com.gmail.samidarehetima.advgame.scenario;

import java.util.Map;

public interface Tag
{
    //タグの開始時（<>を読み込んだ時）に呼び出される関数．
    public void start(Map<String, String> attributes);

    //タグのテキスト（<>と</>で囲まれている部分の文章）のセッター。
    //これはTemplateMethodパターンのほうがいいか？
    public void act(String text);

    //タグの終了時（</>を読み込んだ時）に呼び出される関数。
    public void end();

    //NULLオブジェクトパターン
    public static final Tag NULL = new Tag() {
        
                                      @Override
                                      public void start(Map<String, String> attributes)
                                      {}

                                      @Override
                                      public void act(String text)
                                      {}

                                      @Override
                                      public void end()
                                      {}
                                  };
}
