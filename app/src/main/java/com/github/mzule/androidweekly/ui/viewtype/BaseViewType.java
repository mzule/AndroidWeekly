/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.mzule.androidweekly.ui.viewtype;

import com.github.mzule.easyadapter.ViewType;
import com.github.mzule.layoutannotation.LayoutBinder;

import butterknife.ButterKnife;

/**
 * Created by CaoDongping on 8/29/16.
 */

public abstract class BaseViewType<T> extends ViewType<T> {
    @Override
    public void onCreate() {
        setContentView(LayoutBinder.inflate(getContext(), this));
        ButterKnife.bind(this, getView());
    }
}
