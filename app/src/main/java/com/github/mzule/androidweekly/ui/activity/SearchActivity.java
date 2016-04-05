package com.github.mzule.androidweekly.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.dao.SearchHistoryKeeper;
import com.github.mzule.androidweekly.ui.adapter.SearchHistoryAdapter;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * Created by CaoDongping on 4/5/16.
 */
public class SearchActivity extends BaseActivity {

    @Bind(R.id.queryInput)
    EditText queryInput;
    @Bind(R.id.listView)
    ListView listView;
    private SearchHistoryAdapter adapter;

    public static Intent makeIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @OnItemClick(R.id.listView)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String q = (String) parent.getAdapter().getItem(position);
        SearchHistoryKeeper.save(q);
        startActivity(SearchResultActivity.makeIntent(this, q));
    }

    @Override
    protected void afterInject() {
        adapter = new SearchHistoryAdapter(this);
        listView.setAdapter(adapter);
        queryInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String q = queryInput.getText().toString().trim();
                startActivity(SearchResultActivity.makeIntent(SearchActivity.this, q));
                SearchHistoryKeeper.save(q);
                queryInput.setText("");
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.clear();
        adapter.addAndNotify(SearchHistoryKeeper.read());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_search;
    }
}
