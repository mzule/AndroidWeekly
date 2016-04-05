package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.ui.view.NaviBar;

import butterknife.Bind;

/**
 * Created by CaoDongping on 4/5/16.
 */
public class SearchResultActivity extends BaseActivity {
    @Bind(R.id.naviBar)
    NaviBar naviBar;

    public static Intent makeIntent(Context context, String q) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("q", q);
        return intent;
    }

    @Override
    protected void afterInject() {
        String q = getIntent().getStringExtra("q");
        naviBar.setLeftText(q.toUpperCase());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_search_result;
    }
}
