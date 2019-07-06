package com.xn.materialdesignpro1.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MyCanvasView extends View {

    private final Paint paint;
    private final int color;

    public MyCanvasView(Context context) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        color = Color.argb(255, 250, 117, 37);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);

//        Path path = new Path();
//        //画贝塞尔曲线  三阶贝塞尔曲线
////        path.cubicTo(250,200,280,400,380,440);
//
//        //圆角矩形----实现滴滴图标
//        RectF rectF = new RectF(100, 100, 500, 400);
//        //四个角分别在x与y方向上的半径
//        float[] radii={0,0,0,0,200,200,200,200};
//        path.addRoundRect(rectF,radii, Path.Direction.CCW);
//
//        canvas.drawPath(path,paint);
//
//        path.reset();
//        paint.reset();
//        paint.setColor(Color.WHITE);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(50);
//        path.addRect(490,100,500,110,Path.Direction.CCW);
//        canvas.drawPath(path, paint);
//========================================================================
        //Region区域,创建一个矩形区域
//        Region region = new Region(100, 100, 200, 200);
//        RegionIterator regionIterator = new RegionIterator(region);
//        Rect rect=new Rect();
//        while (regionIterator.next(rect)){
//            canvas.drawRect(rect,paint);
//        }
//        region.union()
//        region.op()
        //=================================================================
        //==================canvas变换技巧==============================
        //1、平移 translate

        //2、缩放 rotate

        //3、旋转
//        Rect rect=new Rect(100,100,400,500);
//        canvas.drawRect(rect,paint);
//        paint.setColor(Color.RED);
//        canvas.rotate(45,100,100);
//        //相当于重新创建图层画布，但是之前的旋转等操作还会保留
//        canvas.drawRect(rect,paint);

        //4、斜拉画布skew
//        Rect rect=new Rect(100,100,400,500);
//        canvas.drawRect(rect,paint);
//        paint.setColor(Color.RED);
//        //x与y方向上的倾斜度，x轴方向倾斜60度，tan60=根号3=1.73
//        canvas.skew(1.73f,0);
//        //相当于重新创建图层画布，但是之前的旋转等操作还会保留
//        canvas.drawRect(rect,paint);

        //5、裁剪画布clip
        Rect rect=new Rect(100,100,400,500);
        canvas.drawRect(rect,paint);
        paint.setColor(Color.RED);
        canvas.clipRect(new Rect(200,200,300,300));
        //相当于重新创建图层画布，但是之前的旋转等操作还会保留
        canvas.drawColor(Color.YELLOW);


    }
}
