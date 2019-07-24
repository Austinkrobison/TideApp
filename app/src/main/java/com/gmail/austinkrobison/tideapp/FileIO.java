package com.gmail.austinkrobison.tideapp;

import android.content.Context;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FileIO {
    private final String  FILENAME = "9434098_annual.xml";
    private Context context;

    public FileIO(Context context){
        this.context = context;
    }
    public RSSFeed readFile(){
        //parses xml contents into an RSSFeed object
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();

            RSSFeedHandler handler = new RSSFeedHandler();
            xmlReader.setContentHandler(handler);

            InputStream in = context.getAssets().open(FILENAME);

            InputSource is = new InputSource(in);
            xmlReader.parse(is);

            RSSFeed items = handler.getFeed();
            return items;

        }catch(Exception e){
            Log.e("Tide App", e.toString());
            return null;
        }

    }
}
