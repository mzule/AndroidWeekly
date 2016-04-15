package com.github.mzule.androidweekly.api.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 * Created by CaoDongping on 4/15/16.
 */
public class DocumentProvider {
    public static Document get(String issue) throws IOException {
        String url = "http://androidweekly.net/";
        if (issue != null) {
            url += issue;
        }
        return Jsoup.parse(new URL(url), 30000);
    }
}
