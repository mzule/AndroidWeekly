package com.github.mzule.androidweekly.api;

import android.os.Handler;

import com.github.mzule.androidweekly.entity.Dict;
import com.github.mzule.androidweekly.util.DictParser;

import java.net.URLEncoder;

/**
 * Created by CaoDongping on 3/31/16.
 */
public class DictionaryApi {
    private Handler handler = new Handler();

    public void look(String q, final ApiCallback<Dict> callback) {
        final String url = "http://dict-co.iciba.com/api/dictionary.php?w=" + URLEncoder.encode(q) + "&key=A3950A86AC38B86FCDEE6EAC68931C25&type=xml";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Dict result = new DictParser(url).parse();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result, false);
                        }
                    });
                } catch (final Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure(e);
                        }
                    });
                }
            }
        }).start();
    }
}
