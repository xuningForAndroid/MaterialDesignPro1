package com.xn.materialdesignpro1.itemtouchhelper;

import android.support.v7.widget.RecyclerView;

/**
 * 监听条目拖拽、侧滑事件
 */
public interface MyItemTouchMoveListener {
    boolean onItemRemove(int position);
    boolean onItemDragged(int fromPosition,int toPosition);

    void showDeleteBtn(RecyclerView.ViewHolder holder);
}
