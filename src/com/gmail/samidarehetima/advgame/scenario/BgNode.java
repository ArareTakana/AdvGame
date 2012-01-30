package com.gmail.samidarehetima.advgame.scenario;

import org.xml.sax.Attributes;

public class BgNode extends Node
{
    final private String mscFileName;
    final private String imgFileName;
    private String text; 

    protected BgNode(Attributes atts)
    {
      super(atts);
      this.mscFileName=atts.getValue("msc");
      this.imgFileName=atts.getValue("img");
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void end()
    {
        System.out.println("</bg>");
    }
}
