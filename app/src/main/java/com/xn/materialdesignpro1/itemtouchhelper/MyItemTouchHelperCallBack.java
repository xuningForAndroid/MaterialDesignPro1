package com.xn.materialdesignpro1.itemtouchhelper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 条目触摸事件回调
 */
public class MyItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    MyItemTouchMoveListener moveListener;

    public MyItemTouchHelperCallBack(MyItemTouchMoveListener moveListener){
        this.moveListener=moveListener;
    }

    //callback回调监听时先调用的，用来判断是什么动作，比如判断方向（监听哪个方向）
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipFlags = ItemTouchHelper.LEFT ;
        int flags = makeMovementFlags(dragFlags, swipFlags);
        return flags;
    }

    //移动的时候回调---拖拽
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder src, RecyclerView.ViewHolder target) {
        if (src.getItemViewType()!=target.getItemViewType()){
            return false;
        }
        boolean result = moveListener.onItemDragged(src.getAdapterPosition(), target.getAdapterPosition());
        return result;
    }

    //侧滑
    @Override
    public void onSwiped(RecyclerView.ViewHolder holder, int direction) {
        //侧滑 删除数据，调用adapter.notifyItemRemove(position)
        moveListener.onItemRemove(holder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder holder, int actionState) {
        //判断选中状态
        if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE){//闲置状态
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(holder, actionState);
    }

    //是否允许长按拖拽
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    //是否允许侧滑事件
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        //恢复
        holder.itemView.setBackgroundColor(Color.WHITE);

        holder.itemView.setAlpha(1);
        holder.itemView.setScaleX(1);
        holder.itemView.setScaleY(1);
        super.clearView(recyclerView, holder);
    }

    @Override
    public void onChildDraw(Canvas c,
                            RecyclerView recyclerView,
                            RecyclerView.ViewHolder holder,
                            float dX, float dY, int actionState,
                            boolean isCurrentlyActive) {
        //dx为水平方向移动的增量（左侧为负）范围是0~view.getwidth() 0-1
        float alpha= 1-Math.abs(dX)/holder.itemView.getWidth();
        if (isCurrentlyActive && actionState==ItemTouchHelper.ACTION_STATE_SWIPE){

           holder.itemView.setAlpha(alpha);
           holder.itemView.setScaleX(alpha);
           holder.itemView.setScaleY(alpha);
        }
//        if (alpha==0){
//            holder.itemView.setAlpha(1);
//            holder.itemView.setScaleX(1);
//            holder.itemView.setScaleY(1);
//        }
        super.onChildDraw(c, recyclerView, holder, dX, dY, actionState, isCurrentlyActive);
    }

    /**
     * 获取删除方块的宽度
     * @param holder
     * @return
     */
    public int getSlideLimitation(RecyclerView.ViewHolder holder){
       QQMessageAdapter.QQViewHolder mHolder= (QQMessageAdapter.QQViewHolder) holder;
       return   mHolder.tv_delete.getLayoutParams().width;
    }
}
