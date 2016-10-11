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
public class FresherArticlesParser implements ArticleParser {

    @Override
    public List<Object> parse(String issue) throws IOException {
        Document doc = DocumentProvider.get(issue);
        List<Object> articles = new ArrayList<>();
        Elements tables = doc.getElementsByTag("table");
        String currentSection = null;
        for (Element e : tables) {
            Elements h2 = e.getElementsByTag("h2");
            Elements h5 = e.getElementsByTag("h5");// 兼容issue-226 SPONSORED 在 h5 标签里面
            if (!h2.isEmpty() || !h5.isEmpty()) {
                currentSection = h2.size() > 0 ? h2.get(0).text() : h5.get(0).text();
                if (!articles.contains(currentSection)) {
                    articles.add(currentSection);
                }
            } else {
                Elements tds = e.getElementsByTag("td");
                Element td = tds.get(tds.size() - 2);
                String imageUrl = null;
                if (tds.size() == 4) {
                    imageUrl = tds.get(0).getElementsByTag("img").get(0).attr("src");
                }
                String title = td.getElementsByClass("article-headline").get(0).text();
                String brief = td.getElementsByTag("p").get(0).text();
                String link = td.getElementsByClass("article-headline").get(0).attr("href");
                String domain = td.getElementsByTag("span").get(0).text().replace("(", "").replace(")", "");
                if (issue == null) {
                    String number = doc.getElementsByClass("issue-header").get(0).getElementsByTag("span").get(0).text();
                    issue = "/issues/issue-" + number.replace("#", "");
                }
                Article article = new Article();
                article.setTitle(title);
                article.setBrief(brief);
                article.setLink(link);
                article.setDomain(domain);
                article.setIssue(issue);
                article.setImageUrl(imageUrl);
                article.setSection(currentSection);
                articles.add(article);
            }
        }
        return articles;
    }
}
