package com.xn.materialdesignpro1.bessel_curve;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 绘制贝塞尔曲线的view
 *利用贝塞尔曲线制作水波纹效果
 */
public class WaveView extends View {

    private Paint paint;
    private Path path;
    private int mWaveLength;
    //初始y值
    private int originY;
    //x改变量
    private int dx;
    //y改变量
    private int dy;
    private int transX;
    private int transY;

    public WaveView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWaveLength=600;

        originY = 500;
        dx = mWaveLength/4;
        dy = 150;
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个波长
//        path.moveTo(100,300);
//        path.quadTo(200,200,300,300);
//        path.quadTo(400,400,500,300);
//        canvas.drawPath(path,paint);

        path.reset();

        if (transY< originY+dy)
            transY+=2;

        path.moveTo(-mWaveLength+transX,originY-transY);

        for (int i = -mWaveLength; i < getWidth()+mWaveLength; i+=mWaveLength) {
            //相对绘制贝塞尔曲线（相对于上一个曲线的终点的距离）
            path.rQuadTo(dx,-dy,2*dx,0);

            path.rQuadTo(dx,dy,2*dx,0);
        }
        //颜色填充，画一个封闭的区间
        path.lineTo(getWidth(),getHeight());
        path.lineTo(0,getHeight());
        path.close();
        canvas.drawPath(path,paint);

    }

    public void startAnimation(){
        ValueAnimator animator=ValueAnimator.ofInt(0,mWaveLength);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                transX = (int) animation.getAnimatedValue();

                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startAnimation();
        return true;
    }
}
