package com.xn.materialdesignpro1.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * 饼状图
 */
public class PieChartView extends View {

    private int[] colors={0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private float mStartAngle=0;
    private List<PieData> mData;
    private int mWidth,mHeight;
    private Paint paint;

    public PieChartView(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
    }

    public void setData(List<PieData> data){
        this.mData=data;
        initData(mData);
        invalidate();
    }

    private void initData(List<PieData> mData) {
        if (mData==null || mData.size()==0)return;

        float sumValue = 0;

        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            sumValue+=pie.getValue();
            pie.setColor(colors[i]);
        }

        float sumAngle=0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);

            float percent = pie.getValue() / sumValue;
            float angle = percent * 360;
            pie.setPercent(percent);
            pie.setAngle(angle);
            sumAngle+=angle;
        }
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mData==null)return;
        float currentAngle = mStartAngle;
        canvas.translate(mWidth/2,mHeight/2);
        float radius=Math.min(mWidth,mHeight)/2;
        RectF oval=new RectF(-radius,-radius,radius,radius);
        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            paint.setColor(pieData.getColor());
            canvas.drawArc(oval, currentAngle,pieData.getAngle(),true,paint);
            currentAngle+=pieData.getAngle();

        }
    }

    public void setStartAngle(float angle){
        this.mStartAngle=angle;
        invalidate();
    }



}
