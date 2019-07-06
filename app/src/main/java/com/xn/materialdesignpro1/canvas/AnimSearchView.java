package com.xn.materialdesignpro1.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AnimSearchView extends View {

    private BaseController controller;
    private Paint paint;

    public AnimSearchView(Context context) {
        super(context);
    }

    public AnimSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        controller.draw(canvas,paint);
    }


    public void startAnim() {
        controller.startAnim();
    }

    public void resetAnim() {
        controller.resetAnim();
    }

    public void setController(BaseController controller) {
        this.controller=controller;
        controller.setView(this);
        invalidate();
    }
}
