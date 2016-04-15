package com.github.mzule.androidweekly.api.parser;

import com.github.mzule.androidweekly.entity.Article;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoDongping on 4/15/16.
 */
public class OlderArticlesParser implements ArticleParser {

    @Override
    public List<Object> parse(String issue) throws IOException {
        Document doc = DocumentProvider.get(issue);
        List<Object> articles = new ArrayList<>();
        Element root = doc.getElementsByClass("issue").get(0);
        while (root.children().size() == 1) {
            root = root.child(0);
        }
        String currentSection = null;
        for (Element e : root.children()) {
            if (e.tagName().equals("h2")) {
                currentSection = e.text();
                articles.add(currentSection);
                continue;
            }
            if (e.tagName().equals("div")) {
                Elements img = e.getElementsByTag("img");
                if (!img.isEmpty()) {
                    Article article = new Article();
                    article.setImageUrl(img.get(0).attr("src"));
                    article.setTitle(e.getElementsByTag("a").get(1).text());
                    article.setLink(e.getElementsByTag("a").get(1).attr("href"));
                    article.setBrief(e.getElementsByTag("p").get(0).text());
                    Elements span = e.getElementsByTag("span");
                    if (!span.isEmpty()) {
                        article.setDomain(span.get(0).text().replace("(", "").replace(")", ""));
                    }
                    article.setIssue(issue);
                    article.setSection(currentSection);
                    articles.add(article);
                }
            } else {
                Article article = new Article();
                Elements title = e.getElementsByTag("a");
                if (title.isEmpty()) {
                    continue;
                }
                article.setTitle(title.get(0).text());
                Elements span = e.getElementsByTag("span");
                if (!span.isEmpty()) {
                    article.setDomain(span.get(0).text().replace("(", "").replace(")", ""));
                }
                article.setLink(e.getElementsByTag("a").get(0).attr("href"));
                article.setBrief(e.text());
                article.setIssue(issue);
                article.setSection(currentSection);
                articles.add(article);
            }
        }
        return articles;
    }
}
