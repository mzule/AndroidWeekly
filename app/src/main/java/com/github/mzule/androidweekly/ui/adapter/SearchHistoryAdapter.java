package com.github.mzule.androidweekly.ui.adapter;

import android.content.Context;

import com.github.mzule.androidweekly.ui.viewtype.SearchHistoryViewType;
import com.github.mzule.easyadapter.SingleTypeAdapter;
import com.github.mzule.easyadapter.ViewType;

/**
 * Created by CaoDongping on 4/5/16.
 */
public class SearchHistoryAdapter extends SingleTypeAdapter<String> {
    public SearchHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    protected Class<? extends ViewType> singleViewType() {
        return SearchHistoryViewType.class;
    }
}
