package com.xn.materialdesignpro1.bessel_curve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 水球加速器
 */
public class WaveBall extends View {

    private Paint paint;
    private Path path;
    private int length;
    private RectF oval;
    private float startAngle;
    private float sweepAngle;
    private boolean userCenter;
    private float radius;
    //颜色需要加深的角度
    private float targetAngle;
    //判断是否在运动
    private boolean isRunning;
    private int state=1;

    public OnAngleColorListener colorListener;
    private int red;
    private int green;
    private int score;
    private Paint waterPaint;
    private float[] firstWaterLine;
    private float[] secondWaterLine;
    private float move;
    private float up=0;
    private float smallRadius;
    private float clipRadius;


    public void setColorListener(OnAngleColorListener colorListener) {
        this.colorListener = colorListener;
    }

    public WaveBall(Context context) {
        super(context);
        init();
    }

    public WaveBall(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        moveWaterLine();
    }
    public void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        path = new Path();
        startAngle=120;
        sweepAngle=300;
        userCenter=false;

        waterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        waterPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //取最小值为正方形的边长
        length = Math.min(width, height);
        oval=new RectF(0,0,length,length);
        radius= length/2;

        //定义两个数组，保存y值
        firstWaterLine=new float[length];
        secondWaterLine = new float[length];

        //设置测量的宽度与高度
        setMeasuredDimension(length, length);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆弧
        canvas.drawArc(oval,startAngle,sweepAngle,userCenter,paint);

        //画刻度线
        drawViewLine(canvas);
        //画水波纹
        drawWave(canvas);
        //绘制小圆和文本的方法
        drawScoreText(canvas);

    }

    private void drawWave(Canvas canvas) {

        //小圆半径
        smallRadius = length/2f-45;
        //绘制水波纹
//        y=Asin(wx+b)+h  //w影响周期，A影响振幅，h影响y的位置，b为初相
        //周期定为view的总宽度
        float mCyclefactorW= (float) (2*Math.PI/(smallRadius*2));
        //得到第一个波的y值
        for (int i = 0; i < length; i++) {
            firstWaterLine[i]=(float)(10*Math.sin(mCyclefactorW*i+move)-up);
        }
        //得到第二个波的y值(第二个波的初相偏左)
        for (int i = 0; i < length; i++) {
            secondWaterLine[i]= (float) (15*Math.sin(mCyclefactorW*i+10+move)-up);
        }
        //裁剪成圆形区域
        Path path = new Path();
        path.reset();
        clipRadius = length / 2f-45;
        path.addCircle(radius,radius, clipRadius, Path.Direction.CCW);
        //用当前要裁剪的区域代替画布中的内容区域
        canvas.clipPath(path, Region.Op.REPLACE);


        canvas.save();
        canvas.translate(0,length/2+ clipRadius);
        for (int i = 0; i < length; i++) {
            canvas.drawLine(i,firstWaterLine[i],i,length,waterPaint);
        }
        for (int i = 0; i < length; i++) {
            canvas.drawLine(i,secondWaterLine[i],i,length,waterPaint);
        }
        canvas.restore();
    }

    private void drawScoreText(Canvas canvas) {
        Paint smallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        smallPaint.setARGB(100,red,green,0);
        canvas.drawCircle(radius,radius, smallRadius,paint);

        //绘制文本
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(smallRadius /2);
        //score通过计算得到
        canvas.drawText(""+score,radius,radius,textPaint);
        //绘制分
        textPaint.setTextSize(smallRadius /6);
        canvas.drawText("分",radius+ smallRadius /2,radius- smallRadius /4,textPaint);
        textPaint.setTextSize(smallRadius /6);
        canvas.drawText("点击优化",radius,radius+ smallRadius /2,textPaint);

    }


    private void drawViewLine(Canvas canvas) {
        canvas.save();
        //移动canvas，x与y移动距离
        canvas.translate(radius,radius);
        //旋转30度
        canvas.rotate(30);

        Paint linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(2);
        linePaint.setAntiAlias(true);
        //每次旋转的角度
        float rotateAngle = sweepAngle / 100;
        //绘制有色 部分的画笔
        Paint targetLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        targetLinePaint.setColor(Color.GREEN);
        targetLinePaint.setStrokeWidth(2);
        //记录绘制过的有色部分的范围
        float hasDraw = 0;

        for (int i = 0; i < 100; i++) {
            if (hasDraw<= targetAngle && targetAngle!=0){
                //计算已经绘制的比例
                float hasDrawPercent = hasDraw / sweepAngle;
                red = 255 -(int) (255 * hasDrawPercent);
                green = (int) (255*hasDrawPercent);

                if (colorListener!=null){
                    colorListener.colorListener(red, green);
                }
                targetLinePaint.setARGB(255, red, green,0);
                //画刻度线
                canvas.drawLine(0,radius,0,radius-40,targetLinePaint);
            }
            else{
                canvas.drawLine(0,radius,0,radius-40,linePaint);
            }

            hasDraw+=rotateAngle;
            canvas.rotate(rotateAngle);
        }

        canvas.restore();
    }


    public void moveWaterLine(){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                //不断改变初相
                move += 1;
                //重新绘制(子线程中调用)
                postInvalidate();
            }
        }, 500, 200);
    }

    public void changeAngle(final float trueAngle){
        if (isRunning){
            return;
        }
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (state){
                    case 1://后退状态
                        isRunning=true;
//                        up-=10;
//                        if (up<0){
//                            up=0;
//                            state=2;
//                        }
                        targetAngle-=3;
                        if (targetAngle <=0){
                            targetAngle=0;
                            //改为前进状态
                            state=2;
                        }
                        break;
                    case 2://前进状态
                        targetAngle+=3;
//                        up+=10;
//                        if (up>=trueAngle){
//                            up=trueAngle;
//                            state=1;//改为后退状态
//                            isRunning=false;
//                            //结束本次运动
//                            timer.cancel();
//                        }
                        if (targetAngle >=trueAngle){//增加到指定宽度
                            targetAngle=trueAngle;
                            state=1;//改为后退状态
                            isRunning=false;
                            //结束本次运动
                            timer.cancel();
                        }
                        break;
                }
                score= (int) (targetAngle/sweepAngle*100);
                up=targetAngle/360*clipRadius*2;
                postInvalidate();
            }
        },500,30);
    }

    public void change(final int trueAngle) {
        if (isRunning) {
            return;
        }
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (state) {
                    case 1://上升
                        isRunning = true;
                        up -= 10;
                        if (up <= 0) {
                            up = 0;
                            state = 2;
                        }
                        break;
                    case 2://下降
                        up += 10;
                        if (up >= trueAngle) {
                            up = trueAngle;
                            state = 1;
                            isRunning = false;
                            timer.cancel();
                        }
                        break;
                    default:
                        break;
                }

                postInvalidate();
            }
        }, 500, 30);
    }


   public interface OnAngleColorListener{
        void colorListener(int red,int green);
    }

}
