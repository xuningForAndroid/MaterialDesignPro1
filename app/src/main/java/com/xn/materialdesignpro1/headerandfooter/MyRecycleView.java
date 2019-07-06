package com.xn.materialdesignpro1.headerandfooter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleView extends RecyclerView {

    private List<View> mHeaderViewInfos=new ArrayList<>();
    private List<View> mFooterViewInfos=new ArrayList<>();
    private Adapter mAdapter;

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0) {
            mAdapter =new MyRecyclerHeaderAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }

    public void addHeaderView(View v){
        mHeaderViewInfos.add(v);
    }

    public void addFooterView(View v){
        mFooterViewInfos.add(v);
    }
}