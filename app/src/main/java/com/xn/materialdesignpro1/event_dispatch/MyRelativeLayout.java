package com.xn.materialdesignpro1.event_dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout {

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("xuning","--dispatchTouchEvent---MyRelativeLayout--action"+event.getAction());
        super.dispatchTouchEvent(event);
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("xuning","--onInterceptTouchEvent---MyRelativeLayout--action"+ev.getAction());
        super.onInterceptTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("xuning","--onTouchEvent---MyRelativeLayout--action"+event.getAction());
        return super.onTouchEvent(event);
    }
}
