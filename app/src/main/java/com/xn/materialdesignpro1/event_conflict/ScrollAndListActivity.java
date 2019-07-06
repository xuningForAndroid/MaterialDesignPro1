package com.xn.materialdesignpro1.event_conflict;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xn.materialdesignpro1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * scrollView嵌套listview滑动冲突
 *
 * view.scrollTo(x,y)  让view相对于它的初始位置滚动一段距离
 * view.scrollBy(x,y)  让view相对于它的现在位置滚动一段距离
 * 这两个方法都是滑动view里面的内容，就是里面的所有的子控件
 */
public class ScrollAndListActivity extends AppCompatActivity implements View.OnTouchListener,AbsListView.OnScrollListener{

    private MyScrollView sv;
    private ArrayAdapter adapter;
    private boolean isBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_and_list);
        ListView lv=findViewById(R.id.lv);
        sv = findViewById(R.id.sv);
        List<String> datas=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("item"+i);
        }
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datas);
        lv.setAdapter(adapter);
        lv.setOnTouchListener(this);
        lv.setOnScrollListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                sv.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                sv.requestDisallowInterceptTouchEvent(false);//拦截
                break;
        }
        return false;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.i("xuning",firstVisibleItem+""+visibleItemCount+""+totalItemCount);
        if (firstVisibleItem==25&&firstVisibleItem + visibleItemCount==totalItemCount){
            sv.requestDisallowInterceptTouchEvent(true);
            return;
        }
         if (firstVisibleItem + visibleItemCount==totalItemCount){
            //滚动到底部
            isBottom = true;
            sv.requestDisallowInterceptTouchEvent(false);//父布局拦截
        }else {
            isBottom =false;
            sv.requestDisallowInterceptTouchEvent(true);
        }
    }
}
