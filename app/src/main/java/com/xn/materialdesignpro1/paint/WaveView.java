package com.xn.materialdesignpro1.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 用paint实现view的水波纹效果
 */
public class WaveView extends View {

    private final Paint paint;
    private final float centerX=100;
    private final float centerY=100;
    private final int[] colors={Color.GREEN,Color.RED,Color.BLUE,Color.YELLOW,Color.GRAY};
    private float radius=50;
    private final RadialGradient radialGradient;
    private final Matrix matrix;
    private float delt;

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        radialGradient = new RadialGradient(centerX, centerY, 50, colors, null, Shader.TileMode.CLAMP);
        matrix = new Matrix();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆环
//        matrix.setTranslate(10,10);
//        radialGradient.setLocalMatrix(matrix);
        paint.setShader(radialGradient);
        if (delt > radius)
            delt =0;

        delt+=10;
        canvas.drawCircle(centerX,centerY,delt,paint);


        postInvalidateDelayed(500);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();

        return true;
    }
}
