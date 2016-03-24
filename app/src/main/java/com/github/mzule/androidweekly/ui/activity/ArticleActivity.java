package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.entity.Article;

import butterknife.Bind;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class ArticleActivity extends BaseActivity {
    @Bind(R.id.web_view)
    WebView webView;

    public static Intent makeIntent(Context context, Article article) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("article", article);
        return intent;
    }

    @Override
    protected void afterInject() {
        Article article = (Article) getIntent().getSerializableExtra("article");
        webView.loadUrl(article.getLink());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_article;
    }
}
