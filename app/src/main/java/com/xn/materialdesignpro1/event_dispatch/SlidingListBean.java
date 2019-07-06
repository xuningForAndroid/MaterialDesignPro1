package com.xn.materialdesignpro1.event_dispatch;

public class SlidingListBean {
    private boolean isOpen;
    private String showText;
    private String hideText;

    public SlidingListBean() {
    }

    public SlidingListBean(boolean isOpen, String showText, String hideText) {
        this.isOpen = isOpen;
        this.showText = showText;
        this.hideText = hideText;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public String getHideText() {
        return hideText;
    }

    public void setHideText(String hideText) {
        this.hideText = hideText;
    }
}
