package com.xn.materialdesignpro1.custom_anim_frame;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

/**
 * 自定义ScrollView可监听内部控件滑动
 */
public class MyCustomScrollView extends ScrollView {

    private MyScrollContent mContent;
    private int mScrollChildCount;

    public MyCustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContent = (MyScrollContent) getChildAt(0);
        mScrollChildCount = mContent.getChildCount();
    }

    /**
     *
     * @param l
     * @param t   当前ScrollView滑动出屏幕外的距离（垂直方向上滑动距离）
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int mScrollViewHeight = getHeight();//获取scrollView在屏幕中显示的高度,不确切的说就是屏幕高度
//        getMeasuredHeight()//获取的是组件的实际大小，包括显示部分和超出屏幕的部分
        Log.i("mScrollViewHeight",mScrollViewHeight+"scrolView高度"+mContent.getHeight());
        for (int i = 0; i < mScrollChildCount; i++) {
            View child = mContent.getChildAt(i);

            if (child instanceof MyScrollChildFramLayout){
                MyScrollChildFramLayout frameChild= (MyScrollChildFramLayout) child;
                //获取child距离ScrollView顶部的距离
                int childToScrollTopHeight = child.getTop();
                //scrollView 滚动出屏幕的距离
                int mScrollHeight = t;
                //当前子view距离顶部的距离
                int childToWindowTopHeight = childToScrollTopHeight - mScrollHeight;
                //执行动画的时机是当前子view距离屏幕顶部的高度小于ScrollView的高度
                if (childToWindowTopHeight <= mScrollViewHeight ){
                    //当前子view距离屏幕底部的距离
                    int childToBottomGap = mScrollViewHeight - childToWindowTopHeight;
                    float ratio=caculateRatio(childToBottomGap,child.getHeight());
                    //设置滚动动画
                    frameChild.onChanged(ratio);
                }else {
                    frameChild.onReset();
                }
            }else {
                continue;
            }

        }
    }

    /**
     * 根据当前view距离屏幕底部高度与自身高度，计算一个执行百分比 0到1之间
     * @param childToBottomGap
     * @param height
     * @return
     */
    private float caculateRatio(int childToBottomGap, int height) {
        float min = Math.min(childToBottomGap / (float)height, 1f);
        float ratio = Math.max(0f, min);
        return ratio;
    }
}
