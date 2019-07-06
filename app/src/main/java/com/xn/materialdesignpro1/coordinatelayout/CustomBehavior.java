package com.xn.materialdesignpro1.coordinatelayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

/**
 * 自定义behavior
 */
public class CustomBehavior extends Behavior<View> {

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     *  用来规定需要监听哪些控件或者容器的状态(需要知道1.监听谁，2.什么状态)
     * @param parent  父容器
     * @param child   需要监听dependency的视图，观察者
     * @param dependency  要监听的view  被观察者
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //也可以用tag或者id来定
        //规定谁是被观察者
        return dependency instanceof TextView ||super.layoutDependsOn(parent, child, dependency);
    }

    /**
     * 被观察的view发生状态改变时的监听
     * 可以做一些联动动画
     * @param parent
     * @param child
     * @param dependency 被监听者
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int offset = dependency.getTop() - child.getTop();
        //垂直方向上移动
        ViewCompat.offsetTopAndBottom(child,offset);
        //旋转动画
        ViewCompat.animate(child).rotation(20*offset).setInterpolator(new AccelerateDecelerateInterpolator()).start();
        return true;
    }
}
