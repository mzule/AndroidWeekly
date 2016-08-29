package com.github.mzule.androidweekly.ui.viewtype;

import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.layoutannotation.Layout;

import butterknife.Bind;

/**
 * Created by CaoDongping on 4/5/16.
 */
@Layout(R.layout.item_search_history)
public class SearchHistoryViewType extends BaseViewType<String> {
    @Bind(R.id.textView)
    TextView textView;

    @Override
    public void onRender(int position, String data) {
        textView.setText(data);
    }
}
