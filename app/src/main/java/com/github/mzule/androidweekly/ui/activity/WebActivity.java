package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.util.Tinter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class WebActivity extends FragmentActivity {
    @Bind(R.id.web_view)
    WebView webView;

    public static Intent makeIntent(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        Tinter.enableIfSupport(this);

        webView.getSettings().setDefaultFontSize(10);
        webView.loadUrl(getIntent().getStringExtra("url"));
    }
}
