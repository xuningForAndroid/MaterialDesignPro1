package com.xn.materialdesignpro1.anim_behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.xn.materialdesignpro1.R;

public class FabBehavior extends FloatingActionButton.Behavior {

    private boolean visible=true;
    private Toolbar toolbar;

    /**
     * @param context
     * @param attrs
     */
    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 观察的view开始滑动
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param axes   滑动关联的轴，只关心垂直的滑动
     * @param type
     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull FloatingActionButton child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        if (toolbar==null){
            toolbar = coordinatorLayout.findViewById(R.id.toolbar);
        }
        //处理垂直方向的滑动
        return axes== ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     * 当观察的view滑动的时候调用
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     * @param type
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull FloatingActionButton child,
                               @NonNull View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed,
                               int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

        if (dyConsumed >0 && visible){//dyConsumed>0向上滑动
            //hide
            visible=false;
            onHide(child, toolbar);

        }else if (dyConsumed <0 && !visible){
            //show
            visible=true;
            onShow(child, toolbar);
        }



    }

    public void onShow(FloatingActionButton fab,Toolbar toolbar) {
        // 显示动画--属性动画
//        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
//        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

        ViewCompat.animate(toolbar).alpha(1).setInterpolator(new DecelerateInterpolator(3));
        ViewCompat.animate(fab).alpha(1).setInterpolator(new DecelerateInterpolator(3));

    }

    public void onHide(FloatingActionButton fab,Toolbar toolbar) {
//        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
//        int bottom = fab.getBottom();
//        fab.animate().translationY(fab.getHeight()+bottom).setInterpolator(new AccelerateInterpolator(3));

        //透明度动画
        ViewCompat.animate(toolbar).alpha(0).setInterpolator(new DecelerateInterpolator(3));
        ViewCompat.animate(fab).alpha(0).setInterpolator(new DecelerateInterpolator(3));

    }
}
