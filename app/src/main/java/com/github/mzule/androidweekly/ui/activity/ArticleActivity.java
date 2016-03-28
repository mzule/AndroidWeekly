package com.github.mzule.androidweekly.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.ui.view.ProgressView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class ArticleActivity extends BaseActivity {
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.progressView)
    ProgressView progressView;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private Article article;

    public static Intent makeIntent(Context context, Article article) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("article", article);
        return intent;
    }

    @OnClick(R.id.increaseButton)
    void increate() {
        webView.getSettings().setTextZoom(webView.getSettings().getTextZoom() + 5);
    }

    @OnClick(R.id.decreaseButton)
    void decrease() {
        webView.getSettings().setTextZoom(webView.getSettings().getTextZoom() - 5);
    }

    @OnClick(R.id.shareButton)
    void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, article.getTitle() + " " + article.getLink());
        intent.setType("text/plain");
        startActivity(intent);

        drawerLayout.closeDrawers();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void afterInject() {
        article = (Article) getIntent().getSerializableExtra("article");
        webView.loadUrl(article.getLink());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressView.finish();
            }
        });
        webView.getSettings().setTextZoom(75);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressView.finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_article;
    }

}
