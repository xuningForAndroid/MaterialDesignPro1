package com.xn.materialdesignpro1.event_dispatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xn.materialdesignpro1.R;

import java.util.List;

public class SlidingListAdapter extends BaseAdapter {
    private final List<SlidingListBean> datas;
    private final Context context;
    private final LayoutInflater mInflater;


    public SlidingListAdapter(Context context, List<SlidingListBean> datas) {
        this.context=context;
        this.datas=datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.sliding_list_item,parent,false);
            holder=new viewHolder();
            holder.content=convertView.findViewById(R.id.content);
            holder.delete= convertView.findViewById(R.id.hide);
            convertView.setTag(holder);
        }else {
            holder= (viewHolder) convertView.getTag();
        }
        SlidingListBean bean = datas.get(position);
        holder.content.setText(bean.getShowText());
        holder.delete.setText(bean.getHideText());
        return convertView;
    }
    static class viewHolder{
        TextView content;
        TextView delete;
    }
}
