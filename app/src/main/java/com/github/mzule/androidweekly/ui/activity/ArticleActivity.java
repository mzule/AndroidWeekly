package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.ui.view.ProgressView;

import butterknife.Bind;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class ArticleActivity extends BaseActivity {
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.progressView)
    ProgressView progressView;

    public static Intent makeIntent(Context context, Article article) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("article", article);
        return intent;
    }

    @Override
    protected void afterInject() {
        Article article = (Article) getIntent().getSerializableExtra("article");
        webView.loadUrl(article.getLink());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressView.finish();
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_article;
    }
}
