package com.xn.materialdesignpro1.paint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xn.materialdesignpro1.R;

/**
 * 自定义加载进度条
 */
public class MyProgressBar extends View {

    private final Paint mPaint;
    private final int roundColor;
    private final float strokeWidth;
    private final int style;
    private static final int STYLE_FILL=0;
    private static final int STYLE_STROKE=1;
    private final int textColor;
    private final float textSize;
    private final int maxValue;
    private final boolean showText;
    private int progress;
    private final int progressColor;

    public int getRoundColor() {
        return roundColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public int getStyle() {
        return style;
    }

    public int getTextColor() {
        return textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public boolean isShowText() {
        return showText;
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyProgressBar);
        roundColor = typedArray.getColor(R.styleable.MyProgressBar_roundColor, Color.RED);
        progressColor = typedArray.getColor(R.styleable.MyProgressBar_progressColor, Color.GREEN);
        strokeWidth = typedArray.getDimension(R.styleable.MyProgressBar_strokeWidth, 10);
        style = typedArray.getInt(R.styleable.MyProgressBar_style, 0);
        textColor = typedArray.getColor(R.styleable.MyProgressBar_textColor, Color.GREEN);
        textSize = typedArray.getDimension(R.styleable.MyProgressBar_textSize, 15);
        maxValue = typedArray.getInt(R.styleable.MyProgressBar_maxValue, 100);
        showText = typedArray.getBoolean(R.styleable.MyProgressBar_showText, true);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆环
        mPaint.setColor(roundColor);
        float centerX = (float)getWidth() / 2;
        float centerY = (float) getHeight() / 2;
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setAntiAlias(true);
        float radius = getWidth()/2-strokeWidth/2;
        canvas.drawCircle(centerX,centerY,radius,mPaint);

        //画文字
        mPaint.reset();
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setColor(textColor);
        String text=(int)(progress /(float)maxValue *100)+"%";
        float startX = (float) getWidth() / 2 - mPaint.measureText(text) / 2;
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        float startY=centerY+ (metrics.bottom-metrics.top)/2-metrics.bottom;
        canvas.drawText(text,startX,startY,mPaint);
        //画圆弧
        mPaint.reset();
        mPaint.setColor(progressColor);
        mPaint.setStrokeWidth(strokeWidth);
        RectF oval=new RectF(centerX-radius,centerY-radius,centerX+radius,centerY+radius);
        switch (style){
            case STYLE_FILL:
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress!=0)//必须要有这个判断，否则会在开始时显示一个角度
                    canvas.drawArc(oval,0,360*progress/maxValue,true,mPaint);
                break;
            case STYLE_STROKE:
                mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval,0,360*progress/maxValue,false,mPaint);
                break;
        }
    }

    public synchronized void setProgress(int progress){
        if (progress<0){
            throw new IllegalArgumentException("progress can't less than 0");
        }
        if (progress >= maxValue){
            progress =maxValue;
        }
        if (progress <=maxValue){
            this.progress=progress;
            postInvalidate();//多线程下要求重绘
        }
    }

    public synchronized int getProgress(){
        return progress;
    }

}
