package com.xn.materialdesignpro1.value_anim;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xn.materialdesignpro1.R;

import java.util.List;

public class TransitionAdapter extends RecyclerView.Adapter<TransitionAdapter.TransitionHolder> {

    private List<TransitionBean> dataList;
    private Context context;

    public TransitionAdapter(List<TransitionBean> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public TransitionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transition_item, parent, false);
        return new TransitionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TransitionHolder holder, final int position) {
        holder.iv.setImageResource(dataList.get(position).getRes());
        holder.tv.setText(dataList.get(position).getTitle());
        if (listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position,holder.iv,holder.tv);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class TransitionHolder extends RecyclerView.ViewHolder{

        private  ImageView iv;
        private  TextView tv;

        public TransitionHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item_iv);
            tv = itemView.findViewById(R.id.item_tv);
        }
    }
    private OnItemClickListener listener;

    public void setItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener{
        void onItemClick(int position,View iv,View tv);
    }
}
