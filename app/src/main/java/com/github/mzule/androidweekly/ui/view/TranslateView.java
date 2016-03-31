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
import android.widget.TextView;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.api.ApiCallback;
import com.github.mzule.androidweekly.api.TranslateApi;
import com.github.mzule.androidweekly.entity.TranslateResult;
import com.github.mzule.androidweekly.util.Keyboard;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by CaoDongping on 3/30/16.
 */
public class TranslateView extends PopupView<Boolean> {
    @Bind(R.id.queryInput)
    EditText queryInput;
    @Bind(R.id.resultView)
    TextView resultView;

    public TranslateView(Context context) {
        super(context);
        init();
    }

    @OnClick(R.id.maskView)
    @Override
    public void finish() {
        super.finish();
        if (getContext() instanceof Activity) {
            Keyboard.hide((Activity) getContext());
        }
    }

    @OnClick(R.id.clearButton)
    void clear() {
        queryInput.setText("");
        resultView.setText("");
    }

    @OnClick(R.id.translateButton)
    void translate() {
        new TranslateApi().translate(queryInput.getText().toString(), new ApiCallback<TranslateResult>() {
            @Override
            public void onSuccess(TranslateResult data, boolean fromCache) {
                resultView.setText(data.getDst());
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
    protected void render(Boolean data) {
        final boolean pasteAndTranslate = Boolean.TRUE.equals(data);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                String paste = getPasteText();
                queryInput.requestFocus();
                Keyboard.show(getContext());
                if (pasteAndTranslate && !TextUtils.isEmpty(paste)) {
                    queryInput.setText(paste);
                    queryInput.setSelection(paste.length());
                    translate();
                }
            }
        }, ANIMATE_DURATION);
    }

    @Nullable
    private String getPasteText() {
        String paste = null;
        ClipboardManager manager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = manager.getPrimaryClip();
        if (clip.getItemCount() > 0) {
            paste = clip.getItemAt(0).getText().toString();
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
