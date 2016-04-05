package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mzule.androidweekly.R;

import butterknife.Bind;

/**
 * Created by CaoDongping on 4/5/16.
 */
public class SearchActivity extends BaseActivity {

    @Bind(R.id.queryInput)
    EditText queryInput;

    public static Intent makeIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void afterInject() {
        queryInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                startActivity(SearchResultActivity.makeIntent(SearchActivity.this, queryInput.getText().toString()));
                queryInput.setText("");
                return true;
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_search;
    }
}
