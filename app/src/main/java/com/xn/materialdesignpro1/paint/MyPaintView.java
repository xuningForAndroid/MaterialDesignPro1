package com.xn.materialdesignpro1.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyPaintView extends View {

    private final Paint mPaint;

    public MyPaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);//描边
//        mPaint.setStrokeWidth(50f);//描边宽度

//        Path path = new Path();
//        path.moveTo(100,100);
//        path.lineTo(300,100);
//        path.lineTo(100,300);
//        path.close();//变成闭合图形
//        mPaint.setStrokeJoin(Paint.Join.MITER);//线条交汇点形状锐角
//        canvas.drawPath(path,mPaint);
//
//        path.moveTo(100,400);
//        path.lineTo(300,400);
//        path.lineTo(100,600);
//        path.close();//变成闭合图形
//        mPaint.setStrokeJoin(Paint.Join.BEVEL);//正常
//        canvas.drawPath(path,mPaint);
//
//        path.moveTo(100,800);
//        path.lineTo(300,800);
//        path.lineTo(100,1100);
//        path.close();//变成闭合图形
//        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆弧
//        canvas.drawPath(path,mPaint);
//        -----------------------------------2.绘制文字----------------------------------------------------
//        mPaint.getLetterSpacing()//字符间距
//        mPaint.getFontSpacing()//字符行间距
//        mPaint.setUnderlineText();
//        mPaint.setTextSize();
        //计算指定长度的字符串（字符长度、字符个数、显示的时候真实的长度）
//        float[] measuredWidth=new float[1];
        //获取的是字符的长度结果为5: 动 脑 ab cd ef
//        int breakText = mPaint.breakText("动脑abcdef", true, 200, measuredWidth);

//        ===================基线问题================================
        //参数1：文本内容
        //参数2：文本开始的x坐标
        //参数3：文本基线y坐标(此处有坑)绘制文本时需要先计算
//        canvas.drawText(str,x,y,paint);

        float myY=100f;//指定我的顶点纵坐标为100
        //以给定坐标做线
        canvas.drawLine(0f,100f,2000f,100f,mPaint);

        String text="许宁xuning";
        mPaint.setTextSize(150);

        mPaint.setTextAlign(Paint.Align.LEFT);//文字与外边框左侧对齐
        //不经过计算绘制
        mPaint.setColor(Color.YELLOW);
        canvas.drawText(text,0,myY,mPaint);

        //实例1：指定文本的左上角顶点坐标（x,y），绘制文本
        mPaint.setColor(Color.GREEN);
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
//        metrics.ascent;
//        metrics.bottom;
//        metrics.descent;
//        metrics.top
        float baseLineY = myY + (- metrics.top);
        canvas.drawText(text,0,baseLineY,mPaint);
        //实例2：以指定坐标（x,y）为中线，绘制文本
        mPaint.setColor(Color.BLUE);
        float height= (metrics.bottom-metrics.top)/2-metrics.bottom;
        baseLineY = myY+height;
        canvas.drawText(text,0,baseLineY,mPaint);

    }
}
