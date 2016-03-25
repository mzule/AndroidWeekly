package com.github.mzule.androidweekly.ui.activity;

import android.os.Bundle;

import com.github.mzule.androidweekly.util.Tinter;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by CaoDongping on 3/24/16.
 */
public abstract class BaseActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        Tinter.enableIfSupport(this);
        afterInject();
        setSwipeBackEnable(enableSwipeBack());
    }

    protected boolean enableSwipeBack() {
        return true;
    }

    protected abstract void afterInject();

    protected abstract int getLayoutResourceId();
}
