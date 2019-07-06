package com.xn.materialdesignpro1.event_dispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xn.materialdesignpro1.R;

/**
 * view的事件分发
 *
 * 1.dispatchTouchEvent()
 * 2.onTouchListener--->onTouch方法
 * 3.onTouchEvent
 * 4.onClickListener---->onClick方法
 *
 * 调用顺序：dispatchTouchEvent------》判断OnTouchListener是否为空和onTouch返回值，返回true，那么onTouchEvent不会执行，
 *          返回false，执行onTouchEvent,-----如果执行了onTouchEvent,在ACTION_UP分支中触发onclick事件
 *
 * 结论： 如果OntouchListener的onTouch方法返回了true，消耗了此事件，那么view里面的onTouchEvent就不会被调用了（看下面的源码）。
 *
 * onTouchEvent方法中的ACTION_UP分支中触发了onclick事件监听
 *
 * onTouchListener----onTouch方法返回true，消耗此事件down，但是up事件无法到达onclickListener
 * onTouchListener----onTouch方法返回false，不会消耗此事件
 *
 *
 *  if (li != null && li.mOnTouchListener != null
     && (mViewFlags & ENABLED_MASK) == ENABLED
     && li.mOnTouchListener.onTouch(this, event)) {
     result = true;
     }

     if (!result && onTouchEvent(event)) {
     result = true;
     }
 *
 * Action_down: 0
 * Action_up:  1
 * ACTION_CANCEL: 3
 * ACTION_MOVE : 2
 *
 *
 * 二、ViewGroup+view的事件分发
 * 1、dispatchTouchEvent()
 * 2、onTouchEvent
 * 3、onInterceptTouchEvent()
 * dispatchTransformedTouchEvent
 *
 *
 *
 */
public class EventDispatchActivity extends AppCompatActivity implements
        View.OnClickListener,View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_conflit);

        View layout = findViewById(R.id.layout);
        layout.setOnClickListener(this);
        layout.setOnTouchListener(this);
        View button = findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setOnTouchListener(this);
//        View outLayout = findViewById(R.id.outLayout);
//        outLayout.setOnClickListener(this);
//        outLayout.setOnTouchListener(this);
    }


    @Override
    public void onClick(View v) {

        Log.i("xuning","------onClick----view"+v.toString());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("xuning","------onTouch----action"+event.getAction()+v.toString());
        return false;//返回true，则onTouchEvent()不会执行，自己处理事件，包括onClick都不会执行;返回false，会调用onTouchEvent(),及后续
    }
}
