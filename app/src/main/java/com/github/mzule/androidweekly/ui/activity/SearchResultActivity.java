package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.dao.ArticleDao;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.ui.adapter.ArticleAdapter;
import com.github.mzule.androidweekly.ui.view.NaviBar;
import com.github.mzule.layoutannotation.Layout;

import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * Created by CaoDongping on 4/5/16.
 */
@Layout(R.layout.activity_search_result)
public class SearchResultActivity extends BaseActivity {
    @Bind(R.id.naviBar)
    NaviBar naviBar;
    @Bind(R.id.listView)
    ListView listView;

    public static Intent makeIntent(Context context, String q) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("q", q);
        return intent;
    }

    @OnItemClick(R.id.listView)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getAdapter().getItem(position);
        if (item instanceof Article) {
            startActivity(ArticleActivity.makeIntent(this, (Article) item));
        }
    }

    @Override
    protected void afterBind() {
        String q = getIntent().getStringExtra("q");
        naviBar.setLeftText(q.toUpperCase());

        ArticleDao articleDao = new ArticleDao();
        List<Article> result = articleDao.search(q);
        ArticleAdapter adapter = new ArticleAdapter(this);
        listView.setAdapter(adapter);
        adapter.addAndNotify(result);
    }
}
