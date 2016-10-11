package com.github.mzule.androidweekly.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.ui.view.base.BaseLinearLayout;
import com.github.mzule.layoutannotation.Layout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by CaoDongping on 4/5/16.
 */
@Layout(R.layout.view_navi_bar)
public class NaviBar extends BaseLinearLayout {

    @Bind(R.id.leftTextView)
    TextView leftTextView;
    @Bind(R.id.rightTextView)
    TextView rightTextView;

    public NaviBar(Context context) {
        super(context);
    }

    public NaviBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NaviBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        super.init(context, attrs);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NaviBar);
            leftTextView.setText(a.getString(R.styleable.NaviBar_nb_left_text));
            rightTextView.setText(a.getString(R.styleable.NaviBar_nb_right_text));
            a.recycle();
        }
    }

    @OnClick(R.id.backButton)
    void back() {
        if (getContext() instanceof Activity) {
            ((Activity) getContext()).finish();
        }
    }

    public void setLeftText(String text) {
        leftTextView.setText(text);
    }

    public void setRightText(String text) {
        rightTextView.setText(text);
    }
}
