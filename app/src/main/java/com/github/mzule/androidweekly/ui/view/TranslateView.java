package com.github.mzule.androidweekly.ui.view;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.api.ApiCallback;
import com.github.mzule.androidweekly.api.DictionaryApi;
import com.github.mzule.androidweekly.entity.Dict;
import com.github.mzule.androidweekly.util.Keyboard;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by CaoDongping on 3/30/16.
 */
public class TranslateView extends PopupView<Void> {
    @Bind(R.id.queryInput)
    EditText queryInput;
    @Bind(R.id.resultView)
    EditText resultView;
    @Bind(R.id.buttonPanel)
    View buttonPanel;

    public TranslateView(Context context) {
        super(context);
        init();
    }

    @OnClick(R.id.maskView)
    @Override
    public void finish() {
        super.finish();
        hideKeyboard();
    }

    private void hideKeyboard() {
        if (getContext() instanceof Activity) {
            Keyboard.hide((Activity) getContext());
        }
    }

    @OnClick(R.id.clearButton)
    void clear() {
        queryInput.setText("");
        resultView.setText("");
        queryInput.requestFocus();
        Keyboard.show(getContext());
    }

    @OnClick(R.id.translateButton)
    void translate() {
        new DictionaryApi().look(queryInput.getText().toString().toLowerCase(), new ApiCallback<Dict>() {
            @Override
            public void onSuccess(Dict data, boolean fromCache) {
                resultView.setText("");
                for (int i = 0; i < data.getPs().size(); i++) {
                    resultView.append(data.getPs().get(i) + "\n");
                }
                if (!TextUtils.isEmpty(data.getFy())) {
                    resultView.append("\n" + data.getFy() + "\n");
                }
                for (int i = 0; i < data.getPos().size(); i++) {
                    resultView.append("\n");
                    resultView.append(data.getPos().get(i) + "\n");
                    resultView.append(data.getAcceptation().get(i) + "\n");
                }
                for (int i = 0; i < data.getSent().size(); i++) {
                    resultView.append("\n");
                    resultView.append(data.getSent().get(i).getOrig() + "\n");
                    resultView.append(data.getSent().get(i).getTrans() + "\n");
                }
                resultView.setText(resultView.getText().toString().trim());
                resultView.setSelection(0);
                hideKeyboard();
                updateResultViewMaxHeight();
            }

            @Override
            public void onFailure(Exception e) {
                resultView.setText(Log.getStackTraceString(e));
            }
        });
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_translate, this);
        ButterKnife.bind(this);
    }

    @Override
    protected void render(Void data) {
        String paste = getPasteText();
        queryInput.requestFocus();
        if (!TextUtils.isEmpty(paste)) {
            queryInput.setText(paste);
            queryInput.setSelection(paste.length());
            translate();
        }
    }

    private void updateResultViewMaxHeight() {
        int height = getResources().getDisplayMetrics().heightPixels;
        resultView.setMaxHeight(height - queryInput.getBottom() - buttonPanel.getMeasuredHeight() - queryInput.getPaddingLeft());
    }

    @Nullable
    private String getPasteText() {
        String paste = null;
        ClipboardManager manager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = manager.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0 && clip.getItemAt(0) != null) {
            CharSequence text = clip.getItemAt(0).getText();
            paste = text == null ? "" : text.toString();
        }
        return paste;
    }

    @Override
    protected View getMaskView() {
        return findViewById(R.id.maskView);
    }

    @Override
    protected View getMainView() {
        return findViewById(R.id.mainView);
    }

    @Override
    protected int getAnimationDirection() {
        return ANIMATION_DIRECTION_TOP_TO_BOTTOM;
    }
}
