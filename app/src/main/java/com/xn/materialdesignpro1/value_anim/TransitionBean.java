package com.xn.materialdesignpro1.value_anim;

import java.io.Serializable;

public class TransitionBean implements Serializable {
    private int res;
    private String title;

    public TransitionBean() {

    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TransitionBean(int res, String title) {
        this.res = res;
        this.title = title;
    }
}
