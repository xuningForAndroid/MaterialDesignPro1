package com.xn.materialdesignpro1.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * 演示画布恢复
 *
 * save与restore方法是成对出现的
 * save表示将当前画布状态压入栈中，restore表示将栈顶的画布状态弹栈
 */
public class CanvasRestoreView extends View {
    public CanvasRestoreView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.GREEN);
        //剪切之前保存画布状态(保存到画布栈里面，涉及到一个栈结构)
        canvas.save();
        canvas.clipRect(new Rect(100,100,400,400));
        canvas.drawColor(Color.BLUE);

        //恢复画布保存的状态
        canvas.restore();
        Paint paint=new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(100,100,100,paint);
    }
}
