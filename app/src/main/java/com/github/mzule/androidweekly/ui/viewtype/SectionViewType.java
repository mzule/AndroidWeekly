package com.github.mzule.androidweekly.ui.viewtype;

import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.easyadapter.ViewType;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class SectionViewType extends ViewType<String> {
    @Bind(R.id.sectionName)
    TextView sectionName;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_section);
        ButterKnife.bind(this, getView());
    }

    @Override
    public void onRender(int position, String data) {
        sectionName.setText(data);
    }
}
