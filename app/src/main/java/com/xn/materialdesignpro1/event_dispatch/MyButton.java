package com.xn.materialdesignpro1.event_dispatch;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyButton extends AppCompatButton {

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("xuning","------dispatchTouchEvent--button--action"+event.getAction());
        super.dispatchTouchEvent(event);//默认值就是true，
        return true;//当返回false，则不管onTouchEvent返回什么，都是将事件交给父容器处理
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("xuning","------onTouchEvent--button--action"+event.getAction());
        // 必须要执行的一行代码super.onTouchEvent(event);
        //返回true，则自己消耗down及以后所有事件和当前控件的click事件
        return super.onTouchEvent(event);
    }
}
