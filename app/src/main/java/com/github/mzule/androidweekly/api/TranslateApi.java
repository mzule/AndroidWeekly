package com.github.mzule.androidweekly.api;

import android.os.Handler;

import com.github.mzule.androidweekly.entity.TranslateResult;
import com.github.mzule.androidweekly.util.JsonUtil;
import com.github.mzule.androidweekly.util.MD5;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by CaoDongping on 3/30/16.
 */
public class TranslateApi {
    private static final String APP_ID = "20160330000017157";
    private static final String SECRET_KEY = "18W3VqvS0Kgk7DbKekyN";
    private Handler handler = new Handler();

    private static String encode(String word) {
        return URLEncoder.encode(word);
    }

    public void translate(String q, final ApiCallback<TranslateResult> callback) {
        String salt = String.valueOf(Math.random());

        String url = "http://api.fanyi.baidu.com/api/trans/vip/translate?";
        url += "q=" + encode(q);
        url += "&from=auto";
        url += "&to=zh";
        url += "&appid=20160330000017157";
        url += "&salt=" + salt;
        String sign = MD5.encode(APP_ID + q + salt + SECRET_KEY);
        url += "&sign=" + encode(sign);
        final String reqUrl = url;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final TranslateResult result = JsonUtil.fromJson(new URL(reqUrl), TranslateResult.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result, false);
                        }
                    });
                } catch (final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure(e);
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
