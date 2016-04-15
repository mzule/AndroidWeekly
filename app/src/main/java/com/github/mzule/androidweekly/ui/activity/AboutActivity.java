package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.github.mzule.androidweekly.R;

import butterknife.Bind;

/**
 * Created by CaoDongping on 4/12/16.
 */
public class AboutActivity extends BaseActivity {
    @Bind(R.id.textView)
    TextView textView;

    public static Intent makeIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    @Override
    protected void afterInject() {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(Html.fromHtml("Github Host: <br/><a href='https://github.com/mzule/AndroidWeekly'>https://github.com/mzule/AndroidWeekly</a><br/>" +
                "Weibo:<br/> <a href='sinaweibo://userinfo?uid=mzule'>mzule</a>"));
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_about;
    }
}
