package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.layoutannotation.Layout;

import butterknife.Bind;

/**
 * Created by CaoDongping on 4/12/16.
 */
@Layout(R.layout.activity_about)
public class AboutActivity extends BaseActivity {
    @Bind(R.id.textView)
    TextView textView;

    public static Intent makeIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    @Override
    protected void afterBind() {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(Html.fromHtml("Copyright: <br/><a href='http://androidweekly.net/'>http://androidweekly.net/</a><br/>" +
                "Github Host: <br/><a href='https://github.com/mzule/AndroidWeekly'>https://github.com/mzule/AndroidWeekly</a><br/>" +
                "Contributor:<br/> <a href='sinaweibo://userinfo?uid=mzule'>mzule</a> / <a href='sinaweibo://userinfo?uid=maoruibin'>maoruibin</a> "));
    }
}
