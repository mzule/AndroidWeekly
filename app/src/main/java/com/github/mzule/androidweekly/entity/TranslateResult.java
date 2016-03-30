package com.github.mzule.androidweekly.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CaoDongping on 3/30/16.
 */
public class TranslateResult {
    private String from;
    private String to;
    @SerializedName("trans_result")
    private List<Result> result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public String getDst() {
        if (getResult() != null && !getResult().isEmpty()) {
            return getResult().get(0).getDst();
        }
        return null;
    }

    public static class Result {
        private String src;
        private String dst;

        public Result(String src, String dst) {
            this.src = src;
            this.dst = dst;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }
}
