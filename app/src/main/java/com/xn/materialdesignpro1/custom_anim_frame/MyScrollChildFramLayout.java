package com.xn.materialdesignpro1.custom_anim_frame;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 这个布局时用来包裹子view，以便于赋我们需要的值
 * 继承FrameLayout是为了开销更小
 */
public class MyScrollChildFramLayout extends FrameLayout implements MyScrollListener {

    private int mWidth;
    private int mHeight;
    private View mInnerView;

    public MyScrollChildFramLayout(@NonNull Context context) {
        super(context);
    }

    public MyScrollChildFramLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isAlpha;
    public boolean isScaleX;
    public boolean isScaleY;
    public int transFrom;
    public int bgFromColor;
    public int bgToColor;

    //滑动初始方向
    public static final int  TRANSFROM_LEFT=0x01;
    public static final int  TRANSFROM_TOP=0x02;
    public static final int  TRANSFROM_RIGHT=0x04;
    public static final int  TRANSFROM_BOTTOM=0x08;

    //颜色估值器
    private static ArgbEvaluator argbEvaluator=new ArgbEvaluator();

    public boolean isAlpha() {
        return isAlpha;
    }

    public void setAlpha(boolean alpha) {
        isAlpha = alpha;
    }

    public boolean isScaleX() {
        return isScaleX;
    }

    public void setScaleX(boolean scaleX) {
        isScaleX = scaleX;
    }

    public boolean isScaleY() {
        return isScaleY;
    }

    public void setScaleY(boolean scaleY) {
        isScaleY = scaleY;
    }

    public int getTransFrom() {
        return transFrom;
    }

    public void setTransFrom(int transFrom) {
        this.transFrom = transFrom;
    }

    public int getBgFromColor() {
        return bgFromColor;
    }

    public void setBgFromColor(int bgFromColor) {
        this.bgFromColor = bgFromColor;
    }

    public int getBgToColor() {
        return bgToColor;
    }

    public void setBgToColor(int bgToColor) {
        this.bgToColor = bgToColor;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mInnerView = getChildAt(0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        onReset();
    }

    @SuppressLint("NewApi")
    @Override
    public void onChanged(float ratio) {//ratio是从0到1的值，进度
        Log.i("ratioonChanged","进度"+ratio);
        if (isAlpha){
            setAlpha(ratio);
        }
        if (isScaleX){
            setScaleX(ratio);
        }
        if (isScaleY){
            setScaleY(ratio);
        }
        if (isTranslation(TRANSFROM_LEFT)){
            setTranslationX(-mWidth * (1-ratio));//-width -> 0
        }
        if (isTranslation(TRANSFROM_TOP)){
            setTranslationY(-mHeight * (1-ratio));
        }
        if (isTranslation(TRANSFROM_RIGHT)){
            setTranslationX(mWidth * (1-ratio));//width -> 0
        }
        if (isTranslation(TRANSFROM_BOTTOM)){
            setTranslationY(mHeight * (1-ratio));
        }
        if (bgToColor!=-1 && bgFromColor!=-1){
            //利用颜色估值器计算出一个不断变化的颜色值
            setBackgroundColor((int)argbEvaluator.evaluate(ratio,bgFromColor,bgToColor));
        }

    }

    @Override
    public void onReset() {
        if (isAlpha){
            setAlpha(0f);
        }
        if (isScaleX){
            setScaleX(0f);
        }
        if (isScaleY){
            setScaleY(0f);
        }
        if (isTranslation(TRANSFROM_LEFT)){
            setTranslationX(-mWidth);
        }
        if (isTranslation(TRANSFROM_TOP)){
            setTranslationY(-mHeight);
        }
        if (isTranslation(TRANSFROM_RIGHT)){
            setTranslationX(mWidth);
        }
        if (isTranslation(TRANSFROM_BOTTOM)){
            setTranslationX(mHeight);
        }
    }

    /**
     * 判断是否有平移
     * @param transMask
     * @return
     */
    private boolean isTranslation(int transMask) {
        if (transFrom==-1){
            return false;
        }
        return (transFrom & transMask) == transMask;
    }

}
