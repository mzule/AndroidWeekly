package com.github.mzule.androidweekly.ui.viewtype;

import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.easyadapter.ViewType;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CaoDongping on 4/5/16.
 */
public class SearchHistoryViewType extends ViewType<String> {
    @Bind(R.id.textView)
    TextView textView;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_search_history);
        ButterKnife.bind(this, getView());
    }

    @Override
    public void onRender(int position, String data) {
        textView.setText(data);
    }
}
