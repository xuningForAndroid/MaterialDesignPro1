package com.xn.materialdesignpro1.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;

/**
 * 在TextView中一般有两种颜色，一个是背景颜色，一个是字体颜色，
 * Paint控制背景颜色，TextPaint控制字体颜色
 *
 * 一般来说绘制图像是设置Paint属性然后在Canvas上操作并显示出来，
 * 但是通过getPaint()获得TextPaint对象后可以直接进行操作无需通过Canvas即可显示效果。
 */
public class LinearGradientTextView extends AppCompatTextView {

    private TextPaint paint;
    private float showWidth;
    private int[] colors={0x22ffffff,0xffffffff,0x22ffffff};
    private  Matrix matrix;
    private LinearGradient linearGradient;
    private float translationX;
    private float textWidth;
    private float deltx;

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint=getPaint();
        String text = getText().toString();
        //整个文字显示的宽度
        textWidth = paint.measureText(text);
        //规定显示两个文字的宽度
        showWidth =2* paint.measureText(text) / text.length();
        deltx =showWidth;
        //用于制作显示渐变效果
        linearGradient = new LinearGradient(-showWidth, 0, 0, 0, colors, new float[]{0f, 0.5f, 1f}, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("xuning","deltx:"+deltx+",textWidth:"+textWidth);

        translationX+=deltx;

        if (translationX > textWidth+1 || translationX < 1){
            deltx =- deltx;
        }
        Log.i("xuning","translationX:"+translationX);
        //不断平移
        matrix.setTranslate(translationX,0);
        linearGradient.setLocalMatrix(matrix);

        //不断刷新，使得页面不断重绘
        postInvalidateDelayed(50);
    }
}
