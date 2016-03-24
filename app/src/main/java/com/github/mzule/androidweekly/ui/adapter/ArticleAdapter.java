package com.github.mzule.androidweekly.ui.adapter;

import android.content.Context;

import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.ui.view.PinnedSectionListView;
import com.github.mzule.androidweekly.ui.viewtype.ArticleViewType;
import com.github.mzule.androidweekly.ui.viewtype.SectionViewType;
import com.github.mzule.easyadapter.TypePerEntityAdapter;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class ArticleAdapter extends TypePerEntityAdapter<Object> implements PinnedSectionListView.PinnedSectionListAdapter {

    public ArticleAdapter(Context context) {
        super(context);
    }

    @Override
    protected void mapEntityViewTypes() {
        mapEntityViewType(String.class, SectionViewType.class);
        mapEntityViewType(Article.class, ArticleViewType.class);
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return getRawViewType(SectionViewType.class) == viewType;
    }
}
