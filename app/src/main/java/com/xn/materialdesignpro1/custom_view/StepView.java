package com.xn.materialdesignpro1.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xn.materialdesignpro1.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 进度展示view
 */
public class StepView extends View {

    private Paint paint;
    private int mSelectColor=Color.BLUE;
    private int mNormalColor=Color.GRAY;
    private int mSteps=3;
    private int mWidth;
    private int mHeight;
    private int mStepLength;
    private Paint textPaint;

    int mCurrentStep=0;
    List<String> mStepTitles=new ArrayList<>();
    private Paint mTitlePaint;

    public StepView(Context context) {
        this(context,null);
    }

    public StepView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context,attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTitlePaint.setTextSize(30);
        mTitlePaint.setStrokeWidth(8);
        mTitlePaint.setColor(Color.BLACK);
        textPaint.setColor(Color.WHITE);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(8);
        textPaint.setTextSize(20);
        textPaint.setStrokeWidth(5);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StepView);
        mNormalColor = array.getColor(R.styleable.StepView_normalColor, Color.GRAY);
        mSelectColor = array.getColor(R.styleable.StepView_selectedColor, Color.GREEN);
        mSteps = array.getInteger(R.styleable.StepView_steps, 3);
        array.recycle();
        for (int i = 0; i < mSteps; i++) {
            mStepTitles.add("步骤"+(i+1));
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mStepLength = (mWidth-100)/(mSteps-1);
        Log.i("xuning","width:"+mWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStep(canvas);
    }

    private void drawStep(Canvas canvas) {
        if (mCurrentStep>0){

            int startX=60;
            int stopX=0;
            for (int i = 0; i < mSteps ; i++) {
                if (i< mCurrentStep){
                    paint.setColor(mSelectColor);
                }else {
                    paint.setColor(mNormalColor);
                }
                stopX=startX+mStepLength;
                canvas.drawCircle(startX,20,20,paint);
                canvas.drawText(i+1+"",startX-5,25,textPaint);
                float v = mTitlePaint.measureText(mStepTitles.get(i));
                canvas.drawText(mStepTitles.get(i),startX-v/2,90,mTitlePaint);
                if (i< mSteps-1)
                    canvas.drawLine(startX+20,20,stopX-20,20,paint);
                startX+=mStepLength;
            }

        }else {
            paint.setColor(mNormalColor);
            int startX=60;
            int stopX=0;
            for (int i = 0; i < mSteps ; i++) {
                stopX=startX+mStepLength;
                canvas.drawCircle(startX,20,20,paint);
                canvas.drawText(i+1+"",startX-5,25,textPaint);
                float v = mTitlePaint.measureText(mStepTitles.get(i));
                canvas.drawText(mStepTitles.get(i),startX-v/2,90,mTitlePaint);
                if (i< mSteps-1)
                    canvas.drawLine(startX+20,20,stopX-20,20,paint);
                startX+=mStepLength;
            }

        }
    }

    public void nextStep(){
        mCurrentStep++;
        invalidate();
    }
    public int getSteps(){
        return mSteps;
    }
    public int getCurrentStep(){
        return mCurrentStep;
    }

    public void setTitles(List<String> datas){
        this.mStepTitles=datas;
        invalidate();
    }

}
