package com.xn.materialdesignpro1.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xn.materialdesignpro1.R;

/**
 * 放大镜效果
 */
public class ZoomImageView extends View {

    private Bitmap bitmap;
    private ShapeDrawable drawable;
    //放大倍数
    private static final int FACTOR = 2;
    //放大镜的半径
    private static final int RADIUS = 100;
    private Matrix matrix = new Matrix();
    private final BitmapShader shader;

    public ZoomImageView(Context context,@Nullable AttributeSet attrs) {
        super(context,attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.qingchunmeinv);
        Bitmap bmp = bitmap;
        //放大后的整个图片
        bmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth()*FACTOR, bmp.getHeight()*FACTOR, true);
        //制作一个圆形的图片(放大的局部)，盖在canvas上面
        shader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
        drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setShader(shader);
        //切出矩形区域---用于绘制圆（内切圆）
        drawable.setBounds(0, 0, RADIUS*2, RADIUS*2);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        //画制作好的圆形图片
        drawable.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        //移动放大区域，，，像素点相对画布向右、向下移动的sp值
        matrix.setTranslate(RADIUS-x*FACTOR,RADIUS-y*FACTOR);
        Log.i("xuning","RADIUS："+RADIUS+",x:"+x+",y:"+y+",FACTOR:"+FACTOR);
        drawable.getPaint().getShader().setLocalMatrix(matrix);
        //重绘区域的确定
        drawable.setBounds(x-RADIUS,y-RADIUS,x+RADIUS,y+RADIUS);
        //重绘
        invalidate();


//        // 将放大的图片往相反的方向挪动
//        mMatrix.setTranslate(RADIUS - x * FACTOR, RADIUS - y *FACTOR);
//        mShapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);
//        // 切出手势区域点位置的圆
//        mShapeDrawable.setBounds(x-RADIUS,y - RADIUS, x + RADIUS, y + RADIUS);
//        invalidate();
        return true;
    }
}
