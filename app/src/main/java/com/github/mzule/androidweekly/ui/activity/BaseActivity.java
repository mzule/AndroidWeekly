package com.github.mzule.androidweekly.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.github.mzule.androidweekly.util.Tinter;

import butterknife.ButterKnife;

/**
 * Created by CaoDongping on 3/24/16.
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        Tinter.enableIfSupport(this);
        afterInject();
    }

    public void back(View v) {
        onBackPressed();
    }

    protected abstract void afterInject();

    protected abstract int getLayoutResourceId();
}
