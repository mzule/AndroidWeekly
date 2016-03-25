package com.github.mzule.androidweekly.ui.viewtype;

import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.entity.Issue;
import com.github.mzule.easyadapter.ViewType;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CaoDongping on 3/25/16.
 */
public class SlideIssueViewType extends ViewType<Issue> {
    @Bind(R.id.nameView)
    TextView nameView;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_slide_issue);
        ButterKnife.bind(this, getView());
    }

    @Override
    public void onRender(int position, Issue data) {
        nameView.setText(data.getName());
        nameView.setSelected(data.isActive());
    }
}
