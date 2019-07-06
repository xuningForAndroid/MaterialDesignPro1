package com.xn.materialdesignpro1.fab_anim;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;

public class FabScrollListener extends OnScrollListener {
    public static final int THRESHOLD = 20;//滑动最小有效距离
    private int distance = 0;
    private FabShowHideListener showHideListener;
    private boolean visible =true;
    public FabScrollListener(FabShowHideListener showHideListener) {
        this.showHideListener = showHideListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        /**
         * dy是y轴方向的滑动增量，有正负之分   注意：向上滑动时，dy为正
         * 正在执行动画时候，不要再执行
         */
        Log.i("verticalDy",dy+"");
        if (distance>THRESHOLD && visible){
            //fab隐藏
            visible=false;
            showHideListener.onHide();
            distance=0;
        }else if (distance<-THRESHOLD && !visible){
            //fab显示
            visible=true;
            showHideListener.onShow();
            distance=0;
        }
        //可见并且向上滑动，不可见并向下滑动
        if ((visible && dy>0) || (!visible && dy<0)){
            distance += dy;
        }
    }
}
