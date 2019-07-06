package com.xn.materialdesignpro1.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xn.materialdesignpro1.R;

public class GradientView extends View {

    private final Paint paint;
    private  Bitmap bitmap;
    private  int width;
    private  int height;
    private int[] colors={Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};

    public GradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.avatar3);
        bitmap = bitmapDrawable.getBitmap();
        //获取原图大小
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //渲染
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//
        float max = Math.max(width, height);
        float min = Math.min(width, height);
        //得到拉伸比例
        float scalePercent = max*1.0f / min;
        //设置像素矩阵，来调节大小，解决宽高不一致的问题
        Matrix matrix = new Matrix();
        if (width < height){
            matrix.setScale(scalePercent,1f);
        }else {
            matrix.setScale(1f,scalePercent);
        }
//        shader.setLocalMatrix(matrix);
//        //给paint设置渲染
//        paint.setShader(shader);
//        paint.setAntiAlias(true);
//        //获取中心点的坐标
//        float centerX = max/2f;
//        float centerY = max/2f;
//        //获取半径
//        float radius = max/2f;
        //画圆形头像
//        canvas.drawCircle(centerX,centerY,radius,paint);
        //画椭圆,,注意：画椭圆时不需要缩放
//        RectF oval=new RectF(0,0,width,height);
//        canvas.drawOval(oval,paint);

        //另一种实现圆形头像的方法
//        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
//        shapeDrawable.getPaint().setShader(shader);
//        shapeDrawable.setBounds(new Rect(0,0,(int) max,(int) max));
//        shapeDrawable.draw(canvas);

        //////////////////////linearGradient//////////////////////////////////////
        //自定义取色器
        //线性渲染
        LinearGradient linearGradient = new LinearGradient(0, 0, 400, 0, colors, null, Shader.TileMode.CLAMP);
//        paint.setShader(linearGradient);
//        canvas.drawRect(new Rect(0,0,400,400),paint);

        //环形渲染----水波纹扩散效果 ，雷达效果
        RadialGradient radialGradient = new RadialGradient(300, 300, 100, colors, null, Shader.TileMode.CLAMP);
//        paint.setShader(radialGradient);
//        canvas.drawCircle(300,300,100,paint);

        //梯度渲染----雷达扫描，手机卫士垃圾扫描效果
        SweepGradient sweepGradient = new SweepGradient(300, 300, colors, null);
        paint.setShader(sweepGradient);
        canvas.drawCircle(300,300,100,paint);

        //组合渲染,渲染效果组合起来
//        ComposeShader composeShader = new ComposeShader(radialGradient, sweepGradient, PorterDuff.Mode.SRC_OVER);
//        paint.setShader(composeShader);
//        canvas.drawCircle(300,300,100,paint);

    }
}
