package com.gmail.samidarehetima.advgame.scenario;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MyDefaultHandler extends DefaultHandler
{
    Stack<Node> stack;

    public MyDefaultHandler()
    {
        this.stack = new Stack<Node>();
    }

    // 文書の開始通知
    @Override
    public void startDocument()
    {
        System.out.println("StartDocument");
    }

    // 要素の開始通知
    @Override
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes attributes)
    {
        //多態性を利用して要素毎にインスタンスを変える。
        //クラス名からインスタンスを作る関数があったはず。
        BgNode node = new BgNode(attributes);

        this.stack.push(node);
    }

    // 要素内の文字データの通知
    @Override
    public void characters(char[] ch, int start, int length)
    {
        this.stack.peek().setText(new String(ch, start, length));
    }

    // 要素の終了通知
    @Override
    public void endElement(String namespaceURI, String localName, String qName)
    {
        Node nowNode = this.stack.pop();
        nowNode.end();
    }

    // 文書の終了通知
    @Override
    public void endDocument()
    {
        System.out.println("endDocument");
    }

    /*****************************************************/

    //通常の文法エラー処理
    @Override
    public void error(SAXParseException ex) throws SAXException
    {
        String errorMessage = "ERROR[" + ex.getLineNumber() + "行"
                + ex.getColumnNumber() + "桁]" + ex.getLocalizedMessage();
        System.out.println(errorMessage);
    }

    //深刻な文法エラー処理
    @Override
    public void fatalError(SAXParseException ex) throws SAXException
    {
        String errorMessage = "FATAL ERROR[" + ex.getLineNumber() + "行"
                + ex.getColumnNumber() + "桁]" + ex.getMessage();
        System.out.println(errorMessage);
    }

    //文法警告処理
    @Override
    public void warning(SAXParseException ex) throws SAXException
    {
        String warningMessage = "WARNING[" + ex.getLineNumber() + "行"
                + ex.getColumnNumber() + "桁]" + ex.getMessage();
        System.out.println(warningMessage);
    }
}
