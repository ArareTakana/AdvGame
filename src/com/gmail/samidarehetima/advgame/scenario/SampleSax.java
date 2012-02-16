package com.gmail.samidarehetima.advgame.scenario;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

public class SampleSax
{
    public static void main(String[] args)
    {
        try
        {
            final String filename = "xml/scenario.xml";

            // ファクトリを作成
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            // XMLを検証
            saxParserFactory.setValidating(true);
            // XMLファイルを読み込む
            BufferedInputStream input = new BufferedInputStream(
                    new FileInputStream(new File(filename)));
            // SAXパーサーを取得
            SAXParser saxParser = saxParserFactory.newSAXParser();
            InputSource inputSource = new InputSource(input);

            MyDefaultHandler handler = new MyDefaultHandler();
            // XMLファイルをリード
            saxParser.parse(inputSource, handler);

        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}
