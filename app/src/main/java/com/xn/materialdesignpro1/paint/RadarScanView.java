package com.xn.materialdesignpro1.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 雷达扫描效果
 * 手机卫士扫描
 */
public class RadarScanView extends View {

    private final int[] colors={Color.GRAY,Color.GREEN,Color.GRAY};
    private  Paint paint;
    private final SweepGradient sweepGradient;
    private float RADIUS=100;
    private int max= 100;
    private int progress=0;
    private final Matrix matrix;

    public RadarScanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        Log.i("xuning","getWidth:"+getWidth());
        sweepGradient = new SweepGradient(getWidth()/2,getHeight()/2,colors,null);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = (float) getWidth() / 2;
        float centerY = (float) getHeight() / 2;
        RADIUS= centerX-5;
        //画圆环
        paint.reset();
        paint.setColor(Color.GRAY);
        paint.setShader(sweepGradient);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        canvas.drawCircle(centerX,centerY,centerX-5,paint);
        //画圆弧
        paint.reset();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        Log.i("xuning","progress:"+progress);

        int degrees = 360 * progress / max;
        int factor=degrees/360;
        degrees=degrees-factor*360;
        matrix.setRotate(degrees,centerX,centerY);

        sweepGradient.setLocalMatrix(matrix);
        paint.setShader(sweepGradient);
        RectF oval = new RectF(centerX - RADIUS, centerY - RADIUS, centerX + RADIUS, centerY + RADIUS);
        if (progress!=0)
            canvas.drawArc(oval,0,degrees ,true,paint);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        if (progress<0){
            throw new IllegalArgumentException("progress can't less than 0");
        }
//        if (progress >= max){
//            progress = 0;
//        }
        if (progress <Integer.MAX_VALUE){
            this.progress=progress;
            postInvalidate();//多线程下要求重绘
        }
    }
}
