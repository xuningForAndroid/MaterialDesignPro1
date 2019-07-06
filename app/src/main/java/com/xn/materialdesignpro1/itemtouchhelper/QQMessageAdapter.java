package com.xn.materialdesignpro1.itemtouchhelper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xn.materialdesignpro1.R;

import java.util.Collections;
import java.util.List;

public class QQMessageAdapter
        extends RecyclerView.Adapter<QQMessageAdapter.QQViewHolder> implements MyItemTouchMoveListener{

    private List<QQDataBean> datas;
    private StartDraggerListener draggerListener;

    public QQMessageAdapter(List<QQDataBean> datas,StartDraggerListener draggerListener){
        this.datas=datas;
        this.draggerListener=draggerListener;
    }

    @NonNull
    @Override
    public QQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
        return new QQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QQViewHolder holder, final int position) {
        holder.iv_logo.setImageResource(datas.get(position).getLogo());
        holder.tv_time.setText(datas.get(position).getTime());
        holder.tv_msg.setText(datas.get(position).getLastMessage());
        holder.tv_name.setText(datas.get(position).getUserName());
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "点击了删除", Toast.LENGTH_SHORT).show();
            }
        });

        holder.iv_logo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    draggerListener.onStartDrag(holder);
                }
                return false;
            }
        });
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        Log.i("itemCount",datas.size()+"");
        return datas.size();
    }

    @Override
    public boolean onItemRemove(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
        return true;
    }

    @Override
    public boolean onItemDragged(int fromPosition, int toPosition) {
        //数据交换，刷新
        Collections.swap(datas,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void showDeleteBtn(RecyclerView.ViewHolder holder) {
        QQViewHolder qqViewHolder= (QQViewHolder) holder;
        qqViewHolder.tv_delete.setVisibility(View.VISIBLE);
    }

    static class QQViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private TextView tv_msg;
        private TextView tv_time;
        private ImageView iv_logo;
        public TextView tv_delete;
        public QQViewHolder(View itemView) {
            super(itemView);
            tv_msg=itemView.findViewById(R.id.tv_lastMsg);
           tv_name= itemView.findViewById(R.id.tv_name);
           tv_time=itemView.findViewById(R.id.tv_time);
           iv_logo=itemView.findViewById(R.id.iv_logo);
           tv_delete=itemView.findViewById(R.id.tv_hide);
        }
    }
    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
     interface OnItemClickListener{

        void onItemClick(RecyclerView.ViewHolder holder, int position);
    }
}
