package com.github.mzule.androidweekly.ui.viewtype;

import android.view.View;
import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.entity.Issue;
import com.github.mzule.layoutannotation.Layout;

import butterknife.Bind;

/**
 * Created by CaoDongping on 3/25/16.
 */
@Layout(R.layout.item_slide_issue)
public class SlideIssueViewType extends BaseViewType<Issue> {
    @Bind(R.id.nameView)
    TextView nameView;
    @Bind(R.id.dateView)
    TextView dateView;
    @Bind(R.id.rootLayout)
    View rootLayout;

    @Override
    public void onRender(int position, Issue data) {
        nameView.setText(data.getName());
        nameView.setSelected(data.isActive());
        dateView.setText(data.getDate());
        dateView.setSelected(data.isActive());
        int color = getContext().getResources().getColor(data.isActive() ? R.color.colorPrimary : R.color.transparent);
        rootLayout.setBackgroundColor(color);
    }
}
