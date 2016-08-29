package com.github.mzule.androidweekly.ui.viewtype;

import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.layoutannotation.Layout;

import butterknife.Bind;

/**
 * Created by CaoDongping on 3/24/16.
 */
@Layout(R.layout.item_section)
public class SectionViewType extends BaseViewType<String> {
    @Bind(R.id.sectionName)
    TextView sectionName;

    @Override
    public void onRender(int position, String data) {
        sectionName.setText(data);
    }
}
