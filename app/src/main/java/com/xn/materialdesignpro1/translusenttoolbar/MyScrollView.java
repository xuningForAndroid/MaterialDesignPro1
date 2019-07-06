package com.xn.materialdesignpro1.translusenttoolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private ScrollTranslusentListener translusentListener;
    private float y1;

    public void setTranslusentListener(ScrollTranslusentListener translusentListener) {
        this.translusentListener = translusentListener;
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (translusentListener!=null){
            float scrollY = getScrollY();//上下滑动距离,这是一个0以上的正数值，越向上值越大
            Log.i("scrollY",scrollY+"");
            int screen_height = getContext().getResources().getDisplayMetrics().heightPixels;
            Log.i("scrollY",screen_height+"");
            if (scrollY<=(screen_height/3f)){//规定从0到屏幕高度的1/3处滑行区间有效，会使得toolbar改变
                float alpha =1- scrollY / (screen_height / 3f);//
                translusentListener.onTranslusent(alpha);
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                y1 = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                Log.i("dyConsumed",y-y1+"");

                break;
        }
        return super.onTouchEvent(ev);
    }
}
