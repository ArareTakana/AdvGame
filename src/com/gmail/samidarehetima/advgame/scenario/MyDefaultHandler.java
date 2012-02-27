//package com.gmail.samidarehetima.advgame.scenario;
//
//import java.util.Stack;
//
//import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
//import org.xml.sax.SAXParseException;
//import org.xml.sax.helpers.DefaultHandler;
//
//public class MyDefaultHandler extends DefaultHandler
//{
//    Stack<Node> nodes;
//
//    public MyDefaultHandler()
//    {
//        this.nodes = new Stack<Node>();
//    }
//
//    // 要素の開始通知
//    @Override
//    public void startElement(String namespaceURI, String localName,
//            String qName, Attributes attributes)
//    {
//        Node node;
//        try
//        {
//            //タグ名からNodeのインスタンスを作成する．
//            //@see http://java.sun.com/j2se/1.5.0/ja/docs/ja/api/java/lang/Class.html#newInstance()
//            //@see http://www.syboos.jp/java/doc/create-object-instance-from-string.html
//            final String nodeClassName = this.getClass().getPackage().getName()
//                    + "." + qName + "Node";
//            Class<?> nodeClass = Class.forName(nodeClassName);
//            node = (Node) nodeClass.newInstance();
//        }
//        catch (Exception e)
//        {
//            node = Node.NULL;
//        }
//
//        node.start(attributes);
//        this.nodes.push(node);
//    }
//
//    // 要素内の文字データの通知
//    @Override
//    public void characters(char[] ch, int start, int length)
//    {
//        //文字列を読み込み，先頭末尾の空白やタブを取り除く
//        String plainText = new String(ch, start, length);
//        String adjustedText = plainText.trim().replaceAll("\t", "");
//        
//        Node nowNode = this.nodes.peek();
//        nowNode.setText(adjustedText);
//    }
//
//    // 要素の終了通知
//    @Override
//    public void endElement(String namespaceURI, String localName, String qName)
//    {
//        Node nowNode = this.nodes.pop();
//        nowNode.end();
//    }
//
//    /*****************************************************/
//
//    //通常の文法エラー処理
//    @Override
//    public void error(SAXParseException ex) throws SAXException
//    {
//        String errorMessage = "ERROR[" + ex.getLineNumber() + "行"
//                + ex.getColumnNumber() + "桁]" + ex.getLocalizedMessage();
//        System.out.println(errorMessage);
//    }
//
//    //深刻な文法エラー処理
//    @Override
//    public void fatalError(SAXParseException ex) throws SAXException
//    {
//        String errorMessage = "FATAL ERROR[" + ex.getLineNumber() + "行"
//                + ex.getColumnNumber() + "桁]" + ex.getMessage();
//        System.out.println(errorMessage);
//    }
//
//    //文法警告処理
//    @Override
//    public void warning(SAXParseException ex) throws SAXException
//    {
//        String warningMessage = "WARNING[" + ex.getLineNumber() + "行"
//                + ex.getColumnNumber() + "桁]" + ex.getMessage();
//        System.out.println(warningMessage);
//    }
//
//    // 文書の開始通知
//    //  @Override
//    //  public void startDocument()
//    //  {
//    //      //        System.out.println("StartDocument");
//    //  }
//    // 文書の終了通知
//    //    @Override
//    //    public void endDocument()
//    //    {
//    //        //        System.out.println("endDocument");
//    //    }
//}
