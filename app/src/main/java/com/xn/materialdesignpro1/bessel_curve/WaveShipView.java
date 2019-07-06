package com.xn.materialdesignpro1.bessel_curve;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xn.materialdesignpro1.R;

/**
 * 小船在波浪上漂浮
 */
public class WaveShipView extends View {

    private float fractor;
    private Path wavePath;
    private Paint paint;
    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;
    private PathMeasure pathMeasure;
    private Matrix matrix;
    private int bitmapWidth;
    private int bitmapHeight;

    public WaveShipView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

        wavePath = new Path();
        initShip();
    }

    private void initShip() {
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=2;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ship, options);
        bitmapWidth = mBitmap.getWidth();
        bitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        initWave();

    }

    private void initWave() {
        float len = mWidth / 8f;
        wavePath.moveTo(0,400);
        wavePath.quadTo(len,300,2*len,400);
        wavePath.quadTo(3*len,500,4*len,400);
        wavePath.quadTo(5*len,300,6*len,400);
        wavePath.quadTo(7*len,500,mWidth,400);

        pathMeasure = new PathMeasure(wavePath, false);
        matrix = new Matrix();
    }

    public WaveShipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWave(canvas);
        drawShip(canvas);
    }

    private void drawWave(Canvas canvas) {
        canvas.drawPath(wavePath,paint);
    }

    private void drawShip(Canvas canvas) {
        matrix.reset();

        float length = pathMeasure.getLength();
        float[] pos=new float[2];
        float[] tan=new float[2];
        pathMeasure.getPosTan(fractor*length,pos,tan);
        float degree= (float) (Math.atan2(tan[1],tan[0])*180f/Math.PI);
        matrix.setRotate(degree,bitmapWidth/2,bitmapHeight/2);
        //postXXX方法不会清除之前的矩阵变幻,如果使用setXXX，则先会清除之前的矩阵变换，所以第二个需要用post
        matrix.postTranslate(pos[0]-bitmapWidth/2,pos[1]-bitmapHeight);

        canvas.drawBitmap(mBitmap,matrix,paint);
    }

    public void startAnimation(){
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fractor = (float) animation.getAnimatedValue();
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
