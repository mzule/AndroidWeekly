package com.github.mzule.androidweekly.ui.activity;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.api.ApiCallback;
import com.github.mzule.androidweekly.api.ArticleApi;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.entity.Issue;
import com.github.mzule.androidweekly.ui.adapter.ArticleAdapter;
import com.github.mzule.androidweekly.ui.adapter.SlideAdapter;
import com.github.mzule.androidweekly.ui.view.ProgressView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.progressView)
    ProgressView progressView;
    @Bind(R.id.slideListView)
    ListView slideListView;
    private ArticleAdapter adapter;
    private SlideAdapter slideAdapter;
    private List<Issue> issues;
    private ArticleApi articleApi;

    @OnItemClick(R.id.slideListView)
    void onSlideItemClick(AdapterView<?> parent, View view, int position, long id) {
        Issue issue = (Issue) parent.getAdapter().getItem(position);
        active(issue);
        slideAdapter.notifyDataSetChanged();

        sendArticleListRequest(issue.getUrl());

        drawerLayout.closeDrawers();
        progressView.start();
    }

    @OnItemClick(R.id.listView)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Article article = (Article) parent.getAdapter().getItem(position);
        startActivity(ArticleActivity.makeIntent(this, article));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @OnClick(R.id.slideMenuButton)
    void onSlideMenuClick() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    protected void afterInject() {
        articleApi = new ArticleApi();

        adapter = new ArticleAdapter(this);
        listView.setAdapter(adapter);

        slideAdapter = new SlideAdapter(this);
        slideListView.setAdapter(slideAdapter);

        sendArticleListRequest(null);
        sendIssueListRequest();
    }

    private void active(Issue issue) {
        for (Issue i : issues) {
            i.setActive(i == issue);
        }
    }

    private void sendArticleListRequest(String issue) {
        articleApi.getPage(issue, new ApiCallback<List<Object>>() {
            @Override
            public void onSuccess(List<Object> data) {
                adapter.clear();
                adapter.addAndNotify(data);
                listView.setSelection(0);
                progressView.finish();
            }

            @Override
            public void onFailure(Exception e) {
                progressView.finish();
            }
        });
    }

    private void sendIssueListRequest() {
        articleApi.getArchive(new ApiCallback<List<Issue>>() {
            @Override
            public void onSuccess(List<Issue> data) {
                issues = data;
                slideAdapter.clear();
                slideAdapter.addAndNotify(issues);
                active(issues.get(0));
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean enableSwipeBack() {
        return false;
    }
}
