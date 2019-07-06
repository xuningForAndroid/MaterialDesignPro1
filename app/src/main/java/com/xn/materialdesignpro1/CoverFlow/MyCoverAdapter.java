package com.xn.materialdesignpro1.CoverFlow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.xn.materialdesignpro1.R;

public class MyCoverAdapter extends RecyclerView.Adapter<MyCoverAdapter.MyHolder> {

    private int[] mColors;
    private Context mContext;

    public MyCoverAdapter(int[] mColors, Context mContext) {
        this.mColors = mColors;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.cover_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.img.setImageResource(mColors[position% mColors.length]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了"+position%mColors.length, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

      class MyHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public MyHolder(View itemView){
            super(itemView);
           img= itemView.findViewById(R.id.img);
        }

    }

    interface  onItemClick{
        void clickItem(int pos);
    }
}
