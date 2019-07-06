package com.xn.materialdesignpro1.event_dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class SlidingQQItem extends LinearLayout {

    private Scroller mScroller;
    private View mHide;
    private View mShow;
    private float startX;
    private float startY;
    private float dx;
    private float dy;

    public SlidingQQItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initThis();
    }

    private void initThis() {
        setOrientation(HORIZONTAL);
        Context context = getContext();
        mScroller = new Scroller(context,null,true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mShow = getChildAt(0);
        mHide = getChildAt(1);
        //错误点:此处获取的宽度为0，因为这个方法调用顺序是在setContentView之后，onMeasure之前
//        mHide.getWidth();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                super.dispatchTouchEvent(ev);

                return true;
            case MotionEvent.ACTION_MOVE:
                dx = ev.getX() - startX;
                dy = ev.getY() - startY;
                //dx < 0 向左滑动，dx>0 向右滑动
                if (Math.abs(dx) - Math.abs(dy) > ViewConfiguration.getTouchSlop()){
                    //滑动距离不能大于rightwidth
                    /**
                     * getScrollX值：
                     * 下面的部分不太懂
                     */
//                    Log.i("xuning","getScrollX:"+getScrollX()+",mHide:"+mHide.getWidth()+",dx:"+(-dx));
                    if (getScrollX() +(-dx) > mHide.getWidth()|| getScrollX()+(-dx)<0 ){
                        //第一个条件是左向右滑动，第二个条件是右向左
                        return true;
                    }
                    scrollBy((int) -dx,0);//表示的是移动增量，为正时代表右向左或者下向上
                    startX= ev.getX();
                    startY =ev.getY();
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                //判断当前手指松开是往左滑动还是向右滑动
                int offset = ((float)getScrollX() / (float) mHide.getWidth()) > 0.5f ? mHide.getWidth() - getScrollX() : -getScrollX();
//                Log.i("xuning","offset:"+offset+",mHideWidth："+mHide.getWidth()+",getScrollX："+getScrollX());
                //开始自动滑动的位置是滑动完成瞬间的位置
                mScroller.startScroll(getScrollX(),getScrollY(),offset,0);
                invalidate();
                startX=0;
                startY=0;
                dx=0;
                dy=0;
                break;
        default:
            break;
        }
        return super.dispatchTouchEvent(ev);
    }
    private boolean isOpen;

    /**
     * 关闭右侧
     */
    public void closeDelete(){
        isOpen =false;
        //这两种方法也可以实现，但是效果不平滑
//        setScrollX(0);
//        scrollTo(0,0);
//        Log.i("xuning","getScrollX:"+getScrollX()+",getScrollY:"+getScrollY()+",mHide:"+mHide.getWidth());
        mScroller.startScroll(getScrollX(),getScrollY(),-mHide.getWidth(),0);
        invalidate();
    }

    //在开启滑动情况下mScroller.startScroll，滑动的过程当中此方法会被不断调用
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){//只要动画不结束，一直返回true
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}
