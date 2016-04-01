package com.github.mzule.androidweekly.entity;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoDongping on 3/31/16.
 */
public class Dict {
    private String key;
    private List<String> ps;
    private List<String> pos;
    private List<String> pron;
    private List<String> acceptation;
    private List<Sent> sent;
    private String fy;

    public Dict() {
        ps = new ArrayList<>();
        pos = new ArrayList<>();
        pron = new ArrayList<>();
        acceptation = new ArrayList<>();
        sent = new ArrayList<>();
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getPs().size(); i++) {
            sb.append(getPs().get(i)).append("\n");
        }
        if (!TextUtils.isEmpty(getFy())) {
            sb.append("\n");
            sb.append(getFy()).append("\n");
        }
        for (int i = 0; i < getPos().size(); i++) {
            sb.append("\n");
            sb.append(getPos().get(i)).append("\n");
            sb.append(getAcceptation().get(i)).append("\n");
        }
        for (int i = 0; i < getSent().size(); i++) {
            sb.append("\n");
            sb.append(getSent().get(i).getOrig()).append("\n");
            sb.append(getSent().get(i).getTrans()).append("\n");
        }
        return sb.toString().trim();
    }

    public void addPos(String pos) {
        this.pos.add(pos);
    }

    public void addAcceptation(String acceptation) {
        this.acceptation.add(acceptation);
    }

    public List<String> getPos() {
        return pos;
    }

    public void setPos(List<String> pos) {
        this.pos = pos;
    }

    public List<String> getAcceptation() {
        return acceptation;
    }

    public void setAcceptation(List<String> acceptation) {
        this.acceptation = acceptation;
    }

    public void addPs(String ps) {
        this.ps.add(ps);
    }

    public void addPron(String pron) {
        this.pron.add(pron);
    }

    public void addSent(Sent sent) {
        this.sent.add(sent);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getPs() {
        return ps;
    }

    public void setPs(List<String> ps) {
        this.ps = ps;
    }

    public List<String> getPron() {
        return pron;
    }

    public void setPron(List<String> pron) {
        this.pron = pron;
    }

    public List<Sent> getSent() {
        return sent;
    }

    public void setSent(List<Sent> sent) {
        this.sent = sent;
    }

    public String getFy() {
        return fy;
    }

    public void setFy(String fy) {
        this.fy = fy;
    }

    public static class Sent {
        private String orig;
        private String trans;

        public String getOrig() {
            return orig;
        }

        public void setOrig(String orig) {
            this.orig = orig;
        }

        public String getTrans() {
            return trans;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }
    }
}
