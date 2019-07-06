package com.xn.materialdesignpro1.headerandfooter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyRecyclerHeaderAdapter extends RecyclerView.Adapter {

    private List<View> mHeaderViewInfos;
    private List<View> mFooterViewInfos;
    private RecyclerView.Adapter mAdapter;
    private final int ITEM_FOOTER=1;
    private final int ITEM_HEADER=2;

    public MyRecyclerHeaderAdapter(List<View> headerViewInfos, List<View> footerViewInfos, RecyclerView.Adapter adapter){
        this.mHeaderViewInfos=headerViewInfos;
        this.mFooterViewInfos=footerViewInfos;
        this.mAdapter=adapter;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType==ITEM_HEADER){//头
           return new HeaderViewHolder(mHeaderViewInfos.get(0));
       }else if (viewType==ITEM_FOOTER){//底
           return new HeaderViewHolder(mFooterViewInfos.get(0));
       }else {
            return mAdapter.onCreateViewHolder(parent,viewType);
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int numHeaders = getHeaderCount();
        if (mAdapter != null && position >= numHeaders) {
            int adjPosition = position - numHeaders;
            int adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder,adjPosition);
                return;
            }else {
                return ;
            }
        }
        return ;
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = getHeaderCount();
        if (mAdapter != null && position >= numHeaders) {
            int adjPosition = position - numHeaders;
            int adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }else {
                return ITEM_FOOTER;
            }
        }
        return ITEM_HEADER;
    }

    @Override
    public int getItemCount() {
        if (mAdapter!=null){
            return getFooterCount()+getHeaderCount()+mAdapter.getItemCount();
        }
        return getFooterCount()+getHeaderCount();
    }
    public int getFooterCount(){
        return mFooterViewInfos==null?0:mFooterViewInfos.size();
    }
    public int getHeaderCount(){
        return mHeaderViewInfos==null?0:mHeaderViewInfos.size();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
