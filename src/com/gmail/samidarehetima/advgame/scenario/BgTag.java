package com.gmail.samidarehetima.advgame.scenario;

import java.util.Map;

public class BgTag implements Tag
{
    private String mscFileName;
    private String imgFileName;
    private String sentence;

    @Override
    public void start(Map<String, String> attributes)
    {
        this.mscFileName = attributes.get("msc");
        this.imgFileName = attributes.get("img");
        
        System.out.println("<Bg>");
        System.out.println("mscFileName: " + this.mscFileName);
        System.out.println("imgFileName: " + this.imgFileName);
    }

    @Override
    public void act(String text)
    {
        this.sentence = text;
        System.out.print(this.sentence);
    }

    @Override
    public void end()
    {
        System.out.println("\n</Bg>");
    }

}
