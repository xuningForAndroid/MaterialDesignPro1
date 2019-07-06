package com.xn.materialdesignpro1.event_dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/**
 * 自定义viewpager
 */
public class CustomViewPager extends ViewGroup {


    private int mTouchSlop;
    private float mLastMoveX;//记录下移动最后一次的位置
    private float downX;
    private int leftBound;
    private int rightBound;
    private float moveX;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewpager();
    }

    private void initViewpager() {
        Context context = getContext();
        final ViewConfiguration configuration = ViewConfiguration.get(context);

        //系统规定的最小偏移量
        mTouchSlop = configuration.getScaledPagingTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        if (changed){

            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                child.layout(child.getMeasuredWidth()*i,0,child.getMeasuredWidth()*(i+1),child.getMeasuredHeight());
                child.setClickable(true);
            }
            leftBound = getChildAt(0).getLeft();
            rightBound = getChildAt(childCount - 1).getRight();
//        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();
                mLastMoveX = downX;
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = ev.getRawX();
                float xDiff = Math.abs(moveX - downX);
                mLastMoveX = moveX;
                if (xDiff > mTouchSlop){//滑动距离有效
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                /**
                 * 如何有滑动的效果？
                 * View.scrollTo(x,y);
                 * 		让View相对于它初始的位置滚动一段距离。
                 * View.scrollBy(x,y);
                 * 		让View相对于它现在的位置滚动一段距离。
                 * 注意：上面两种方法都是滑动View里面的内容，即里面的所有子控件。
                 */
                moveX =event.getRawX();
                int scrollDx = (int) (mLastMoveX - moveX);
                if (scrollDx +getScrollX() < leftBound){
                    scrollTo(leftBound,0);//相对于初始位置滑动距离
                    return true;
                }else if (scrollDx + getScrollX()+getWidth() > rightBound){
                    scrollTo(rightBound - getWidth(),0);
                    return true;
                }
                scrollBy(scrollDx,0);//相对于当前位置滑动距离
                mLastMoveX =moveX;
                break;
        }
        return super.onTouchEvent(event);
    }
}
