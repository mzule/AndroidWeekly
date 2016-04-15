package com.github.mzule.androidweekly.api.parser;

import android.support.annotation.WorkerThread;

/**
 * Created by CaoDongping on 4/15/16.
 */
public class ArticleParsers {
    @WorkerThread
    public static ArticleParser get(String issue) {
        if (issue == null || Integer.parseInt(issue.split("-")[1]) > 102) {
            return new FresherArticlesParser();
        } else {
            return new OlderArticlesParser();
        }
    }

}
