package com.web.fc2.samidarehetima.advgame.scenario;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class Scenario
{
    final private String          filepath;
    final private XMLStreamReader xmlNow;
    final private Stack<Tag>      tagBuffer = new Stack<Tag>();

    public Scenario(String filepath)
    {        
        this.filepath = filepath;
        try
        {
            XMLInputFactory StAXfactory = XMLInputFactory.newInstance();
            BufferedInputStream fileStream = new BufferedInputStream(
                    new FileInputStream(filepath));
            this.xmlNow = StAXfactory.createXMLStreamReader(fileStream);
        }
        catch (FileNotFoundException e)
        {
            throw new IllegalArgumentException(filepath + "is not found.");
        }
        catch (XMLStreamException e)
        {
            throw new IllegalArgumentException(filepath + "can't open.");
        }
    }

    public boolean hasNextTag()
    {
        try
        {
            return this.xmlNow.hasNext();
        }
        catch (XMLStreamException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public void doNextTag()
    {
        try
        {
            if (this.xmlNow.hasNext())
            {
                switch (this.xmlNow.next())
                {
                case XMLEvent.START_ELEMENT:
                {
                    tagBuffer.push(startTag());
                    break;
                }
                case XMLEvent.CHARACTERS:
                {
                    insertTextToTag(tagBuffer.peek(), this.xmlNow.getText());
                    break;
                }
                case XMLEvent.END_ELEMENT:
                {
                    endTag(tagBuffer.pop());
                    break;
                }
                }
            }
        }
        catch (XMLStreamException e)
        {
            while (!tagBuffer.empty())
            {
                Tag tagNow = tagBuffer.pop();
                tagNow.end();
            }
            throw new IllegalStateException(e);
        }
    }

    public void close()
    {
        try
        {
            this.xmlNow.close();
        }
        catch (XMLStreamException e)
        {
            throw new IllegalStateException(this.filepath
                    + "is already closed.");
        }
    }

    private Tag startTag()
    {
        Tag tagNow = Tag.NULL;

        try
        {
            //タグ名からクラスを生成する．
            //@see http://java.sun.com/j2se/1.5.0/ja/docs/ja/api/java/lang/Class.html#newInstance()
            //@see http://www.syboos.jp/java/doc/create-object-instance-from-string.html
            final String nodeClassName = this.getClass().getPackage().getName()
                    + "." + this.xmlNow.getName() + "Tag";
            Class<?> nodeClass = Class.forName(nodeClassName);
            tagNow = (Tag) nodeClass.newInstance();
        }
        catch (Exception e)
        {
            tagNow = Tag.NULL;
        }

        //属性の取得
        Map<String, String> attributes = new HashMap<String, String>();
        int attributeTotal = this.xmlNow.getAttributeCount();
        for (int i = 0; i < attributeTotal; i++)
        {
            attributes.put(this.xmlNow.getAttributeLocalName(i),
                    this.xmlNow.getAttributeValue(i));
        }
        tagNow.setAttributes(attributes);
        tagNow.start();
        return tagNow;
    }
    
    private void insertTextToTag(Tag tagNow, String xmlText)
    {
        //空白文字（\t等も含む）と改行を取り除く
        String adjustedText = xmlText.replaceAll("\\s|\\n", "");
        tagNow.setText(adjustedText);
        tagNow.act();
    }

    private void endTag(Tag tagNow)
    {
        tagNow.end();
    }
}
