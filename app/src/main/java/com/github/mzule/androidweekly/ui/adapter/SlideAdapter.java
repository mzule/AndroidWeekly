package com.github.mzule.androidweekly.ui.adapter;

import android.content.Context;

import com.github.mzule.androidweekly.entity.Issue;
import com.github.mzule.androidweekly.ui.viewtype.SlideIssueViewType;
import com.github.mzule.easyadapter.SingleTypeAdapter;
import com.github.mzule.easyadapter.ViewType;

/**
 * Created by CaoDongping on 3/25/16.
 */
public class SlideAdapter extends SingleTypeAdapter<Issue> {

    public SlideAdapter(Context context) {
        super(context);
    }

    @Override
    protected Class<? extends ViewType> singleViewType() {
        return SlideIssueViewType.class;
    }
}
