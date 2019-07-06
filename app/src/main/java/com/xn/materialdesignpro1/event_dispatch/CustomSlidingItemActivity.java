package com.xn.materialdesignpro1.event_dispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.xn.materialdesignpro1.R;

import java.util.ArrayList;
import java.util.List;

public class CustomSlidingItemActivity extends AppCompatActivity {

    private SlidingQQItem mItem;
    private ListView mListView;
    List<SlidingListBean> datas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_list_item);
//        mListView = findViewById(R.id.listView);
//        for (int i = 0; i < 30; i++) {
//            SlidingListBean bean = new SlidingListBean(false, "内容", "删除");
//            datas.add(bean);
//        }
//        SlidingListAdapter adapter = new SlidingListAdapter(this, datas);
//        mListView.setAdapter(adapter);
    }

}
