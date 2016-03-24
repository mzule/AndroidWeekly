package com.github.mzule.androidweekly.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.api.ApiCallback;
import com.github.mzule.androidweekly.api.ArticleApi;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.ui.adapter.ArticleAdapter;
import com.github.mzule.androidweekly.ui.view.ProgressView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.progressView)
    ProgressView progressView;

    @OnItemClick(R.id.listView)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Article article = (Article) parent.getAdapter().getItem(position);
        startActivity(ArticleActivity.makeIntent(this, article));
    }

    @Override
    protected void afterInject() {

        final ArticleAdapter adapter = new ArticleAdapter(this);
        listView.setAdapter(adapter);

        new ArticleApi().getPage(new ApiCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> data) {
                adapter.clearAndNotify();
                adapter.addAndNotify(data);
                progressView.finish();
            }

            @Override
            public void onFailure(Exception e) {
                progressView.finish();
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }
}
