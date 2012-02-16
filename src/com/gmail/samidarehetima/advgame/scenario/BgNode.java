package com.gmail.samidarehetima.advgame.scenario;

import org.xml.sax.Attributes;

public class BgNode implements Node
{
    private String mscFileName;
    private String imgFileName;
    private String sentence;

    @Override
    public void start(Attributes atts)
    {
        this.mscFileName = atts.getValue("msc");
        this.imgFileName = atts.getValue("img");
        
        System.out.println("<Bg>");
        System.out.println("mscFileName: " + this.mscFileName);
        System.out.println("imgFileName: " + this.imgFileName);
    }

    @Override
    public void setText(String text)
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
