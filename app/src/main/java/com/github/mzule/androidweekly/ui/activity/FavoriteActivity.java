package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.dao.FavoriteDao;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.entity.Favorite;
import com.github.mzule.androidweekly.ui.adapter.ArticleAdapter;
import com.github.mzule.androidweekly.util.DateUtil;
import com.github.mzule.layoutannotation.Layout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by CaoDongping on 3/28/16.
 */
@Layout(R.layout.activity_favorite)
public class FavoriteActivity extends BaseActivity {
    private static final int REQUEST_CODE_OPEN_ARTICLE = 0x1;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.listView)
    ListView listView;
    private FavoriteDao favoriteDao;
    private ArticleAdapter adapter;

    public static Intent makeIntent(Context context) {
        return new Intent(context, FavoriteActivity.class);
    }

    @OnItemClick(R.id.listView)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getAdapter().getItem(position);
        if (item instanceof Article) {
            startActivityForResult(ArticleActivity.makeIntent(this, (Article) item), REQUEST_CODE_OPEN_ARTICLE);
        }
    }

    @OnClick(R.id.exportButton)
    void exportToFile() {
        try {
            favoriteDao.exportToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Export complete", Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawers();
    }

    @OnClick(R.id.importButton)
    void importFromFile() {
        try {
            favoriteDao.importFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter.clear();
        adapter.addAndNotify(extract(favoriteDao.read()));
        Toast.makeText(this, "Import complete", Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawers();
    }

    @Override
    protected void afterBind() {
        favoriteDao = new FavoriteDao();
        adapter = new ArticleAdapter(this);
        listView.setAdapter(adapter);
        renderFavorites();
    }

    private void renderFavorites() {
        List<Favorite> favorites = favoriteDao.read();
        adapter.clear();
        adapter.addAndNotify(extract(favorites));
    }

    private List<Object> extract(List<Favorite> favorites) {
        List<Object> ret = new ArrayList<>();
        for (Favorite favorite : favorites) {
            String time = DateUtil.format(favorite.getTime());
            if (!ret.contains(time)) {
                ret.add(time);
            }
            ret.add(favorite.getArticle());
        }
        return ret;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_OPEN_ARTICLE) {
            if (data.getBooleanExtra("changed", false)) {
                renderFavorites();
            }
        }
    }
}
