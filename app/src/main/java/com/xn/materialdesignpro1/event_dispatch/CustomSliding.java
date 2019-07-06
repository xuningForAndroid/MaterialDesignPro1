package com.xn.materialdesignpro1.event_dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * 自定义HorizontalScrollView实现QQ侧滑效果
 */
public class CustomSliding extends HorizontalScrollView {

    private int mScreenWidth;
    private boolean isFirst;
    private ViewGroup mMenu;
    private ViewGroup mMain;
    private int mMenuWidth;//menu处的宽度
    private int mMainWidth;
    private float downX;

    public CustomSliding(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSliding();
    }

    private void initSliding() {
        Context context = getContext();
        //获取屏幕宽度
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenWidth = displayMetrics.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isFirst){
           LinearLayout wrapper= (LinearLayout) getChildAt(0);
           mMenu = (ViewGroup) wrapper.getChildAt(0);
           mMain = (ViewGroup) wrapper.getChildAt(1);

           mMenuWidth =2* mScreenWidth/3;//规定屏幕的三分之二为menu的宽度
           mMainWidth =mScreenWidth;
           mMenu.getLayoutParams().width=mMenuWidth;
           mMain.getLayoutParams().width=mMainWidth;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed,left,top,right,bottom);
        if (changed){
            scrollTo(mMenuWidth,0);
            isFirst = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();
                break;
            case MotionEvent.ACTION_UP://处理回弹效果
                float dx = ev.getRawX() - downX;
                Log.i("xuning",ev.getRawX()+"x方向距离");
                if (dx < mScreenWidth/3 ){
                    smoothScrollTo(mMenuWidth,0);
                }else {
                    smoothScrollTo(0,0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {//处理动画效果
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("xuning","onScrollChanged:"+l);//l值规律，越往左侧滑动，值越大，往右滑动值越来越小
        float factor = (float) l/mMenuWidth;//最大移动距离是 mMenuWidth  1-0

        //平移
        mMenu.setTranslationX((float) (factor * mMenuWidth*0.6));
        //缩放0.6-1  (0~0.4)+0.6
        mMenu.setScaleX((float) (1-0.4*factor));
        mMenu.setScaleY((float) (1-0.4*factor));

        //Main缩放 1-0.8  (0.2~0)+0.8  0-1 1-0
        mMain.setScaleY((float) (0.8+0.2*factor));
        mMain.setScaleX((float) (0.8+0.2*factor));
        //透明 0.5-1
        mMenu.setAlpha(1-factor);
    }
}
