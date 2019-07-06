package com.xn.materialdesignpro1.canvas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

//放大镜的绘制控制器
public class BigMirrorController extends BaseController {

    public float cx;
    public float cy;
    public float cr;
    private RectF oval;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.GREEN);
        switch (state){
            case STATE_NORMAL:
                drawNormalView(canvas,paint);
                break;
            case STATE_START:
                drawStartView(canvas,paint);
                break;
            case STATE_STOP:
                drawResetView(canvas,paint);
                break;

        }
    }

    private void drawResetView(Canvas canvas, Paint paint) {
        canvas.save();
        if (resetPro> 0.5){//绘制放大镜和手柄
            /**
             *  0~ -360
             * 0.5 ~ 1---->0-1
             *
             */
            canvas.drawArc(oval,
                    45,
                    -360*(2*resetPro-1f),
                    false,
                    paint);

            canvas.drawLine(
                    oval.right+cr-j,
                    oval.bottom+cr-j,
                    oval.right-j,
                    oval.bottom-j,
                    paint);

        }else {//绘制手柄
            /**
             * 实际需要
             *0~1，
             * 现在条件0~0.5
             */
            canvas.drawLine(
                    oval.right-j+cr,
                    oval.bottom-j+cr,
                    oval.right-j+cr*(resetPro*2),
                    oval.bottom-j+cr*(resetPro*2),
                    paint
            );
        }
        //绘制底部横线
        /**
         * 0~1
         * 需要的是x~0
         */
        canvas.drawLine(

                oval.right-j+cr,
                oval.bottom-j+cr,
                (oval.right-j+cr)*(1-resetPro*0.01f),
                oval.bottom-j+cr,
                paint
        );
        canvas.restore();

        //将就行区域向左侧平移
        oval.left = cx-cr+(1-resetPro)*250;
        oval.right=cx+cr+(1-resetPro)*250;
        oval.top=cy-cr;
        oval.bottom=cy+cr;

    }

    private void drawStartView(Canvas canvas, Paint paint) {
        canvas.save();
        if (pro<= 0.5){//绘制放大镜和手柄
            /**
             * -360 ~ 0
             * 0 ~ 0.5---->0-1
             *
             */
            canvas.drawArc(oval,
                    45,
                    360*(2*pro-1f),
                    false,
                    paint);

            canvas.drawLine(
                    oval.right-j,
                    oval.bottom-j,
                    oval.right+cr-j,
                    oval.bottom+cr-j,
                    paint);

        }else {//绘制手柄
            /**
             * 实际需要
             *0~1，
             * 现在条件0.5-1
             */
            canvas.drawLine(
                oval.right-j+cr*(pro*2-1),
                    oval.bottom-j+cr*(pro*2-1),
                    oval.right-j+cr,
                    oval.bottom-j+cr,
                    paint
            );
        }
        //绘制底部横线
        canvas.drawLine(
                (oval.right-j+cr)*(1-pro*0.8f),
                oval.bottom-j+cr,
                oval.right-j+cr,
                oval.bottom-j+cr,
                paint
        );
        canvas.restore();

        //将就行区域向右平移
        oval.left = cx-cr+pro*250;
        oval.right=cx+cr+pro*250;
        oval.top=cy-cr;
        oval.bottom=cy+cr;

    }

    private void drawNormalView(Canvas canvas, Paint paint) {
        float viewWidth = getViewWidth();
        float viewHeight = getViewHeight();
        cx=viewWidth/2f;
        cy= viewHeight/2f;
        cr=viewWidth/20f;
        canvas.save();
        oval = new RectF(cx-cr,cy-cr,cx+cr,cy+cr);
        //需要添加旋转轴，否则位置可能出现错误
        canvas.rotate(45,cx,cy);
        canvas.drawLine(cx+cr,cy,cx+2*cr,cy,paint);
        canvas.drawArc(oval,
                0,
                360,
                false,
                paint);
        canvas.restore();
    }

    @Override
    public void startAnim() {
        state= STATE_START;
        startViewAnimation();
    }

    @Override
    public void resetAnim() {
        state = STATE_STOP;
        resetViewAnimation();
    }
}
