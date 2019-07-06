package com.xn.materialdesignpro1.custom_anim_frame;

/**
 * 滚动事件监听
 */
public interface MyScrollListener {
    //滑动发生改变时调用
    void onChanged(float ratio);
    //view复原时调用
    void onReset();
}
