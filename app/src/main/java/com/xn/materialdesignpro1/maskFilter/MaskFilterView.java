package com.xn.materialdesignpro1.maskFilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.xn.materialdesignpro1.R;

public class MaskFilterView extends View {

    private final Bitmap bitmap;
    private float progress;
    private Paint paint;

    public MaskFilterView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon3);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //关闭硬件加速，否则滤镜无效果
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        //===================模糊遮罩============================
//        paint.setColor(Color.RED);
//        RectF rect=new RectF(0,0,200,200);
//        canvas.drawRect(rect,paint);
//        //设置模糊遮罩
//        paint.setMaskFilter(new BlurMaskFilter(50,BlurMaskFilter.Blur.SOLID));
//        RectF rect1=new RectF(300,0,500,200);
//        canvas.drawRect(rect1,paint);


        //======================浮雕遮罩===================================
//        canvas.drawBitmap(bitmap,null,new RectF(0,0,200,200),paint);
//        paint.setMaskFilter(new EmbossMaskFilter(new float[]{30,30,30},0.5f,5f,20f));
//        canvas.drawBitmap(bitmap,null,new RectF(300,0,500,200),paint);

        //======================颜色RGB的滤镜处理==============================
        paint.reset();
        canvas.drawBitmap(bitmap,null,new RectF(0,0,400,400), paint);
        //红色分量改为原来的二倍，绿色分量增加100
//        float[] matrix=new float[]{
//            2f,0,0,0,0,
//            0,1f,0,0,100,
//            0,0,1,0,0,
//            0,0,0,1,0
//        };
        //只要红色分量
//        float[] matrix=new float[]{
//                1,0,0,0,0,
//                0,0,0,0,0,
//                0,0,0,0,0,
//                0,0,0,1,0
//        };
        //反相效果（相机底片）
//        float[] matrix=new float[]{
//                -1f,0,0,0,255,
//                0,-1f,0,0,255,
//                0,0,-1f,0,255,
//                0,0,0,1,0
//        };
        //美颜，颜色增强-----色彩缩放//colorMatrix.setScale(1,1,1.4f,1);//可以直接设置缩放
//        float[] matrix=new float[]{
//                1.3f,0,0,0,0,
//                0,1.3f,0,0,0,
//                0,0,1.3f,0,0,
//                0,0,0,1,0
//        };
        //处理图像为黑白色//可以直接用饱和度设置colorMatrix.setSaturation(0),0~1的值，饱和度越大，色彩越强烈
//        float[] matrix=new float[]{
//                0.213f,0.715f,0.072f,0,0,
//                0.213f,0.715f,0.072f,0,0,
//                0.213f,0.715f,0.072f,0,0,
//                0,0,0,1,0
//        };
        //红绿反色
//        float[] matrix=new float[]{
//                0,1f,0,0,0,
//                1f,0,0,0,0,
//                0,0,1,0,0,
//                0,0,0,1,0
//        };
        //复古风格
//        float[] matrix=new float[]{
//                1/2f,1/2f,1/2f,0,0,
//                1/3f,1/3f,1/3f,0,0,
//                1/4f,1/4f,1/4f,0,0,
//                0,0,0,1,0
//        };

//        ColorMatrix colorMatrix = new ColorMatrix(matrix);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(progress);
        //色彩旋转
        /**
         * axis,代表绕哪个轴旋转0,1,2分别代表红绿蓝
         * degree 旋转的角度
         */
//        colorMatrix.setRotate(0,degree);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap,null,new RectF(0,600,400,1000), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                progress+=0.2f;
                invalidate();
                break;
        }
        return true;
    }
}
