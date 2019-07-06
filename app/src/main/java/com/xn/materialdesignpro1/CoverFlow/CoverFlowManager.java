package com.xn.materialdesignpro1.CoverFlow;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/**
 * 自定义manager,控制recycleView 的布局
 */
public class CoverFlowManager extends RecyclerView.LayoutManager {
    private onItemSelectedListener mOnItemSelectedListener;

    private boolean mIsFlatFlow;
    private boolean mIsGrayItem;
    private boolean mIsAlphaItem;
    private float mIntervalRatio;
    //总的偏移量
    private int mOffsetAll;

    //保存所有item的上下左右的偏移量信息
    private SparseArray<Rect> mAllItemsFrames=new SparseArray<>();
    //记录item是否出现过屏幕，并且还未回收，true表示出现过屏幕上，并且还未回收
    private SparseBooleanArray mHasAttachedItems=new SparseBooleanArray();

    //回收器
    private RecyclerView.Recycler mRecycler;
    private int mDecoratedChildWidth;
    private int mDecoratedChildHeight;
    //起始item的x坐标
    private int mStartX;
    //起始item的y坐标
    private int mStartY;
    private int MAX_RECT_COUNT=100;
    private RecyclerView.State mState;
    private int  SCROLL_RIGHT=1;
    private int mSelectPosition;
    private ValueAnimator mValueAnimator;
    private int SCROLL_LEFT=0;
    private int mLastSelectPosition;


    public CoverFlowManager(boolean isFlatFlow, boolean isGrayItem, boolean isAlphaItem, float csIntervalRatio) {
        this.mIsFlatFlow = isFlatFlow;
        this.mIsGrayItem = isGrayItem;
        this.mIsAlphaItem = isAlphaItem;

        if (mIntervalRatio >=0)
            mIntervalRatio = csIntervalRatio;
        else if (mIsFlatFlow)
            mIntervalRatio =1.1f;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <=0 || state.isPreLayout()){
            mOffsetAll =0;
            return;
        }
        mAllItemsFrames.clear();
        mHasAttachedItems.clear();
        //得到子view的宽和高,这边的宽和高都是一样的，所以只需要一次测量
        View scap = recycler.getViewForPosition(0);
        addView(scap);
        measureChildWithMargins(scap,0,0);
        //计算测量布局的宽和高
        mDecoratedChildWidth = getDecoratedMeasuredWidth(scap);
        mDecoratedChildHeight = getDecoratedMeasuredHeight(scap);
        mStartX = Math.round(getHorizontalSpace()- mDecoratedChildWidth /2.0f);
        mStartY = Math.round(getVerticalSpace()- mDecoratedChildHeight /2.0f);

        float offset=mStartX;

        for (int i = 0; i < getItemCount() && i < MAX_RECT_COUNT; i++) {
            Rect frame = mAllItemsFrames.get(i);
            if(frame ==null){
                frame =new Rect();
            }
            frame.set(Math.round(offset),mStartY,Math.round(offset+ mDecoratedChildWidth),mStartY+mDecoratedChildHeight);
            mAllItemsFrames.put(i,frame);
            mHasAttachedItems.put(i,false);
            offset = offset + getIntervalDistance();//原始位置累加，
        }
        detachAndScrapAttachedViews(recycler);//在布局之前，将所有的子View先Detach掉，放入到Scrap缓存中
        if ((mRecycler ==null || mState ==null)&& mSelectPosition !=0){
            mOffsetAll= calculateOffsetForPosition(mSelectPosition);
            onSelectedCallBack();
        }

