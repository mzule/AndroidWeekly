package com.github.mzule.androidweekly.util;

import android.util.Xml;

import com.github.mzule.androidweekly.entity.Dict;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.URL;

/**
 * Created by CaoDongping on 3/31/16.
 */
public class DictParser {
    private Dict dict;
    private String url;

    public DictParser(String url) {
        this.url = url;
    }

    public Dict parse() throws IOException, XmlPullParserException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(new URL(url).openStream(), null);
        parser.nextTag();
        dict = new Dict();
        readDict(parser);
        return dict;
    }

    private void readDict(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "dict");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "key":
                    dict.setKey(readText(parser));
                    break;
                case "ps":
                    dict.addPs(readText(parser));
                    break;
                case "pron":
                    dict.addPron(readText(parser));
                    break;
                case "pos":
                    dict.addPos(readText(parser));
                    break;
                case "acceptation":
                    dict.addAcceptation(readText(parser));
                    break;
                case "fy":
                    dict.setFy(readText(parser));
                    break;
                case "sent":
                    parseSent(parser);
                    break;

            }
        }
    }

    private void parseSent(XmlPullParser parser) throws IOException, XmlPullParserException {
        Dict.Sent sent = new Dict.Sent();
        parser.require(XmlPullParser.START_TAG, null, "sent");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "orig":
                    sent.setOrig(readText(parser));
                    break;
                case "trans":
                    sent.setTrans(readText(parser));
                    break;
            }
        }
        dict.addSent(sent);
    }


    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = null;
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result == null ? null : result.trim();
    }
}
