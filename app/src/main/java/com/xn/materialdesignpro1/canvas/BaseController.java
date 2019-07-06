package com.xn.materialdesignpro1.canvas;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class BaseController {
    public static final int STATE_NORMAL=0;
    public static final int STATE_START=1;
    public static final int STATE_STOP=2;
    public int state=STATE_NORMAL;
    public int j=10;


    private AnimSearchView searchView;
    public Float pro;
    public Float resetPro;

    public abstract void draw(Canvas canvas , Paint paint);

    public float getViewWidth(){
        if (searchView==null)
            throw new NullPointerException("没有绑定控件");
        return searchView.getWidth();
    }
    public float getViewHeight(){
        if (searchView==null)
            throw new NullPointerException("没有绑定控件");
        return searchView.getHeight();
    }
    public abstract void startAnim();

    public void startViewAnimation() {
        ValueAnimator animator=ValueAnimator.ofFloat(0f,1f);
        animator.setDuration(800);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                pro = (Float) animation.getAnimatedValue();
                //要求重绘
                searchView.invalidate();
            }
        });
        pro=0f;
        animator.start();
    }

    public void resetViewAnimation(){
        ValueAnimator animator=ValueAnimator.ofFloat(0f,1f);
        animator.setDuration(800);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                resetPro = (Float) animation.getAnimatedValue();
                //要求重绘
                searchView.invalidate();
            }
        });
        resetPro=0f;
        animator.start();
    }

    public abstract void resetAnim();

    public void setView(AnimSearchView animSearchView) {
        this.searchView=animSearchView;
    }
}
