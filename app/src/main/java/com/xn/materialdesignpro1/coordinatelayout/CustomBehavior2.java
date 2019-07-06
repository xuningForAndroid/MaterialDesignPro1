package com.xn.materialdesignpro1.coordinatelayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

public class CustomBehavior2 extends CoordinatorLayout.Behavior {
    public CustomBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *
     * @param coordinatorLayout
     * @param child      是滑动关联的view
     * @param directTargetChild  产生滑动的view
     * @param target
     * @param axes
     * @param type
     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //监听垂直方向滑动
        return ViewCompat.SCROLL_AXIS_VERTICAL==axes && directTargetChild instanceof NestedScrollView;
    }



    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
       //垂直、水平方向平移
//        ViewCompat.offsetTopAndBottom(child,-dyConsumed);
//        ViewCompat.offsetLeftAndRight(child,-dyConsumed);
//        child.setScrollY();
//        child.animate().translationY().start();
//        ViewCompat.animate(child).translationY().setInterpolator(new AccelerateDecelerateInterpolator()).start();
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        child.setScrollY(target.getScrollY());
    }

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        ((NestedScrollView)child).fling(target.getScrollY());
        return super.onNestedFling(coordinatorLayout,child,target,velocityX,velocityY,consumed);
    }
}
