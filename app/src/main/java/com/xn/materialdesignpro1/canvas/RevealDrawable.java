package com.xn.materialdesignpro1.canvas;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;

/**
 * 自定义drawable
 */
public class RevealDrawable extends Drawable {

    private final Drawable unselectedDrawable;
    private final Drawable selectedDrawable;

    public RevealDrawable(Drawable unselectedDrawable, Drawable selectedDrawable) {
        this.unselectedDrawable=unselectedDrawable;
        this.selectedDrawable=selectedDrawable;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int level = getLevel();
        Rect bounds = getBounds();

        //绘制灰色部分
        Rect leftRect=new Rect();
        int height=bounds.height();
        int width = bounds.width();
        int leftWidth = (int)(level / 5000f * width);
        Gravity.apply(
                Gravity.LEFT,
                leftWidth,
                height,
                bounds,
                leftRect
        );
        canvas.save();
        canvas.clipRect(leftRect);
        unselectedDrawable.draw(canvas);
        canvas.restore();

        //绘制彩色部分
        int rightWidth=width - leftWidth;
        Rect rightRect=new Rect();
        Gravity.apply(
                Gravity.RIGHT,
                rightWidth,
                height,
                bounds,
                rightRect
        );
        canvas.save();
        //需要先将画布切割
        canvas.clipRect(rightRect);
        //然后再画
        selectedDrawable.draw(canvas);
        canvas.restore();
    }

    @Override
    protected boolean onLevelChange(int level) {
        invalidateSelf();
        return true;
    }

    @Override
    public int getIntrinsicWidth() {
        int max = Math.max(unselectedDrawable.getIntrinsicWidth(), selectedDrawable.getIntrinsicWidth());

        return max;
    }

    @Override
    public int getIntrinsicHeight() {
        int max = Math.max(unselectedDrawable.getIntrinsicHeight(), selectedDrawable.getIntrinsicHeight());

        return max;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
//        super.onBoundsChange(bounds);
        unselectedDrawable.setBounds(bounds);
        selectedDrawable.setBounds(bounds);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
