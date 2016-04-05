package com.github.mzule.androidweekly.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mzule.androidweekly.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by CaoDongping on 4/5/16.
 */
public class NaviBar extends LinearLayout {

    @Bind(R.id.leftTextView)
    TextView leftTextView;
    @Bind(R.id.rightTextView)
    TextView rightTextView;

    public NaviBar(Context context) {
        super(context);
        init(null);
    }

    public NaviBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NaviBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @OnClick(R.id.backButton)
    void back() {
        if (getContext() instanceof Activity) {
            ((Activity) getContext()).finish();
        }
    }

    private void init(AttributeSet attrs) {
        setOrientation(VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.view_navi_bar, this);
        ButterKnife.bind(this);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NaviBar);
            leftTextView.setText(a.getString(R.styleable.NaviBar_nb_left_text));
            rightTextView.setText(a.getString(R.styleable.NaviBar_nb_right_text));
            a.recycle();
        }
    }

    public void setLeftText(String text) {
        leftTextView.setText(text);
    }

    public void setRightText(String text) {
        rightTextView.setText(text);
    }
}
