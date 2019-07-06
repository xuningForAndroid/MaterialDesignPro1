package com.xn.materialdesignpro1.custom_anim_frame;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xn.materialdesignpro1.R;

/**
 * scrollView直接包裹的布局
 */
public class MyScrollContent extends LinearLayout {

    public MyScrollContent(Context context) {
        super(context);
    }

    public MyScrollContent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MyLayoutParams myLayoutParams = new MyLayoutParams(getContext(), attrs);
        return myLayoutParams;
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        MyLayoutParams myLayoutParams= (MyLayoutParams) params;
        if (!isHasCustomFeild(myLayoutParams)){//遍历到某个字view时发现没有自定义属性
            super.addView(child, index, params);
        }else {
            //将自定义属性加入自己的所加入的布局中
            MyScrollChildFramLayout childFramLayout = new MyScrollChildFramLayout(getContext());
            //将获取的属性填入自己的布局
            childFramLayout.setAlpha(myLayoutParams.mScrollAlpha);
            childFramLayout.setBgFromColor(myLayoutParams.mScrollFromColor);
            childFramLayout.setBgToColor(myLayoutParams.mScrollToColor);
            childFramLayout.setScaleX(myLayoutParams.mScrollScaleX);
            childFramLayout.setScaleY(myLayoutParams.mScrollScaleY);
            childFramLayout.setTransFrom(myLayoutParams.mScrollTransFrom);
            //将子view加入自己的布局
            childFramLayout.addView(child);
            //将自己的布局加入外层布局
           super.addView(childFramLayout,index,params);
        }


    }

    /**
     * 判断有无自定义属性
     * @param myLayoutParams
     * @return
     */
    private boolean isHasCustomFeild(MyLayoutParams myLayoutParams) {
        return myLayoutParams.mScrollAlpha ||
                myLayoutParams.mScrollScaleX ||
                myLayoutParams.mScrollScaleY ||
                (myLayoutParams.mScrollFromColor != -1 && myLayoutParams.mScrollToColor != -1) ||
                myLayoutParams.mScrollTransFrom != -1;
    }

 public static  class MyLayoutParams extends LinearLayout.LayoutParams{

      private final boolean mScrollAlpha;
      private final int mScrollTransFrom;
      private final int mScrollToColor;
      private final int mScrollFromColor;
      private final boolean mScrollScaleY;
      private final boolean mScrollScaleX;

      public MyLayoutParams(Context context, AttributeSet attrs) {
          super(context, attrs);
          TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomScrollView);
          mScrollAlpha = typedArray.getBoolean(R.styleable.MyCustomScrollView_scroll_alpha, false);
          mScrollScaleX = typedArray.getBoolean(R.styleable.MyCustomScrollView_scroll_scaleX, false);
          mScrollScaleY = typedArray.getBoolean(R.styleable.MyCustomScrollView_scroll_scaleY, false);
          mScrollFromColor = typedArray.getColor(R.styleable.MyCustomScrollView_scroll_bgfromcolor, -1);
          mScrollToColor = typedArray.getColor(R.styleable.MyCustomScrollView_scroll_bgToColor, -1);
          mScrollTransFrom = typedArray.getInt(R.styleable.MyCustomScrollView_scroll_translationfrom, -1);
          typedArray.recycle();
        }

    }
}
