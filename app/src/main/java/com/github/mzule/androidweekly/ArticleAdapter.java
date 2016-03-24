package com.github.mzule.androidweekly;

import android.content.Context;

import com.github.mzule.easyadapter.SingleTypeAdapter;
import com.github.mzule.easyadapter.ViewType;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class ArticleAdapter extends SingleTypeAdapter<Article> {

    public ArticleAdapter(Context context) {
        super(context);
    }

    @Override
    protected Class<? extends ViewType> singleViewType() {
        return ArticleViewType.class;
    }
}
