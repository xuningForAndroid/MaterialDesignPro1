package com.xn.materialdesignpro1.CoverFlow;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 需要重写getChildDrawingorder对item的绘制顺序进行控制
 */
public class CoverflowRecyclerView extends RecyclerView {

    private CoverFlowManager.Builder mCoverFlowBuilder;
    private float mDownX;

    public CoverflowRecyclerView(Context context) {
        super(context);
        init();
    }

    public CoverflowRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoverflowRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        createManagerBuilder();
        setLayoutManager(mCoverFlowBuilder.build());
        setChildrenDrawingOrderEnabled(true);//开启重新排序
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    //创建布局构造器
    private void createManagerBuilder() {
        if (mCoverFlowBuilder==null)
            mCoverFlowBuilder=new CoverFlowManager.Builder();
    }
    //设置是否为普通平面滚动  true是平面滚动   false是叠加缩放滚动
    public void setFlatFlow(boolean isFlat){
        createManagerBuilder();
        mCoverFlowBuilder.setFlat(isFlat);
        setLayoutManager(mCoverFlowBuilder.build());
    }
    //设置item灰度渐变
    public void setGrayItem(boolean grayItem){
        createManagerBuilder();
        mCoverFlowBuilder.setGrayItem(grayItem);
        setLayoutManager(mCoverFlowBuilder.build());
    }

    //设置item透明度渐变
    public void setAlphaItem(boolean isAlpha){
        createManagerBuilder();
        mCoverFlowBuilder.setAlphaItem(true);
        setLayoutManager(mCoverFlowBuilder.build());
    }

    //设置item间隔比例
    public void setIntervalRatio(float intervalRatio){
        createManagerBuilder();
        mCoverFlowBuilder.setIntervalRatio(intervalRatio);
        setLayoutManager(mCoverFlowBuilder.build());
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (!(layout instanceof CoverFlowManager))
            throw new IllegalArgumentException("the layoutmanager must be CoverFlowLayoutManager");
        super.setLayoutManager(layout);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int center=getCoverFlowLayoutManager().getCenterPosition()
                -getCoverFlowLayoutManager().getfirstVisiblePosition();//计算正在显示的所有item的中间位置
        if (center < 0)
            center =0;
        else if (center > childCount)
            center =childCount;

        int order;
        if(i==center)
            order = childCount - 1;
        else if (i > center)
            order = center + childCount - 1 + i;
        else
            order = i;

        return order;
    }

    public int getSelectPos(){
        return getCoverFlowLayoutManager().getSelectPos();
    }

    //设置选中监听
    public void setOnSelectedListener(CoverFlowManager.onItemSelectedListener listener){
        getCoverFlowLayoutManager().setOnItemSelectedListener(listener);
    }

    public CoverFlowManager getCoverFlowLayoutManager(){
        return (CoverFlowManager) getLayoutManager();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                //设置父布局不拦截
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                //向左滑动到最前，向右滑动到最后
                if ((moveX > mDownX && getCoverFlowLayoutManager().getCenterPosition()==0) ||
                        ( moveX <mDownX && getCoverFlowLayoutManager().getCenterPosition()== getCoverFlowLayoutManager().getItemCount()-1
                        ))
                    getParent().requestDisallowInterceptTouchEvent(false);//如果是滑动到最后或者最前，放开拦截
                else //滑动到中间，设置父布局不拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
