package com.xn.materialdesignpro1.custom_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.xn.materialdesignpro1.R;

/**
 * 自定义加载动画view，（类似于ios上的加载菊花）
 */
public class MyLoadingView extends View {

    private int mWidth;
    private int mHeight;
    private int mBackgroundColor;
    private int mLoadingColor;
    private String mLoadingText;
    private Paint mTextPaint;
    private Paint mLoadingPaint;
    private Paint mBackPaint;

    private float sweepAngle=360;
    private float startAngle=60;
    private float rotateAngle;
    private float progress;


    public MyLoadingView(Context context) {
        this(context,null);
    }

    public MyLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        initPaint();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyLoadingView);
        mBackgroundColor = array.getColor(R.styleable.MyLoadingView_backgroundColor, Color.parseColor("#770b0306"));
        mLoadingColor = array.getColor(R.styleable.MyLoadingView_loadingColor, Color.WHITE);
        mLoadingText = array.getString(R.styleable.MyLoadingView_loadingText);
        
        array.recycle();
    }
    private final int[] colors={Color.GRAY,Color.WHITE,Color.GRAY};
    SweepGradient sweepGradient;
    Matrix matrix;
    private void initPaint() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLoadingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLoadingPaint.setColor(mLoadingColor);
        mLoadingPaint.setStyle(Paint.Style.FILL);
        mLoadingPaint.setStrokeWidth(8);
        mLoadingPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setColor(mLoadingColor);
        mBackPaint.setColor(mBackgroundColor);
        mBackPaint.setStyle(Paint.Style.FILL);
        rotateAngle = sweepAngle/11;


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        sweepGradient = new SweepGradient(mWidth/2,(mHeight-30)/2,colors,null);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawLoading(canvas);
    }

    private void drawLoading(Canvas canvas) {
        canvas.save();
        startAngle=progress*sweepAngle;
        canvas.translate(mWidth/2,(mHeight-30)/2);
        canvas.rotate(startAngle);
        float hasDrawAngle=0;

        float curLength=2;
        for (int i = 0; i < 11; i++) {

            if (hasDrawAngle>sweepAngle)
                curLength=2;
            if (curLength> 13)
                curLength=2;
            canvas.drawLine(40,0,40+curLength,0,mLoadingPaint);
            curLength+=1;
            hasDrawAngle+=rotateAngle;
            canvas.rotate(rotateAngle);
        }
        canvas.restore();
        float degrees = 360 * progress ;
        matrix.setRotate(degrees,mWidth/2,(mHeight-30)/2);
        sweepGradient.setLocalMatrix(matrix);
        mLoadingPaint.setShader(sweepGradient);
    }

    private void drawBackground(Canvas canvas) {
        RectF rectF=new RectF(0,0,mWidth,mHeight);
        canvas.drawRoundRect(rectF,20,20,mBackPaint);
    }
    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startAnim();
        return super.onTouchEvent(event);
    }
}
