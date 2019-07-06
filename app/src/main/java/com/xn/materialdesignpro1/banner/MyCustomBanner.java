package com.xn.materialdesignpro1.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * 自定义轮播图
 * 重点是怎样控制自己滚动、滑动手势不冲突
 * 使用view.postDelay(runnable)
 */
public class MyCustomBanner extends ViewPager {

    //滑动方向
    private Direction direction;
    //滑动间隔时间
    private long delaySecond;
    private OnSelectListener mOnSelectListener;
    private int[] resIds;

    public void setOnSelectListener(OnSelectListener onSelectListener){
        this.mOnSelectListener=onSelectListener;
        MyBannerActivity.MyPagerAdapter adapter= (MyBannerActivity.MyPagerAdapter) getAdapter();
        resIds = adapter.resId;
    }

    public void setImageList(int[] imageList){
        this.resIds=imageList;
    }
    //播放器
    private Runnable player=new Runnable() {
        @Override
        public void run() {
            play(direction);
        }
    };

    public MyCustomBanner(@NonNull Context context) {
        super(context);
    }

    public MyCustomBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public void setShowTimes(Long delaySecond){
        this.delaySecond=delaySecond;
    }
    public void setDirection(Direction direction){
        this.direction=direction;
    }

    public void startLoop(){
        stopLoop();
        postDelayed(player,delaySecond);
    }

    public void stopLoop(){
        removeCallbacks(player);
    }

    public void previous(){
        if (direction==Direction.left)
            play(Direction.right);
        else
            play(Direction.left);
    }
    public void next(){
        play(direction);
    }

    public synchronized void play(Direction direction){
        if (getAdapter()==null)return;
        int item = 0;
        int currentItem = getCurrentItem();
        int count = getAdapter().getCount();
        switch (direction){
            case left://向左滑动
                item=currentItem+1;
                if (item>count)
                    item=0;
                break;
            case right:
                item=currentItem-1;
                if (item<0)
                    item =count;
                break;
        }
        Log.i("xuning","currentItem:"+currentItem+",item:"+item+",count:"+count);
        setCurrentItem(item);
        int indicatorPos= item%resIds.length;
        mOnSelectListener.onSelect(indicatorPos);
        startLoop();
    }

    //防止内存泄漏
    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks(player);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOnPageChangeListener(new OnPageChangeListener() {

            private int currentPosition=0;
            boolean isLeft;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position > currentPosition){
                    Log.i("xuning","right");
                    currentPosition=position;
                    isLeft=false;
                }else if (position < currentPosition){
                    Log.i("xuning","left");
                    currentPosition=position;
                    isLeft=true;
                }

            }
            @Override
            public void onPageSelected(int position) {
                Log.i("xuning","position:"+position);
                mOnSelectListener.onSelect(position%resIds.length);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                //当页面拖动状态发生改变的时候
                if (state==SCROLL_STATE_IDLE)//页面静止
                    startLoop();
                else if (state ==SCROLL_STATE_DRAGGING)
                    stopLoop();
            }
        });
    }
}
