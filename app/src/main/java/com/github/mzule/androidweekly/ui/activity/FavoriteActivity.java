package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.dao.FavoriteKeeper;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.ui.adapter.ArticleAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * Created by CaoDongping on 3/28/16.
 */
public class FavoriteActivity extends BaseActivity {
    private static final int REQUEST_CODE_OPEN_ARTICLE = 0x1;
    @Bind(R.id.listView)
    ListView listView;
    private ArticleAdapter adapter;

    public static Intent makeIntent(Context context) {
        return new Intent(context, FavoriteActivity.class);
    }

    @OnItemClick(R.id.listView)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Article article = (Article) parent.getAdapter().getItem(position);
        startActivityForResult(ArticleActivity.makeIntent(this, article), REQUEST_CODE_OPEN_ARTICLE);
    }

    @Override
    protected void afterInject() {
        List<Article> articles = FavoriteKeeper.read();
        adapter = new ArticleAdapter(this);
        listView.setAdapter(adapter);
        adapter.addAndNotify(articles);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_favorite;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_OPEN_ARTICLE) {
            Article article = (Article) data.getSerializableExtra("article");
            if (!FavoriteKeeper.read().contains(article)) {
                adapter.removeAndNotify(article);
            }
        }
    }
}
