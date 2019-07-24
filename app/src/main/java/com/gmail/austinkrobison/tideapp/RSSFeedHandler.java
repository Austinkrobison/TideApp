package com.gmail.austinkrobison.tideapp;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class RSSFeedHandler extends DefaultHandler {
    private RSSFeed feed;
    private RSSItem item;

    private boolean isTime = false;
    private boolean isHighLow = false;
    private boolean isPredInFeet = false;
    private boolean isDate = false;
    private boolean isDay = false;

    public RSSFeed getFeed(){
        return feed;
    }
    public void startDocument() throws SAXException{
        feed = new RSSFeed();
        item = new RSSItem();
    }
    public void endDocument() throws SAXException{}

    public void startElement(String namespaceURI, String LocalName,
                             String qName, Attributes atts) throws SAXException{
        if(qName.equals("item")){
            item = new RSSItem();
            return;
        }else if(qName.equals("date")) {
            isDate = true;
            return;
        }else if(qName.equals("day")) {
            isDay = true;
            return;
        }else if(qName.equals("pred_in_ft")) {
            isPredInFeet = true;
            return;
        }else if(qName.equals("time")) {
            isTime = true;
            return;
        }else if(qName.equals("highlow")) {
            isHighLow = true;
            return;
        }
    }
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException{
        if(qName.equals("item")) {
            feed.addItem(item);
            return;
        }
    }
    public void characters(char ch[], int start,int length){
        String s = new String(ch, start, length);
        if(isDate){
            item.setDate(s);
            isDate = false;
        }else if(isDay){
            item.setDay(s);
            isDay = false;
        }else if(isTime){
            item.setTime(s);
            isTime = false;
        }else if(isHighLow){
            item.setHighLow(s);
            isHighLow = false;
        }else if(isPredInFeet){
            item.setPredInFeet(s);
            isPredInFeet = false;
        }
    }

}
