package com.xn.materialdesignpro1.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyFlowLayout extends ViewGroup {

    private List<List<View>> mAllViews=new ArrayList<>();//以行为单位存储起来
    private List<Integer> lineHeights=new ArrayList<>();//记录每一行有多高


    public MyFlowLayout(Context context) {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        lineHeights.clear();
        List<View> lineViews = new ArrayList<>();//每一行有多少列数据

        int viewWidth = getMeasuredWidth();//容器的宽度
        int viewHeight = getMeasuredHeight();//容器的高度
        int lineWidth=0;//行宽
        int lineHeight=0;//行高

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth()+params.rightMargin+params.leftMargin;
            int childHeight = child.getMeasuredHeight()+params.topMargin+params.bottomMargin;
            if (childWidth+lineWidth> viewWidth){
                //换行
                lineHeights.add(lineHeight);
                mAllViews.add(lineViews);
                lineWidth=0;
                lineViews=new ArrayList<>();
            }
            lineWidth+=childWidth;
            lineHeight=Math.max(lineHeight,childHeight);
            lineViews.add(child);

        }

        lineHeights.add(lineHeight);
        mAllViews.add(lineViews);

        int left=0;
        int top=0;
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            lineViews=mAllViews.get(i);//获取每一行所有的view
            lineHeight = lineHeights.get(i);//获取每一行的高度

            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                int left_child = left + lp.leftMargin;
                int top_child = top + lp.topMargin;
                int right_child = left_child + childView.getMeasuredWidth();
                int bottom_child = top_child + childView.getMeasuredHeight();
                childView.layout(left_child,top_child,right_child,bottom_child);
                left+=childView.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            }
            //下一行
            left=0;
            top+=lineHeight;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width=0;//控件的宽度，也是所有行中最宽的一行
        int height=0;//控件的高度，也是所有行高之和

        int lineWidth=0;//一行的宽度
        int lineHeight=0;//一行的高度

        //1.测量所有子控件的大小
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            //判断换行
            if (childWidth+lineWidth > widthSize){
                //重新起一行
                lineWidth=childWidth;
                height+=lineHeight;//记录除新开始的一行以上所有的行高之和
                lineHeight=childHeight;//记录新一行的高度

                width= Math.max(lineWidth,width);
            }else {
                lineWidth+=childWidth;
                lineHeight=Math.max(lineHeight,childHeight);
            }

            //最后一步，需要叠加
            if (i==childCount-1){
                width=Math.max(lineWidth,width);

                height+=lineHeight;
            }

        }

        //2.测量自身的大小
        int  measuredWidth=(widthMode==MeasureSpec.EXACTLY)?widthSize:width;
        int measuredHeight=(heightMode ==MeasureSpec.EXACTLY)?heightSize:height;
        setMeasuredDimension(measuredWidth,measuredHeight);
    }
}