        layoutItems(recycler,state,SCROLL_RIGHT);
        mRecycler =recycler;
        mState=state;

    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (mValueAnimator !=null && mValueAnimator .isRunning())
            mValueAnimator.cancel();
        int travel= dx;
        if (dx + mOffsetAll <0)
            travel =-mOffsetAll;
        else if (dx+ mOffsetAll > getMaxOffset())
            travel=(int) (getMaxOffset() - mOffsetAll);
        mOffsetAll+= travel;
        layoutItems(recycler,state,dx>0? SCROLL_RIGHT:SCROLL_LEFT);
        return travel;
    }

    //获取最大偏移值
    private float getMaxOffset() {
        return (getItemCount()-1)* getIntervalDistance();
    }

    //计算出所在位置的偏移
    private int calculateOffsetForPosition(int position) {
        return Math.round(getIntervalDistance()* position);
    }

    //计算当前选中位置，并回调
    private void onSelectedCallBack() {
       mSelectPosition= Math.round(mOffsetAll/getIntervalDistance());
       if (mOnItemSelectedListener!=null && mSelectPosition != mLastSelectPosition){
           mOnItemSelectedListener.onSelect(mSelectPosition);
       }
       mLastSelectPosition=mSelectPosition;
    }

    private void layoutItem(View child, Rect frame){
        layoutDecorated(child,
                frame.left-mOffsetAll,
                frame.top,
                frame.right-mOffsetAll,
                frame.bottom);
        if (!mIsFlatFlow){
            //不是平面普通滚动的情况下才进行缩放
            child.setScaleX(computeScale(frame.left- mOffsetAll));
            child.setScaleY(computeScale(frame.left-mOffsetAll));
        }

        if (mIsAlphaItem)
            child.setAlpha(computeAlpha(frame.left-mOffsetAll));

        if (mIsGrayItem)
            grayItem(child,frame);
    }

    //变化item 的灰度值
    private void grayItem(View child, Rect frame) {
        float value=computeGrayScale(frame.left-mOffsetAll);
        ColorMatrix matrix = new ColorMatrix(new float[]{
                value, 0, 0, 0, 120 * (1 - value),
                0, value, 0, 0, 120 * (1 - value),
                0, 0, value, 0, 120 * (1 - value),
                0, 0, 0, 1, 250 * (1 - value),
        });
        Paint grayPaint = new Paint();
        grayPaint.setColorFilter(new ColorMatrixColorFilter(matrix));

        child.setLayerType(View.LAYER_TYPE_HARDWARE,grayPaint);
        if (value >=1)
            //移除硬件加速
            child.setLayerType(View.LAYER_TYPE_NONE,null);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        switch (state){
            case RecyclerView.SCROLL_STATE_IDLE:
                fixOffsetWhenFinishScroll();
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:

                break;
            case RecyclerView.SCROLL_STATE_SETTLING:

                break;
        }
    }

    @Override
    public void scrollToPosition(int position) {
        if (position < 0 || position > getItemCount()-1)return;
       mOffsetAll= calculateOffsetForPosition(position);

       if (mRecycler ==null || mState ==null){
           mSelectPosition=position;
       }else {
           layoutItems(mRecycler,mState,position> mSelectPosition? SCROLL_RIGHT: SCROLL_LEFT);
           onSelectedCallBack();
       }
    }


    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        int finalOffset = calculateOffsetForPosition(position);
        if (mRecycler ==null || state ==null)
            mSelectPosition =position;
        else
            startScroll(mOffsetAll,finalOffset);
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
        removeAllViews();
        mRecycler=null;
        mState=null;
        mOffsetAll=0;
        mSelectPosition=0;
        mLastSelectPosition=0;
        mHasAttachedItems.clear();
        mAllItemsFrames.clear();
    }

    //滚动到指定的x轴位置
    private void startScroll(int from, int to) {
        if (mValueAnimator!=null && mValueAnimator.isRunning()){
            mValueAnimator.cancel();
        }
       final int direction= from < to ? SCROLL_RIGHT: SCROLL_LEFT;
       mValueAnimator= ValueAnimator.ofFloat(from,to);
       mValueAnimator.setDuration(500);
       mValueAnimator.setInterpolator(new DecelerateInterpolator());
       mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
           @Override
           public void onAnimationUpdate(ValueAnimator animation) {
               mOffsetAll= Math.round((Float) animation.getAnimatedValue());
               layoutItems(mRecycler,mState,direction);
           }
       });
       mValueAnimator.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animation) {

           }

           @Override
           public void onAnimationEnd(Animator animation) {
                onSelectedCallBack();
           }

           @Override
           public void onAnimationCancel(Animator animation) {

           }

           @Override
           public void onAnimationRepeat(Animator animation) {

           }
       });
       mValueAnimator.start();
    }

    //修正停止滚动后，item滚动在中间位置
    private void fixOffsetWhenFinishScroll() {
        int scrollN = (int) (mOffsetAll * 1.0f / getIntervalDistance());
        float moreDx = (mOffsetAll % getIntervalDistance());
        if (moreDx > (getIntervalDistance() * 0.5)) {
            scrollN ++;
        }
        int finalOffset = (int) (scrollN * getIntervalDistance());
        startScroll(mOffsetAll, finalOffset);
        mSelectPosition = Math.round (finalOffset * 1.0f / getIntervalDistance());
    }

    private float computeGrayScale(int x) {
        float itemMidPos = x + mDecoratedChildWidth / 2; //item中点x坐标
        float itemDx2Mid = Math.abs(itemMidPos - getHorizontalSpace() / 2); //item中点距离控件中点距离
        float value = 1 - itemDx2Mid * 1.0f / (getHorizontalSpace() /2);
        if (value < 0.1) value = 0.1f;
        if (value > 1) value = 1;
        value = (float) Math.pow(value,.8);
        return value;
    }

    private float computeAlpha(int x) {
        float alpha = 1 - Math.abs(x - mStartX) * 1.0f / Math.abs(mStartX + mDecoratedChildWidth / mIntervalRatio);
        if (alpha < 0.3f) alpha = 0.3f;
        if (alpha > 1) alpha = 1.0f;
        return alpha;
    }

    private float computeScale(int x) {
        float scale = 1 - Math.abs(x - mStartX) * 1.0f / Math.abs(mStartX + mDecoratedChildWidth / mIntervalRatio);
        scale=scale<0?0:(scale>1?1:scale);
        return scale;
    }

    /**
     * 布局item，先清除已经超出屏幕的item，再绘制可以显示在屏幕里面的item
     * @param recycler
     * @param state
     * @param direction
     */
    private void layoutItems(RecyclerView.Recycler recycler, RecyclerView.State state, int direction) {
        if (state.isPreLayout())return;

        Rect displayFrame = new Rect(mOffsetAll, 0, mOffsetAll + getHorizontalSpace(), getVerticalSpace());

        int position=0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            position = getPosition(child);

            Rect rect= getFrame(position);
            if(!Rect.intersects(displayFrame,rect)){//item没有在显示区域，就说明需要回收
                removeAndRecycleView(child,recycler);//回收滑动出屏幕的view
                mHasAttachedItems.delete(position);
            }else {//item还在显示区域内，更新滑动后的item的位置
                layoutItem(child,rect);//更新item的位置
                mHasAttachedItems.put(position,true);
            }
        }

        if (position ==0 ) position =mSelectPosition;
        int min = position - 50 >= 0 ? position - 50 : 0;
        int max = position + 50 < getItemCount() ? position + 50 : getItemCount();

        for (int i = min; i < max; i++) {
            Rect rect = getFrame(i);
            if (Rect.intersects(displayFrame,rect) && !mHasAttachedItems.get(i)){//重新加载可见区域内的item
                View scrap = recycler.getViewForPosition(i);
                measureChildWithMargins(scrap,0,0);
                if (direction == SCROLL_LEFT || mIsFlatFlow)//向左滚动，新增的item需要添加在最前面
                    addView(scrap,0);
                else
                    addView(scrap);
                layoutItem(scrap,rect);
                mHasAttachedItems.put(i,true);
            }
        }
    }

    private Rect getFrame(int position) {
        Rect frame = mAllItemsFrames.get(position);
        if (frame ==null){
            frame=new Rect();
            float offset = mStartX + getIntervalDistance() * position;//原始位置累加
            frame.set(Math.round(offset),mStartY,Math.round(offset+ mDecoratedChildWidth),mStartY+ mDecoratedChildHeight);
        }
        return frame;
    }

    private float getIntervalDistance() {
        return mDecoratedChildWidth* mIntervalRatio;
    }

    //获取整个布局的垂直空间大小
    private int getVerticalSpace() {
        return getHeight()-getPaddingBottom()-getPaddingTop();
    }

    //获取整个布局的水平空间大小
    private int getHorizontalSpace() {
        return getWidth()-getPaddingLeft()-getPaddingRight();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public int getCenterPosition() {
        int pos = (int) (mOffsetAll / getIntervalDistance());
        int more = (int) (mOffsetAll % getIntervalDistance());
        if (more > getIntervalDistance() * 0.5f) pos++;
        return pos;
    }

    //获取第一个可见的item位置,,,,,该Item为绘制在可见区域的第一个Item，有可能被第二个Item遮挡
    public int getfirstVisiblePosition() {
        Rect displayFrame = new Rect(mOffsetAll, 0, mOffsetAll + getHorizontalSpace(), getVerticalSpace());
        int cur = getCenterPosition();
        for (int i = cur - 1; i >= 0; i--) {
            Rect rect = getFrame(i);
            if (!Rect.intersects(displayFrame, rect)) {
                return i + 1;
            }
        }
        return 0;
    }
    /**
     * 获取最后一个可见的Item位置
     * <p>Note:该Item为绘制在可见区域的最后一个Item，有可能被倒数第二个Item遮挡
     */
    public int getLastVisiblePosition() {
        Rect displayFrame = new Rect(mOffsetAll, 0, mOffsetAll + getHorizontalSpace(), getVerticalSpace());
        int cur = getCenterPosition();
        for (int i = cur + 1; i < getItemCount(); i++) {
            Rect rect = getFrame(i);
            if (!Rect.intersects(displayFrame, rect)) {
                return i - 1;
            }
        }
        return cur;
    }

    /**
     * 获取可见范围内最大的显示Item个数
     */
    public int getMaxVisibleCount() {
        int oneSide = (int) ((getHorizontalSpace() - mStartX) / (getIntervalDistance()));
        return oneSide * 2 + 1;
    }

    public int getSelectPos() {
        return mSelectPosition;
    }

    public void setOnItemSelectedListener(onItemSelectedListener listener) {
        this.mOnItemSelectedListener=listener;
    }

    static class Builder{
        boolean isFlat=false;
        boolean isGrayItem =false;
        boolean isAlphaItem=false;
        float csIntervalRatio=-1f;

        public CoverFlowManager build(){
            return new CoverFlowManager(isFlat,isGrayItem,isAlphaItem,csIntervalRatio);
        }

        public Builder setFlat(boolean Flat) {
            isFlat=Flat;
            return this;
        }

        public Builder setGrayItem(boolean grayItem) {
            isGrayItem=grayItem;
            return this;
        }

        public Builder setAlphaItem(boolean alphaItem) {
            isAlphaItem=alphaItem;
            return this;
        }

        public Builder setIntervalRatio(float intervalRatio) {
            csIntervalRatio=intervalRatio;
            return this;
        }
    }

    interface onItemSelectedListener{
        void onSelect(int pos);
    }
}
