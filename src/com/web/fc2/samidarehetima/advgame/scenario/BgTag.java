package com.web.fc2.samidarehetima.advgame.scenario;

import java.util.Map;

public class BgTag implements Tag
{
    private String mscFileName = "";
    private String imgFileName = "";
    private String sentence = "";

    @Override
    public void setAttributes(Map<String, String> attributes)
    {
        this.mscFileName = attributes.get("msc");
        this.imgFileName = attributes.get("img");
    }

    @Override
    public void setText(String text)
    {
        this.sentence = text;
    }
    
    @Override
    public void start()
    {
        System.out.println("<Bg>");
        System.out.println("mscFileName: " + this.mscFileName);
        System.out.println("imgFileName: " + this.imgFileName);
    }

    @Override
    public void act()
    {
        ShowingText.INSTASNCE.setText(sentence);
//        System.out.print(this.sentence);
    }

    @Override
    public void end()
    {
        System.out.println("\n</Bg>");
    }
}
