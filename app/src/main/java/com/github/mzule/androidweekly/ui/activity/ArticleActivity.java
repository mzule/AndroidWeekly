package com.github.mzule.androidweekly.ui.activity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.dao.FavoriteDao;
import com.github.mzule.androidweekly.dao.TextZoomKeeper;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.ui.view.ProgressView;
import com.github.mzule.androidweekly.ui.view.TranslateView;

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
    @Bind(R.id.favoriteButton)
    View favoriteButton;
    private Article article;
    private WebSettings settings;
    private boolean changed;
    private FavoriteDao favoriteDao;

    public static Intent makeIntent(Context context, Article article) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra("article", article);
        return intent;
    }

    @OnClick(R.id.favoriteButton)
    void favorite(View v) {
        changed = true;
        v.setSelected(!v.isSelected());
        if (v.isSelected()) {
            favoriteDao.save(article);
        } else {
            favoriteDao.delete(article);
        }
        drawerLayout.closeDrawers();
    }

    @OnClick(R.id.increaseButton)
    void increate() {
        settings.setTextZoom(settings.getTextZoom() + 5);
    }

    @OnClick(R.id.decreaseButton)
    void decrease() {
        settings.setTextZoom(settings.getTextZoom() - 5);
    }

    @OnClick(R.id.translateButton)
    void translateAndPaste(View v) {
        new TranslateView(this).attachTo(this, null);
        drawerLayout.closeDrawers();
    }

    @OnClick(R.id.shareButton)
    void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, article.getTitle() + " " + article.getLink());
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "SHARE"));

        drawerLayout.closeDrawers();
    }

    @OnClick(R.id.copyUrlButton)
    void copyUrl() {
        ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label",article.getLink());
        clipboard.setPrimaryClip(clip);
        drawerLayout.closeDrawers();
        Toast.makeText(ArticleActivity.this, "已复制文章链接到粘贴板", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void afterInject() {
        favoriteDao = new FavoriteDao();
        article = (Article) getIntent().getSerializableExtra("article");
        settings = webView.getSettings();
        webView.loadUrl(article.getLink());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressView.stop();
            }
        });
        settings.setTextZoom(TextZoomKeeper.read(settings.getTextZoom()));
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        favoriteButton.setSelected(favoriteDao.contains(article));
    }

    @Override
    protected void onStop() {
        super.onStop();
        TextZoomKeeper.save(settings.getTextZoom());
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("changed", changed);
        setResult(RESULT_OK, intent);
        super.finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_article;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
